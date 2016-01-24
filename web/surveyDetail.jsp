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

        <%            String survey_id = null;
            ArrayList survey_ary = db_select.queryListAll("survey", " where activitiesID= " + activities_id);
            if (survey_ary != null) {
                SurveyBean survey_ary_val = (SurveyBean) survey_ary.get(0);
                survey_id = survey_ary_val.getSurveyID();
            }
            for (int j = 0; j < surveyqType.size(); j++) {
                SurveyqTypeBean surveyQuestion_val = (SurveyqTypeBean) surveyqType.get(j);
                surveyType[j] = surveyQuestion_val.getName();
            }
        %>


        <%
            ArrayList surveyQuestion = db_select.queryListAll("surveyQuestion", " where surveyID='" + survey_id + "'");

        %>
        <table border="1">
            <tr><th>Question</th><th>Question Type</th></tr>


            <form method="post" action="updateServlet" >
                <input type="hidden" name="table_type" value="survey">
                <input type="hidden" name="activities_id" value="<%=activities_id%>">
                <input type="hidden" name="survey_id" value="<%=survey_id%>">
                <tr><%
                    for (int j = 0; j < surveyQuestion.size(); j++) {
                        SurveyQuestionBean surveyQuestion_val = (SurveyQuestionBean) surveyQuestion.get(j);%>
                    <td><input type="text" name="question_<%=surveyQuestion_val.getSqID()%>" value="<%=surveyQuestion_val.getQuestion()%>"></td>

                    <td> 
                        <select name="qTypeID_<%=surveyQuestion_val.getSqID()%>" >
                            <%   for (int n = 0; n < surveyType.length; n++) {%>


                            <%
                                if (((surveyQuestion_val.getqTypeID() - 1) == n)) {
                            %>
                            <option value="<%=(n + 1)%>"selected ><%=surveyType[n]%></option>               
                            <%
                            } else {%>
                            <option value="<%=(n + 1)%>"><%=surveyType[n]%></option>               
                            <%}
                                }%>
                        </select> 
                    </td><td>
                        <a href="deleteServlet?table_type=survey&sqID=<%=surveyQuestion_val.getSqID()%>"><i class="fa fa-trash fa-2x"></i></a>
                    </td></tr>
                </td></tr>
                <%}
                %>
        </table>
        <input type="submit" value="Update">


    </form>
    <a href="surveyInsert.jsp?survey_id=<%=survey_id%>"><i class="fa fa-plus fa-2x"></i></a>

</body>
</html>
