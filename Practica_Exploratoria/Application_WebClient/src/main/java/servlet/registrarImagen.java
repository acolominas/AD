/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import client.RESTConnection;
import client.FileUtil;
import client.Image;
/**
 *
 * @author alumne
 */
@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
public class registrarImagen extends HttpServlet {

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
            String user = null;
            if (sessionsa != null && sessionsa.getAttribute("username") != null) {
                user = (String) sessionsa.getAttribute("username");
                String token = (String) sessionsa.getAttribute("token");
                RESTConnection.setToken(token);
            } else {
                response.sendRedirect("login.jsp");
                return;
            }

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String keywords = request.getParameter("keywords");
            String author = request.getParameter("author");
            String capture_date = request.getParameter("capture_date");
            Part part = request.getPart("image");

            if (title == null || description == null || keywords == null || author == null || capture_date == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            if (title.isEmpty() || description.isEmpty() || keywords.isEmpty() || author.isEmpty() || capture_date.isEmpty() || part == null) {
                request.setAttribute("error_type", "registrar");
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request, response);
            } else {                                                 
                String filename = FileUtil.getNewFilename(part);                          
                //if (RESTConnection.uploadImage(part,filename)) {
                    Image image = new Image();
                    image.title = title;
                    image.description = description;
                    image.keywords = keywords;
                    image.author = author;
                    image.creator = user;
                    image.capture_date = capture_date;
                    image.filename = filename;
                    if(RESTConnection.registerImage(image)) {                   
                        response.sendRedirect("menu.jsp");
                    }
                    else {
                        request.setAttribute("error_type", "registrar");
                        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                        rd.forward(request, response);
                    }
                //}

            }
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
