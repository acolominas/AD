<%-- 
    Document   : crearUsuario
    Created on : 03-oct-2021, 13:46:49
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%   
HttpSession sessionsa = request.getSession(false);
String user = null;
if(sessionsa != null && sessionsa.getAttribute("username") != null) response.sendRedirect("menu.jsp"); 
%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">     
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Manager - Crear Usuario</title>
    </head>
    <body>
        <div align="center">
        <h1>Crear Usuario</h1>
        <form action="crearUsuario" method = "POST">
            <!--  Campos del formulario -->
            Username <input type="text" name="username"/> <br/>
            Password <input type="password" name="password_1"/> <br/>
            Repeat Password <input type="password" name="password_2"/> <br/>            
        <input type="submit" value="Create User" />
        </form>
        </div>
        <div align="center">
        <a href="login.jsp">Cancelar</a> 
        </div>
        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>    
    </body>
</html>
