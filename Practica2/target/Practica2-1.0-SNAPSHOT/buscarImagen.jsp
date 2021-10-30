<%-- 
    Document   : buscarImagen
    Created on : 30-sep-2021, 13:14:26
    Author     : alumne
--%>

<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%@page import="App.Image"%>
<%@page contentType="text/html" session="false" pageEncoding="UTF-8"%>
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
        <title>Image Manager - Buscar Imagenes</title>
    </head>
    <body>
        <div align="center">
        <h1>Buscar Imagen</h1>
        <%
            List<Image> images = (List<Image>) request.getAttribute("images");
            Integer emptysearch = (Integer) request.getAttribute("emptysearch");
            if (emptysearch != null && emptysearch == 1) out.println("La busqueda ha dado 0 resultados");
            if (images == null || images.isEmpty()) {
                out.println("<form action='buscarImagen' method = 'POST'>");
                out.println("<select name='search_by'>");
                out.println("<option value='title'>Title</option>");
                out.println("<option value='keyword'>Keyword</option>");
                out.println("<option value='author'>Author</option>");
                out.println("</select>");
                out.println("<input type='text' name='value'/> <br/>");
                out.println("<input type='submit' value='Search' />");
            }
            else {               
                out.println("<table class='table'>");
                out.println("<tr>");
                out.println("<th>Id</th>");
                out.println("<th>Title</th>");
                out.println("<th>Description</th>");
                out.println("<th>Keywords</th>");
                out.println("<th>Author</th>");
                out.println("<th>Creator</th>");
                out.println("<th>Storage Date</th>");
                out.println("<th>Capture Date</th>");
                out.println("<th>Filename</th>");
                out.println("<th>Image</th>");
                out.println("<th>Actions</th>");
                out.println("</tr>");

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
                    out.println("<td><a href='display.jsp?id="+img.id+"'><img src='Images/"+img.filename+"'width='75' height='50'></a></td>");
                    if (user.equals(img.creator)) {
                        out.println("<td><a href='modificarImagen.jsp?id="+img.id+"'>Modify</a>/<a href='eliminarImagen.jsp?id="+img.id+"'>Delete</a></td>");
                    }
                    out.println("</tr>");
                 }
                out.println("</table>");
            }
        %>
        </div>
        <div align="center">
        <a href="menu.jsp">Volver a menu</a>
        </div>
    </body>
</html>
