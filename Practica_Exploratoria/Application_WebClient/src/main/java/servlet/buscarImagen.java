/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import client.Image;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import client.RESTConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author alumne
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
public class buscarImagen extends HttpServlet {   
      

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
            throws ServletException, IOException, JSONException  {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sessionsa = request.getSession(false);
            if (sessionsa == null || sessionsa.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
                return;
            }          
            
            JSONObject resp = null;
            String token = (String) sessionsa.getAttribute("token");
            RESTConnection.setToken(token);

            Image image = new Image();
            image.title = request.getParameter("title");
            image.description = request.getParameter("description");
            image.keywords = request.getParameter("keywords");
            image.author = request.getParameter("author");
            image.creator = request.getParameter("creator");
            image.capture_date = request.getParameter("capture_date");
            image.storage_date = request.getParameter("storage_date");;

            if(image.title == null || image.description == null || image.keywords == null || image.author == null || image.creator == null || image.capture_date == null || image.storage_date == null) response.sendRedirect("login.jsp");
            else
            {
                if(image.title.isEmpty() && image.description.isEmpty() && image.keywords.isEmpty() && image.author.isEmpty() && image.creator.isEmpty() && image.capture_date.isEmpty() && image.storage_date.isEmpty())
                    response.sendRedirect("redirect.jsp");    

                else {
                    
                    resp = RESTConnection.searchImages(image);
                }   
            }
            if(resp.get("message").equals("Images not found")) request.setAttribute("emptysearch", 1);
            request.setAttribute("images", resp);                

            RequestDispatcher rd = request.getRequestDispatcher("buscarImagen.jsp");
            rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(buscarImagen.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (JSONException ex) {
            Logger.getLogger(buscarImagen.class.getName()).log(Level.SEVERE, null, ex);
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