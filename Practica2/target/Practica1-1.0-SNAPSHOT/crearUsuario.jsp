<%-- 
    Document   : crearUsuario
    Created on : 03-oct-2021, 13:46:49
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Manager - Crear Usuario</title>
    </head>
    <body>
        <form action="crearUsuario" method = "POST">
            <!--  Campos del formulario -->
            USERNAME: <input type="text" name="username"/> <br/>
            PASSWORD: <input type="password" name="password_1"/> <br/>
            REPEAT PASSWORD: <input type="password" name="password_2"/> <br/>            
        <input type="submit" value="Create User" />
        </form>
        <a href="login.jsp">Cancelar</a>  
    </body>
</html>
