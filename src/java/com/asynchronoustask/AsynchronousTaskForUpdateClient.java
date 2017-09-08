/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asynchronoustask;

import java.util.HashMap;
import java.util.Map;
import javax.websocket.Session;



/**
 *
 * @author vignesh
 */
public class AsynchronousTaskForUpdateClient implements Runnable{
  private Map<String,Session> users;
   private HashMap<String,String> usersSessionId;
   private Session session;

     public AsynchronousTaskForUpdateClient(Map<String,Session> users,HashMap<String,String> usersSessionId,Session session) {
        this.users = users;
        this.usersSessionId = usersSessionId;
        this.session = session;
    }
    
    @Override
    public void run() {
  
        
        
    
    }
    
}
