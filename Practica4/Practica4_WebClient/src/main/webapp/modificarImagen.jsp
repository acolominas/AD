<%-- 
    Document   : modificarImagen
    Created on : 30-sep-2021, 13:13:19
    Author     : alumne
--%>

<%@page import="org.json.JSONObject"%>
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
        <title>Image Manager - Modificar Imagen</title>
    </head>
    <body>
        <div align="center">
        <h1>Modificar Imagen</h1>
        <form action="modificarImagen" method = "POST" enctype="multipart/form-data">
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
            out.println("<input type='hidden' name='id' value='"+image.get("id")+"'/>");
            out.println("<td><input type='text' value='"+image.get("title")+"' name='title'/></td>");
            out.println("<td><input type='text' value='"+image.get("description")+"' name='description'/></td>");
            out.println("<td><input type='text' value='"+image.get("keywords")+"' name='keywords'/></td>");
            out.println("<td><input type='text' value='"+image.get("author")+"' name='author'/></td>");
            out.println("<td><label>"+image.get("creator")+"</label></td>");
            out.println("<input type='hidden' name='creator' value='"+image.get("creator")+"'/>");
            out.println("<td><label>"+image.get("storage_date")+"</label></td>");
            out.println("<input type='hidden' name='storage_date' value='"+image.get("storage_date")+"'/>");
            out.println("<td><input type='date' value='"+image.get("capture_date")+"' name='capture_date'/></td>");
            out.println("<td><label>"+image.get("filename")+"</label></td>");
            out.println("<input type='hidden' name='filename' value='"+image.get("filename")+"'/>");
            %>
            <td><input type='file' name='image'/></td>
            </tr>    
           
           </table>
           <input type="submit" value="Modificar" />            
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
