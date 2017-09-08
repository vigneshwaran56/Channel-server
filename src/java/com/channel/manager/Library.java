/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.channel.manager;

import com.asynchronoustask.AsynchronousTaskForClient;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author vignesh
 */
public class Library {
    
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
    public static void main(String[] args) {
        try{
           
            AsynchronousTaskForClient asynchronousTaskForChannel = new AsynchronousTaskForClient("846e79c8-ea1f-44d1-9399-f4379f476187");
           Thread th = new Thread(asynchronousTaskForChannel);
       th.start();
        }catch(Exception e){
         
        }
    }
}
