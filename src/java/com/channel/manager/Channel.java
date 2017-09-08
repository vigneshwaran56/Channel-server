/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.channel.manager;


import com.channel.servlet.Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author tringapps
 */
public class Channel extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
 System.out.println("com.jinify.channel.Channel.doPost()"+request.getReader());

String id = request.getSession(true).getId();
 
 StringBuilder jasonBuff = new StringBuilder();
      String line = null;
      try {
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
          jasonBuff.append(line);
      } catch (Exception e) { /*error*/ }

       System.out.println("Request JSON string :"+jasonBuff.toString());
      //write the response here by getting JSON from jasonBuff.toString()

  try {
      JSONObject jsonObject = new JSONObject(jasonBuff.toString());
      
       Client jinifyClient = new Client();
           
           jinifyClient.execute(jsonObject);

   //   out.print(jsonObject.get("name"));//writing output as you did

    } catch (
            Exception e) {        
         throw new IOException("Error parsing JSON ");
   }
                    
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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
