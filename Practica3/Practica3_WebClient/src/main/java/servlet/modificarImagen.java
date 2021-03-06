/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;
/*
import App.Image;
import DB.DB;
import DISK.ImageDisk;*/
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
import client.FileUtil;
import client.SOAPConnection;
import ws.Image;

/**
 *
 * @author alumne
 */
@WebServlet(name = "modificarImagen", urlPatterns = {"/modificarImagen"})
@MultipartConfig
public class modificarImagen extends HttpServlet {

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
            String user = null;
            if (sessionsa != null && sessionsa.getAttribute("username") != null) {
                user = (String) sessionsa.getAttribute("username");
            } else {
                response.sendRedirect("login.jsp");
                return;
            }
            String creator = request.getParameter("creator");
            if (creator == null || !creator.equals(user)) {
                response.sendRedirect("menu.jsp");
                return;
            }

            String id = request.getParameter("id");

            String title = request.getParameter("title");
            String description = request.getParameter("description");
            String keywords = request.getParameter("keywords");
            String storage_date = request.getParameter("storage_date");
            String capture_date = request.getParameter("capture_date");
            String filename = request.getParameter("filename");
            String author = request.getParameter("author");
            Part part = request.getPart("image");
            String old_filename = filename;

            if (title == null || description == null || keywords == null || capture_date == null || storage_date == null) {
                response.sendRedirect("login.jsp");
                return;
            }

            if (title.isEmpty() || description.isEmpty() || keywords.isEmpty() || author.isEmpty() || capture_date.isEmpty()) {
                request.setAttribute("error_type", "modificar");
                RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                rd.forward(request, response);
            } else {
                Boolean new_image = part.getSize() > 0;
                if (new_image) {
                    Format f = new SimpleDateFormat("yyyy-MM-dd");
                    storage_date = f.format(new Date());
                    filename = FileUtil.getFilename(part);
                    filename = String.valueOf(id) + "_" + filename;
                    SOAPConnection.uploadImage(part, filename);                  
                }
                Image image = new Image();
                image.setId(id);
                image.setTitle(title);
                image.setDescription(description);
                image.setKeywords(keywords);
                image.setAuthor(author);
                image.setCreator(creator);
                image.setStorageDate(storage_date);
                image.setCaptureDate(capture_date);
                image.setFilename(filename);
                //Image img = new Image(id, title, description, keywords, author, author, storage_date, capture_date, filename);

                SOAPConnection.modifyImage(image);
                
                if (new_image && !old_filename.equals(filename)) {
                    if(!SOAPConnection.removeImage(old_filename)) 
                    {
                        request.setAttribute("error_type", "eliminar");
                        RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
                        rd.forward(request, response);
                    }
                }

                response.sendRedirect("menu.jsp");

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
