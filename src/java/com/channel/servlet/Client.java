/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.channel.servlet;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author tringapps
 */
@ClientEndpoint
public class Client {

    WebSocketContainer container = null;
        Session session = null;
        
    @OnMessage
     public void onmessage(String message){
        System.out.println("i rcvd this "+message);
        Logger.getLogger("check").warning("i rcvd this "+message);
      
    }
    
    @OnClose
     public void onClose(){
         Logger.getLogger("check").warning("your connection has closed");
            
     } 
            
    
   public  void execute(JSONObject jsonMsg) throws DeploymentException, IOException {
    
       try{
           System.out.println("com.channel.servlet.JinifyClient.execute()"+jsonMsg);
        container = ContainerProvider.getWebSocketContainer();
        
            session = container.connectToServer(this, URI.create("ws://localhost:8080/OnQuik-Channel/api/OnQuikServer"));
        //    ws://104.197.200.69:8080/OnQuik-Channel/jinifyEndpoint
        //   ws://104.197.200.69:8080/OnQuik-Channel/jinifyEndpoint
            session.getBasicRemote().sendText(jsonMsg.toString());
            
       }catch(Exception ex){
            Logger.getLogger("check").warning("i rcvd this "+ex);
         
       } 
    
    
    }
   public static void main(String args[]){
       
        try {
            JSONObject jsonobject = new JSONObject(" {\"to\":[\"734749d2-08da-4854-910d-9ef694571bac\",\"b14cf0fb-a7d7-40f5-ae63-ad61a207c52f\",\"31222974-2212-48aa-a7e2-7213fa34a5dc\",\"48bca85c-1252-46f7-a86d-c44cfc82708b\",\"918116ee-53fe-406f-80b4-293eb7411b05\",\"86b3f9e0-c3cd-462c-a6d0-bde2344b9aae\",\"ec7a5470-6960-46f3-87c8-ad9a9bb663e4\",\"6eeae5be-4d31-4eeb-896e-81a295a1c6f1\",\"c2b92f27-902b-4268-8986-8984bb9deede\",\"be1e2d99-50a5-40eb-a789-4c1494262f77\",\"97775f8a-852d-4bda-8967-4e1e9abc83f5\",\"4f23fb64-527d-4e74-811f-eec55b781aa3\",\"48683db1-e84f-4569-88ef-f24745226955\",\"d9018bdc-0093-44ae-8941-3f3125a8671a\",\"18f0bda0-7867-4bf8-b918-7b5e8a26682d\",\"4bfc1a62-3cea-4314-a657-da2dcb5f2804\",\"a8fd9803-c861-43be-90f9-609ce62c33b1\",\"9acc4997-4d65-49ae-bfbd-2f981fc39d6f\",\"6a29e786-2ba9-40bd-bb5a-072b1e7f1a9f\",\"be8744d4-a1c0-4071-8e88-2475b9353d36\",\"b43f3781-2de1-4e26-9f9c-820ec2d3796d\",\"9f18f67f-cb36-42b8-b5db-31b56ea32607\",\"4ce82752-7562-45df-8a6d-d6f99ecbfe6c\",\"ec7a2bac-57b4-4018-8ec0-88c157c26e81\",\"fa9f678f-896f-40e3-ba4b-94624d255bbb\",\"9a0285ab-06e9-470b-9c99-61424f6164e0\",\"3bf29910-5f6e-4487-8f47-c1db67002750\",\"82980f0f-ae49-4bb3-9a2c-a97882cd145e\",\"bb3903e1-5b4e-4c0f-8956-931295c809a1\",\"ab0457dc-9e5a-4123-9088-efd3aac12b73\"],\"message\":\"{\\\"code\\\":\\\"jpt-rf-s5\\\",\\\"title\\\":\\\"Success!\\\",\\\"description\\\":\\\"Remove Place-Success\\\",\\\"message\\\":\\\"You have removed Jinify TTwo from Test with their access to its people and things.\\\",\\\"status\\\":\\\"Success\\\",\\\"actionMap\\\":{\\\"pushToken\\\":\\\"pushToken\\\",\\\"placeId\\\":\\\"9b20a5a8-af51-41a8-9f77-cf9c32ec322a\\\",\\\"combinedKey\\\":\\\"jinifyuser2@gmail.com9b20a5a8-af51-41a8-9f77-cf9c32ec322a\\\"},\\\"keySet\\\":\\\"\\u003crname\\u003e,\\u003cplace\\u003e\\\",\\\"actionSet\\\":\\\"placeId,pushToken,combinedKey\\\"}\",\"appId\":\"329f5524-9329-4b81-b02d-6ec903cb5c0b\",\"type\":\"json\",\"messageId\":\"50200afa-0c86-464e-891c-040491dee331\"}");
       
            JSONArray jsona =   jsonobject.getJSONArray("to");
            for(int i =0; i<jsona.length();i++){
                System.out.println("com.channel.servlet.Client.main()"+jsona.get(i));
            }
           
                    
                    } catch (JSONException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }
    
}
