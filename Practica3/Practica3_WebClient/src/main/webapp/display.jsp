<%-- 
    Document   : display
    Created on : 19-oct-2021, 13:20:34
    Author     : alumne
--%>

<%@page import="java.util.Base64"%>
<%@page import="ws.Image"%>
<%@page import="client.SOAPConnection"%>
<%@page contentType="text/html" session = "false" pageEncoding="UTF-8"%>
<%   
HttpSession sessionsa = request.getSession(false);
String user = null;
if(sessionsa != null && sessionsa.getAttribute("username") != null) user = (String) sessionsa.getAttribute("username");      
else response.sendRedirect("login.jsp"); 

String id = request.getParameter("id");          
Image image = SOAPConnection.searchById(Integer.valueOf(id));
if (image == null){
     response.sendRedirect("menu.jsp");
     return;
}
%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">     
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Display Image</title>
    </head>
    <body>
            <% byte[] img = SOAPConnection.downloadImage(image.getFilename());
            String base64Image = Base64.getEncoder().encodeToString(img);            
            out.println("<div align='center'>");
            out.println("<img src='data:image/jpg;base64,"+base64Image+"'/>");
            out.println("</div>");
            out.println("<div align='center'>");
            out.println("<a href=\"menu.jsp\">Volver a menu</a>");
            out.println("</div>");
            %>
        <!-- JavaScript -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>    
    </body>
</html>
