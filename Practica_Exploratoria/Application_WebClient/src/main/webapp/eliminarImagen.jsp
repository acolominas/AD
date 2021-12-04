<%-- 
    Document   : eliminarImagen
    Created on : 30-sep-2021, 13:13:37
    Author     : alumne
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="java.util.Base64"%>
<%@page import="client.RESTConnection"%>
<%@page contentType="text/html" session="false" pageEncoding="UTF-8"%>
<%   
HttpSession sessionsa = request.getSession(false);
String user = null;
if(sessionsa != null && sessionsa.getAttribute("username") != null) user = (String) sessionsa.getAttribute("username");      
else {
    response.sendRedirect("login.jsp");
    return;
} 
String id = request.getParameter("id");
String token = (String) sessionsa.getAttribute("token");
RESTConnection.setToken(token);
JSONObject resp = RESTConnection.searchById(Integer.valueOf(id));
JSONObject image = resp.getJSONArray("body").getJSONObject(0);
if (image == null || !user.equals(image.get("creator"))){
     response.sendRedirect("menu.jsp");
}
%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">                   
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Manager - Eliminar Imagen</title>
    </head>
    <body>
        <div align="center">
        <h1>Eliminar Imagen</h1>
        <form action="eliminarImagen" method = "POST">
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
            </tr>
        <%  
            out.println("<tr>");                        
            out.println("<td><label>"+image.get("title")+"</label></td>");
            out.println("<td><label>"+image.get("description")+"</label></td>");
            out.println("<td><label>"+image.get("keywords")+"</label></td>");
            out.println("<td><label>"+image.get("author")+"</label></td>");
            out.println("<td><label>"+image.get("creator")+"</label></td>");
            out.println("<td><label>"+image.get("storage_date")+"</label></td>");
            out.println("<td><label>"+image.get("capture_date")+"</label></td>");
            out.println("<td><label>"+image.get("filename")+"</label></td>");
            //byte[] img = SOAPConnection.downloadImage(image.getFilename());
            //String base64Image = Base64.getEncoder().encodeToString(img);
            //out.println("<td><a href='display.jsp?id="+image.get("id")+"'><img src='data:image/jpg;base64,"+base64Image+"''width='75' height='50'></a></a></td>");
            out.println("</tr>");         
        %>
           
           </table>
            <% out.println("<input type='hidden' name='id' value='"+image.get("id")+"'/>"); %>
           <input type="submit" value="Eliminar" /> 
        </form>
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
