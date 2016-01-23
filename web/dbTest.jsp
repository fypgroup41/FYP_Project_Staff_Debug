<%-- 
    Document   : survey
    Created on : Jan 23, 2016, 3:23:08 PM
    Author     : test
--%>

<%@page import="db.bean.SurveyQuestionBean"%>
<%@page import="db.bean.SurveyqTypeBean"%>
<%@page import="db.bean.ActivitiesBean"%>
<%@page import="db.bean.UserBean"%>
<%@page import="db.bean.SurveyBean"%>
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
            }
        %>
        <%
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
            ArrayList surveyqType = db_select.queryListAll("surveyqType", "");

            String activities_id = request.getParameter("activities_id");
            String[] surveyType = new String[surveyqType.size()];


        %>  




        <%            ArrayList surveyQuestion = db_select.queryListAll("surveyQuestion", " where survey_id='" + "1" + "'");
            out.print(surveyQuestion.size() + "A");
        %>


    </body>
</html>
