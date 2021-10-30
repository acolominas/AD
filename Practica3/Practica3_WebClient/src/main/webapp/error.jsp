<%-- 
    Document   : error
    Created on : 30-sep-2021, 12:28:37
    Author     : alumne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String error_type = (String) request.getAttribute("error_type"); %>
<html>
    <head>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">     
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Image Manager - Error</title>
    </head>
    <body>
        <div align="center">
        <h1>Error</h1>
        <% 
            if (error_type == null) {
                response.sendRedirect("login.jsp");
            }
            else if (error_type.equals("login")) {
                out.println("Error login, porfavor introduzca usuario y password correcto");
                out.println("<div align='center'><a href='login.jsp'>Volver a pagina de login</a></div>");
            }
            else if (error_type.equals("eliminar")) {
                out.println("Se ha producido un error en el proceso de eliminacion de la imagen");
                out.println("<div align='center'><a href='menu.jsp'>Volver al menu principal</a></div>");                
            }
            else if (error_type.equals("modificar")) {
                out.println("Se ha producido un error en el proceso de modificacion de la imagen");
                out.println("<div align='center'><a href='menu.jsp'>Volver al menu principal</a></div>");                
            }
            else if (error_type.equals("registrar")) {
                out.println("Se ha producido un error durante el registro de la imagen");
                out.println("<div align='center'><a href='menu.jsp'>Volver al menu principal</a></div>"); 
            }
            else if (error_type.equals("crear_usuario")) {
                out.println("Se ha producido un error durante la creación del usuario");
                out.println("<div align='center'><a href='login.jsp'>Volver a la pagina de login</a></div>"); 
            }
            else {
                response.sendRedirect("login.jsp");
            }       
        %>
        </div>
        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>   
    </body>
</html>
