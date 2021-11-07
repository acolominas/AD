<%-- 
    Document   : buscarImagen
    Created on : 30-sep-2021, 13:14:26
    Author     : alumne
--%>

<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%@page import="ws.Image"%>
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
                out.println("<option value='creation_date'>Creation Date</option>");
                out.println("</select>");
                out.println("<input type='text' name='value'/> <br/>");
                out.println("<input type='submit' value='Search' />");
            }
            else {               
                out.println("<table class='table'>");
                out.println("<tr>");
                out.println("<th>Title</th>");
                out.println("<th>Description</th>");
                out.println("<th>Keywords</th>");
                out.println("<th>Author</th>");
                out.println("<th>Creator</th>");
                out.println("<th>Storage Date</th>");
                out.println("<th>Capture Date</th>");
                out.println("<th>Filename</th>");
                out.println("<th>Actions</th>");
                out.println("</tr>");

                ListIterator<Image> listIterator = images.listIterator();
                while(listIterator.hasNext()) {
                    Image image = listIterator.next();
                    out.println("<tr>");
                    out.println("<td>"+image.getTitle()+"</td>");
                    out.println("<td>"+image.getDescription()+"</td>");
                    out.println("<td>"+image.getKeywords()+"</td>");
                    out.println("<td>"+image.getAuthor()+"</td>");
                    out.println("<td>"+image.getCreator()+"</td>");
                    out.println("<td>"+image.getStorageDate()+"</td>");
                    out.println("<td>"+image.getCaptureDate()+"</td>");
                    out.println("<td>"+image.getFilename()+"</td>");
                    out.println("<td>");
                    out.println("<a href='display.jsp?id="+image.getId()+"'>View</a>");
                    if (user.equals(image.getCreator())) {
                        out.println("/ <a href='modificarImagen.jsp?id="+image.getId()+"'>Modify</a> / <a href='eliminarImagen.jsp?id="+image.getId()+"'>Delete</a>");
                    }
                    out.println("</td>");
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
