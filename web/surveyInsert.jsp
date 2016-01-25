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
        <jsp:include page="/sourceLink.jsp"/>
        <jsp:include page="/header.jsp"/>
        <h4> Insert Survey  Question - Survey ID : <%=request.getParameter("survey_id")%> </h4>
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

            ArrayList surveyAnswer = db_select.queryListAll("surveyAnswer", "");
            ArrayList surveyQuestion = db_select.queryListAll("surveyQuestion", "");
            ArrayList surveyqType = db_select.queryListAll("surveyqType", "");
            ArrayList survey = db_select.queryListAll("survey", "");

            String[] surveyType = new String[surveyqType.size()];


        %>  

        <%                        for (int j = 0; j < surveyqType.size(); j++) {

                SurveyqTypeBean surveyQuestion_val = (SurveyqTypeBean) surveyqType.get(j);
                surveyType[j] = surveyQuestion_val.getName();

            }
        %>


        <form method="post" action="insertServlet" >


            <input type="hidden" name="table_type" value="survey">
            <input type="hidden" name="survey_id" value="<%=request.getParameter("survey_id")%>">
            <table class="table">
                <tr><td>Question</td><td>            <input type="text"name="question"></td></tr>
                <tr><td>Question Type</td><td>
                        <select name="qTypeID" >
                            <%   for (int n = 0; n < surveyType.length; n++) {%>
                            <option value="<%=(n + 1)%>"><%=surveyType[n]%></option>               
                            <%
                                }%>
                        </select> 
                    </td></tr>
            </table>


            <br>
            <input type="submit" value="Update">
        </form>

    </body>
</html>
