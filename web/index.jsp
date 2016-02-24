<%-- 
    Document   : newjsp
    Created on : Dec 20, 2015, 2:35:41 PM
    Author     : test
--%>


<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
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

            try {
                /*    System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
                 Class.forName("com.mysql.jdbc.Driver");
                 String dbUser = this.getServletContext().getInitParameter("dbUsername");
                 String dbPassword = this.getServletContext().getInitParameter("dbPassword");
                 String dbUrl = this.getServletContext().getInitParameter("dbUrl");
                 Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);*/
                if (session.getAttribute("staffInfo") == null) {
                    String redirectURL = "login.jsp";
                    response.sendRedirect(redirectURL);
                } else {
                    String redirectURL = "activities.jsp";
                    response.sendRedirect(redirectURL);
                }
           /* } catch (ClassNotFoundException ex) {
                String redirectURL = "ServerMaintain.jsp";
                response.sendRedirect(redirectURL);*/
            } catch (Exception ex) {
                String redirectURL = "ServerMaintain.jsp";
                response.sendRedirect(redirectURL);
            }
        %>


    </body>
</html>
