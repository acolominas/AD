<%-- 
    Document   : listImg
    Created on : 30-sep-2021, 13:13:56
    Author     : alumne
--%>

<%@page import="com.google.gson.JsonElement"%>
<%@page import="org.json.JSONArray"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.Base64"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%@page import="client.RESTConnection" contentType="text/html" session="false" pageEncoding="UTF-8"%>
<%   
HttpSession sessionsa = request.getSession(false);
String user = null;
if(sessionsa != null && sessionsa.getAttribute("username") != null) user = (String) sessionsa.getAttribute("username");      
else {
    response.sendRedirect("login.jsp");
    return;
 }
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
                <th>Image</th>
                <th>Action</th>
            </tr>
        <%             
            String token = (String) sessionsa.getAttribute("token");
            RESTConnection.setToken(token);
            JSONArray images = RESTConnection.listImages();                         
            for (int i=0; i<images.length(); i++){
                JSONObject image = images.getJSONObject(i);
                out.println("<tr>");
                out.println("<td>"+image.get("title")+"</td>");
                out.println("<td>"+image.get("description")+"</td>");
                out.println("<td>"+image.get("keywords")+"</td>");
                out.println("<td>"+image.get("author")+"</td>");
                out.println("<td>"+image.get("creator")+"</td>");
                out.println("<td>"+image.get("storage_date")+"</td>");
                out.println("<td>"+image.get("capture_date")+"</td>");
                out.println("<td>"+image.get("filename")+"</td>");                                                           
                out.println("<td><a href='"+image.get("object_url")+"'><img src='"+image.get("object_url")+"' width='75' height='50'></a></a></td>");
                if (user.equals(image.get("creator"))) {
                    out.println("<td><a href='modificarImagen.jsp?id="+image.get("id")+"'>Modify</a> / <a href='eliminarImagen.jsp?id="+image.get("id")+"'>Delete</a><td>");
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
