/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import client.SOAPConnection;

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession sessionsa = request.getSession(false);
            if (sessionsa == null || sessionsa.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            String search_by = request.getParameter("search_by");
            String value = request.getParameter("value");
            if (search_by == null || value == null) {
                response.sendRedirect("menu.jsp");
                return;
            }
            if (!value.isEmpty()) {                
                List<ws.Image> images = null;
                if (search_by.equals("title")) {
                    images = SOAPConnection.searchByTitle(value);
                } else if (search_by.equals("author")) {
                    images = SOAPConnection.searchByAuthor(value);                    
                } else if (search_by.equals("keyword")) {
                    images = SOAPConnection.searchByKeywords(value); 
                } else if (search_by.equals("creation_date")) {
                    images = SOAPConnection.searchByCreaDate(value); 
                }
                if(images.isEmpty()) request.setAttribute("emptysearch", 1);
                request.setAttribute("images", images);
            } else {
                request.setAttribute("images", null);
                request.setAttribute("", null);
            }

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
