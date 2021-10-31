<%-- 
    Document   : listImg
    Created on : 30-sep-2021, 13:13:56
    Author     : alumne
--%>

<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%@page import="client.SOAPConnection,ws.Image" contentType="text/html" session="false" pageEncoding="UTF-8"%>
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
        <table class="table">
            <tr>
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
            List<ws.Image> images = SOAPConnection.listImages();
            
            ListIterator<ws.Image> listIterator = images.listIterator();
            while(listIterator.hasNext()) {
                ws.Image image = listIterator.next();
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
                out.println("<form method = 'POST'>");
                out.println("<input type='hidden' name='id' value='"+image.getId()+"'/>");
                out.println("<button type='submit' formaction='mostrarImagen'>View</button>");
                if (user.equals(image.getCreator())) {                                    
                    out.println("<button type='submit' formaction=''>Modify</button>");
                    out.println("<button type='submit' formaction=''>Delete</button>");                   
                }
                out.println("</form>"); 
                out.println("</td>");
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
