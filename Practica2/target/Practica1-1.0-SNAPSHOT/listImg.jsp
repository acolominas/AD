<%-- 
    Document   : listImg
    Created on : 30-sep-2021, 13:13:56
    Author     : alumne
--%>

<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%@page import="DB.DB,App.Image" contentType="text/html" session="false" pageEncoding="UTF-8"%>
<%   
HttpSession sessionsa = request.getSession(false);
String user = null;
if(sessionsa != null && sessionsa.getAttribute("username") != null) user = (String) sessionsa.getAttribute("username");      
else response.sendRedirect("login.jsp"); 
%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">                   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Manager - Listar Imagenes</title>
    </head>
    <body>
        <div align="center">
        <h1>Listar Imagenes</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Keywords</th>
                <th>Author</th>
                <th>Creator</th>
                <th>Storage Date</th>
                <th>Capture Date</th>
                <th>Filename</th>
                <th>Action</th>
            </tr>
        <%  
            DB db = new DB();
            List<Image> images = DB.ListImages();
            
            ListIterator<Image> listIterator = images.listIterator();
            while(listIterator.hasNext()) {
                Image img = listIterator.next();
                out.println("<tr>");
                out.println("<td>"+img.id+"</td>");
                out.println("<td>"+img.title+"</td>");
                out.println("<td>"+img.description+"</td>");
                out.println("<td>"+img.keywords+"</td>");
                out.println("<td>"+img.author+"</td>");
                out.println("<td>"+img.creator+"</td>");
                out.println("<td>"+img.storage_date+"</td>");
                out.println("<td>"+img.capture_date+"</td>");
                out.println("<td>"+img.filename+"</td>");
                if (user.equals(img.creator)) {
                    out.println("<td><a href='Images/"+img.filename+"'>View</a>/<a href='modificarImagen.jsp?id="+img.id+"'>Modify</a>/<a href='eliminarImagen.jsp?id="+img.id+"'>Delete</a></td>");
                }
                else {
                    out.println("<td><a href='Images/"+img.filename+"'>View</a></td>");
                }
                out.println("</tr>");
            }          
        %>
        </table>
        </div>
        <div align="center">
        <a href="menu.jsp">Volver a menu</a>
        </div>
        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    
    </body>
</html>
