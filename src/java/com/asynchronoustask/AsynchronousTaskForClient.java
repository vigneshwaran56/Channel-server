/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asynchronoustask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;



/**
 *
 * @author vignesh
 */
public class AsynchronousTaskForClient implements Runnable{

   private String clientId;

     public AsynchronousTaskForClient(String clientId) {
        this.clientId = clientId;
       
    }
    
    @Override
    public void run() {
  
        System.out.println("asynchc task"+clientId);
       
        
       try {
            URL url = new URL("http://server2-dot-jinify-dev.appspot.com/updatechannel?channelId="+clientId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("GET");        
           System.out.println("com.asynchronoustask.AsynchronousTaskForClient.run()"+connection.getResponseCode());
       } catch (ProtocolException ex) {
           Logger.getLogger(AsynchronousTaskForClient.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(AsynchronousTaskForClient.class.getName()).log(Level.SEVERE, null, ex);
       }
       
    
    }
    
}
