/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import client.SOAPConnection;
import java.util.Base64;
import ws.Image;

/**
 *
 * @author alumne
 */
@WebServlet(name = "mostrarImagen", urlPatterns = {"/mostrarImagen"})
public class mostrarImagen extends HttpServlet {

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
            HttpSession sessionsa = request.getSession(false);
            if (sessionsa == null || sessionsa.getAttribute("username") == null) {
                response.sendRedirect("login.jsp");
                return;
            }
            String id = request.getParameter("id");
            if (id == null || id.isEmpty()) {
                response.sendRedirect("listImg.jsp");
                return;
            }
            Image image =  SOAPConnection.searchById(Integer.valueOf(id));
            if (image == null){
                response.sendRedirect("menu.jsp");
                return;
            }
            byte[] img = SOAPConnection.downloadImage(image.getFilename());
            String base64Image = Base64.getEncoder().encodeToString(img);        
            out.println("<div align='center'>");
            out.println("<img src='data:image/jpg;base64,"+base64Image+"'/>");
            out.println("</div>");
            out.println("<div align='center'>");
            out.println("<a href=\"menu.jsp\">Volver a menu</a>");
            out.println("</div>");
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
