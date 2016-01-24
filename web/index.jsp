<%-- 
    Document   : newjsp
    Created on : Dec 20, 2015, 2:35:41 PM
    Author     : test
--%>


<%@page import="java.io.Writer"%>
<%@page import="db.bean.ActivitiesBean"%>
<%@page import="db.bean.AdminBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.handle.DB_Select"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("staffInfo") == null) {
                String redirectURL = "login.jsp";
                response.sendRedirect(redirectURL);
            } else {
                String redirectURL = "activities.jsp";
                response.sendRedirect(redirectURL);
            }
        %>


    </body>
</html>
