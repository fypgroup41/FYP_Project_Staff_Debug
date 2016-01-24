<%-- 
    Document   : requestSuccess
    Created on : Jan 23, 2016, 1:24:18 AM
    Author     : test
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="refresh" content="2;url=<%=request.getAttribute("link")%>" />
        <title>JSP Page</title>
    </head>
    <body>
        <%=request.getAttribute("status")%>
        <br>
        Return the main page in 2  second
    </body>
</html>
