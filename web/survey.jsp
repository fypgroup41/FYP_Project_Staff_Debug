<%-- 
    Document   : survey
    Created on : Jan 23, 2016, 3:23:08 PM
    Author     : test
--%>

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
        <jsp:include page="/sourceLink.jsp"/>
        <jsp:include page="/header.jsp"/>
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


        %>

        <table border="1">

            <%                        ArrayList survey = db_select.queryListAll("survey", "");
                for (int j = 0; j < survey.size(); j++) {
                    SurveyBean survey_val = (SurveyBean) survey.get(j);
                    ArrayList activities = db_select.queryListAll("activities", " where activitiesID=" + survey_val.getActivitiesID());
                    ActivitiesBean activities_val = (ActivitiesBean) activities.get(0);

            %>
            <tr>
                <td><%= activities_val.getName()%></td><td>
                    
                    <a href="surveyDetail.jsp?activities_id=<%=survey_val.getActivitiesID()%>"><i class="fa fa-pencil-square-o fa-2x"></i></a></td></tr>
                <%
                    }

                %>
    </body>
</html>
