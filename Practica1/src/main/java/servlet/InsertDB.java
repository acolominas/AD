/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author alumne
 */
@WebServlet(name = "InsertDB", urlPatterns = {"/InsertDB"})
public class InsertDB extends HttpServlet {

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
       Connection connection = null;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String query;
            PreparedStatement statement;
            
            Class.forName("org.apache.derby.jdbc.ClientDriver");

            // create a database connection
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/pr2;user=pr2;password=pr2");
            
            /*
            // SQL Commands to create the database can be found in file database.sql		 		  
            */            

            /* 
               COMMENT THE FOLLOWING CODE IF YOU ALREADY HAVE 
               CREATED AND FILLED THE TABLES
            */          
           String author=request.getParameter("author");
           query = "SELECT * FROM usuarios WHERE id_usuario = ?";
           statement = connection.prepareStatement(query);
           statement.setString(1, author);
           ResultSet rs = statement.executeQuery();
           
           if ( !rs.next()) {
               out.println("No existe un autor con ese ID");
           }
           else {           
                query = "INSERT INTO IMAGE (TITLE, DESCRIPTION, KEYWORDS, AUTHOR, CREATOR, CAPTURE_DATE, STORAGE_DATE, FILENAME) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query); 
                String title=request.getParameter("title");
                String desc=request.getParameter("description");
                String keywords=request.getParameter("keywords");
                String creator=request.getParameter("creator");
                String cap_date=request.getParameter("capture_date");
                String sto_date=request.getParameter("storage_date");
                String filename=request.getParameter("filename");
                statement.setString(1, title);
                statement.setString(2, desc);
                statement.setString(3, keywords);
                statement.setString(4, author);
                statement.setString(5, creator);
                statement.setString(6, cap_date);
                statement.setString(7, sto_date);
                statement.setString(8, filename);            
                statement.executeUpdate();          
           }
            // Select information from users and images and show in the web
            query = "select * from usuarios";
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();    

            while (rs.next()) {
                // read the result set
                out.println("<br>Id usuario = " + rs.getString("id_usuario"));
                out.println("Password = " + rs.getString("password"));
            }

            query = "select * from image";
            statement = connection.prepareStatement(query);
            rs = statement.executeQuery();                                    

            while (rs.next()) {
                // read the result set
                out.println("<br>Id image = " + rs.getString("id"));
                out.println("Titulo = " + rs.getString("title"));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            out.println("Error!");
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
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

