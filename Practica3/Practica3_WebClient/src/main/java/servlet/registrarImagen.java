/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

//import App.Image;
//import DB.DB;
//import DISK.ImageDisk;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import client.SOAPConnection;
import client.FileUtil;
import java.text.DateFormat;

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
            } else {
                response.sendRedirect("login.jsp");
                return;
            }
            Date date = new Date();
            //Pattern for showing milliseconds in the time "SSS"
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String stringDate = sdf.format(date);
            System.out.println(stringDate);
            
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
                System.out.println("Failed to register the image");
                request.setAttribute("error_type", "registrar");
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request, response);
            } else {
                int id = SOAPConnection.getMaxId();

                out.println(id);

                Format f = new SimpleDateFormat("yyyy-MM-dd");
                String storage_date = f.format(new Date());
                
                System.out.println(System.currentTimeMillis());
                
                String filename = FileUtil.getFilename(part);
                filename = String.valueOf(id + 1) + "_" + filename;
                
                if (SOAPConnection.uploadImage(part,filename)) {
                    ws.Image image = new ws.Image();
                    image.setTitle(title);
                    image.setDescription(description);
                    image.setKeywords(title);
                    image.setAuthor(author);
                    image.setCreator(user);
                    image.setCaptureDate(capture_date);
                    image.setStorageDate(storage_date);
                    image.setFilename(filename);
                    SOAPConnection.registerImage(image);
                    
                    date = new Date();
                     //Pattern for showing milliseconds in the time "SSS"
                    sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                    stringDate = sdf.format(date);
                    System.out.println(stringDate);
                    
                    System.out.println(System.currentTimeMillis());
                    
                    response.sendRedirect("menu.jsp");
                }

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
