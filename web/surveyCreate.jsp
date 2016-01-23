<%-- 
    Document   : newjsp
    Created on : Jan 23, 2016, 10:56:51 PM
    Author     : test
--%>

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

            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);


        %>
        <%           
            if (request.getParameter("table_type").equals("survey_create")) {
                String activities_id = null;
                activities_id = request.getParameter("activities_id");
                String sql = "INSERT INTO `survey` (`surveyID`, `activitiesID`) VALUES ('" + db_select.getAvailSurveyID() + "', '" + activities_id + "');";
                boolean isSuccess = db_select.editRecordBySql(sql);

            }
        %>
    </body>
</html>
