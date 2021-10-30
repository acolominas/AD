<%-- 
    Document   : registrarImagen
    Created on : 30-sep-2021, 13:12:54
    Author     : alumne
--%>

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
        <title>Image Manager - Registrar Imagen</title>
    </head>
    <body>
        <div align="center">
        <h1>Registrar Imagen</h1>
        <form action="registrarImagen" method = "POST" enctype="multipart/form-data">
           <table>
            <tr>
                <th>Title</th>
                <th>Description</th>
                <th>Keywords</th>
                <th>Author</th>
                <th>Capture Date</th>
                <th>Image</th>
            </tr>
            <tr>
            <td><input type='text' name='title'/></td>
            <td><input type='text' name='description'/></td>
            <td><input type='text' name='keywords'/></td>
            <td><input type='text' name='author'/></td>
            <td><input type='date' name='capture_date'/></td>
            <td><input type='file' name='Image'/></td>
            </tr>     
           </table>
           <input type="submit" value="Registrar" /> 
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
