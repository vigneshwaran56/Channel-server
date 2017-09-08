/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.channel.servlet;

import com.asynchronoustask.AsynchronousTaskForClient;
import com.channel.manager.Library;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tringapps
 */

@ServerEndpoint("/api/{userid}")
public class Server  {
    private final Logger log = Logger.getLogger(getClass().getName());

  
   private static  Map<String,Session> users = new TreeMap<String,Session>();
    private static HashMap<String,String> usersSessionId = new HashMap<>();

         
   @OnOpen
     public void onOpen(Session userSession,@PathParam("userid") String userid ) throws IOException{
      log.log(Level.INFO, "{0} connected!", userSession.getId());
       System.out.println("com.channel.servlet.Server.onOpen()"+userid);
       
        users.put(userid, userSession);
        usersSessionId.put(userSession.getId(),userid);

        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("to",userid);
            jSONObject.put("from","OnQuik-Server");
            jSONObject.put("message", "{\\\"code\\\":\\\"conn-estab\\\",\\\"title\\\":\\\"Success!\\\",\\\"description\\\":\\\"Connection Established!!\\\",\\\"message\\\":\\\"You have succesfully connected with channel server.\\\",\\\"status\\\":\\\"Success\\\"}");
            broadcast(jSONObject,userSession);
        } catch (JSONException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
       
         try{
           
            AsynchronousTaskForClient asynchronousTaskForChannel = new AsynchronousTaskForClient(userid);
           Thread th = new Thread(asynchronousTaskForChannel);
       th.start();
        }catch(Exception e){
         
        }
        
    }
   
     @OnMessage
    public void onMessage(Session session, String message) throws IOException {
        try {
            log.info(message.toString());
            JSONObject jSONObject = new JSONObject(message);
            jSONObject.put("from", usersSessionId.get(session.getId())+"");
            System.out.println("com.channel.servlet.Server.onMessage()"+jSONObject);
            sendMessageToClient(jSONObject);
        } catch (JSONException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @OnClose
    public void onClose(Session session) throws IOException{
        log.log(Level.INFO, "{0} disconnected!", session.getId());
        String clientId = usersSessionId.get(session.getId());
        
    users.remove(usersSessionId.get(session.getId()));      
         usersSessionId.remove(session.getId());
       
        
        try{
           
            AsynchronousTaskForClient asynchronousTaskForChannel = new AsynchronousTaskForClient(clientId);
           Thread th = new Thread(asynchronousTaskForChannel);
       th.start();
        }catch(Exception e){
         
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        
        System.out.println("com.channel.servlet.Server.onError()"+session.getId());
        String clientId = usersSessionId.get(session.getId());
        users.remove(clientId);
        usersSessionId.remove(session.getId());
        
        try{
           
            AsynchronousTaskForClient asynchronousTaskForChannel = new AsynchronousTaskForClient(clientId);
           Thread th = new Thread(asynchronousTaskForChannel);
       th.start();
       System.out.println("com.channel.servlet.Server.onError()"+usersSessionId.get(session.getId()));
       
        }catch(Exception e){
         
        }
    }
    
     public static Logger saveLogger(Logger logger){
        
          

    try {  

        // This block configure the logger with handler and formatter  
        FileHandler  fh = new FileHandler("/home/vignesh/Logging/tomcatlog.log");  
        logger.addHandler(fh);
        SimpleFormatter formatter = new SimpleFormatter();  
        fh.setFormatter(formatter);  

    } catch (SecurityException e) {  
        e.printStackTrace();  
    } catch (IOException e) {  
        e.printStackTrace();  
    }  

        return logger;
    }
    private static void broadcast(JSONObject message,Session session) throws IOException {
     synchronized(session) {
         if(session.isOpen()){
        session.getBasicRemote().sendText(message.toString());
         }
     }
    }
    private static void sendMessageToClient(JSONObject message) throws IOException, JSONException{
        System.out.println("com.channel.servlet.Server.sendMessageToClient()"+message);
        
        JSONArray jsona = message.getJSONArray("to");
                for(int i = 0; i < jsona.length(); i++){
                    String to = jsona.get(i)+"";
                     System.out.println("to"+to);
        
        System.out.println("users set"+users);
        
        if(users.containsKey(to)){
            Session session =   users.get(to);
            synchronized(session) {
            message.put("to", to);
     if(session.isOpen()){
            session.getBasicRemote().sendText(message.toString());
     }
            }
        }
    }    
        
        
    }
    private static String getSessionId(String to) {
        if (users.containsValue(to)) {
            for (String sessionId: users.keySet()) {
                if (usersSessionId.get(sessionId).equals(to)) {
                    return sessionId;
                }
            }
        }
        return null;
    }
}
