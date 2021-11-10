<%-- 
    Document   : buscarImagen
    Created on : 30-sep-2021, 13:14:26
    Author     : alumne
--%>

<%@page import="client.RESTConnection"%>
<%@page import="org.json.JSONObject"%>
<%@page import="org.json.JSONArray"%>
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
            JSONObject resp = (JSONObject) request.getAttribute("images");    
            Integer emptysearch = (Integer) request.getAttribute("emptysearch");
            if (emptysearch != null && emptysearch == 1) out.println("La busqueda ha dado 0 resultados");
            if (resp == null || (emptysearch != null && emptysearch == 1)) {                
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
                out.println("<th>Image</th>");
                out.println("<th>Actions</th>");
                out.println("</tr>");                
                
                JSONArray images = resp.getJSONArray("body");
                JSONObject image = new JSONObject();
                for (int i=0; i<images.length(); i++){
                    image = images.getJSONObject(i);              
                    out.println("<tr>");
                    out.println("<td>"+image.get("title")+"</td>");
                    out.println("<td>"+image.get("description")+"</td>");
                    out.println("<td>"+image.get("keywords")+"</td>");
                    out.println("<td>"+image.get("author")+"</td>");
                    out.println("<td>"+image.get("creator")+"</td>");
                    out.println("<td>"+image.get("storage_date")+"</td>");
                    out.println("<td>"+image.get("capture_date")+"</td>");
                    out.println("<td>"+image.get("filename")+"</td>");                    
                    JSONObject resp_img = RESTConnection.downloadImage(image.get("id").toString());
                    String base64Image = resp_img.get("body").toString();                
                    out.println("<td><a href='display.jsp?id="+image.get("id")+"'><img src='data:image/jpg;base64,"+base64Image+"' width='75' height='50'></a></a></td>");
                    
                    if (user.equals(image.get("creator"))) {
                        out.println("<td><a href='modificarImagen.jsp?id="+image.get("id")+"'>Modify</a> / <a href='eliminarImagen.jsp?id="+image.get("id")+"'>Delete</a></td>");
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
