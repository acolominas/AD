<%-- 
    Document   : eliminarImagen
    Created on : 30-sep-2021, 13:13:37
    Author     : alumne
--%>

<%@page import="App.Image"%>
<%@page import="DB.DB"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Manager - Eliminar Imagen</title>
    </head>
    <body>
        <form action="eliminarImagen" method = "POST">
           <table>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Description</th>
                <th>Keywords</th>
                <th>Author</th>
                <th>Creator</th>
                <th>Storage Date</th>
                <th>Capture Date</th>
                <th>Filename</th>
            </tr>
        <%  
            String id = request.getParameter("id");
            DB db = new DB();            
            Image img = DB.SearchImageById(id);        

            out.println("<tr>");
            out.println("<td><label>"+img.id+"</label></td>");            
            out.println("<td><label>"+img.title+"</label></td>");
            out.println("<td><label>"+img.description+"</label></td>");
            out.println("<td><label>"+img.keywords+"</label></td>");
            out.println("<td><label>"+img.author+"</label></td>");
            out.println("<td><label>"+img.creator+"</label></td>");
            out.println("<td><label>"+img.storage_date+"</label></td>");
            out.println("<td><label>"+img.capture_date+"</label></td>");
            out.println("<td><label>"+img.filename+"</label></td>");
            out.println("</tr>");         
        %>
           
           </table>
            <% out.println("<input type='hidden' name='id' value='"+img.id+"'/>"); %>
           <input type="submit" value="Eliminar" /> 
        </form>
        <a href="menu.jsp">Volver a menu</a>  
    </body>
</html>
