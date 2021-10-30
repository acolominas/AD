<%-- 
    Document   : logout
    Created on : 03-oct-2021, 13:24:09
    Author     : alumne
--%>

<%@page contentType="text/html" session="false" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%   
           HttpSession sessionsa = request.getSession(false);
           if(sessionsa != null) {
                sessionsa.invalidate();
                sessionsa = null;
           }      
           response.sendRedirect(request.getContextPath() + "/login.jsp"); 
        %>
    </body>
</html>