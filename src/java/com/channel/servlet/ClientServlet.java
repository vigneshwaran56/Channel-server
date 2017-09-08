package com.channel.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.DeploymentException;
import org.json.JSONException;
import org.json.JSONObject;

public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ClientServlet() {
        // TODO Auto-generated constructor stub
    }

    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    
        protected void processRequest(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException, JSONException{
            
            System.out.println("com.channel.servlet.ClientServlet.processRequest()");
          String  jsonString = request.getParameter("actionStructure");
               System.out.println("com.channel.servlet.ClientServlet.processRequest()"+jsonString);
          
        String usermail = request.getParameter("usermail");
           System.out.println("com.channel.servlet.ClientServlet.processRequest()"+usermail);
          
        if(jsonString != null && usermail != null){
           // 
            try {
              JSONObject json = new JSONObject(jsonString);
                   System.out.println("com.channel.servlet.ClientServlet.processRequest()"+json);
          
    	  json.put("usermail", request.getParameter("usermail")+":client"); 
              
              Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, json);

               Client jinifyClient = new Client();
               
                try {
                    jinifyClient.execute(json);
                } catch (DeploymentException ex) {
                    Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
               
               response.getWriter().write(json.toString());
    		response.setStatus(200);
            } 
           
            catch (JSONException ex) {
             Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
           } 
        }
       // response.getOutputStream().write("no query param value reached".getBytes());
        }

        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            try {
                processRequest(request, response);
            } catch (JSONException ex) {
                Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();
        
            System.out.println("com.channel.servlet.ClientServlet.doPost()"+data);
             try {
              JSONObject json = new JSONObject(data);
                      
              
                   System.out.println("com.channel.servlet.ClientServlet.processRequest()"+json);

             Client Client = new Client();
               
            try {
                Client.execute(json);
            } catch (DeploymentException ex) {
                Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
               
               response.getWriter().write(json.toString());
    		response.setStatus(200);
            } 
           
            catch (JSONException ex) {
             Logger.getLogger(ClientServlet.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo() {
            return "Short description";
        }// </editor-fold>

    

}
