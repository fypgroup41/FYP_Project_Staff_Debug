<%-- 
    Document   : newjsp2
    Created on : Feb 15, 2016, 4:19:34 AM
    Author     : test
--%>




<%@page import="ict.servlet.UploadToServer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        UploadToServer a = new UploadToServer();
        int reponse = a.upLoad2Server("" + "/storage/emulated/0/DCIM/Camera/a.mp4");
        out.write("" + reponse);
        out.close();
    %>
</html>


