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

            ArrayList surveyAnswer = db_select.queryListAll("surveyAnswer", "");
            ArrayList surveyQuestion = db_select.queryListAll("surveyQuestion", "where surveyID='1'");
            ArrayList surveyqType = db_select.queryListAll("surveyqType", "");
            ArrayList survey = db_select.queryListAll("survey", "");

            String[] surveyType = new String[surveyqType.size()];


        %>
        
     
        <h4>Survey Demo - Survey ID 1</h4>
        <%                        for (int j = 0; j < surveyqType.size(); j++) {

                SurveyqTypeBean surveyQuestion_val = (SurveyqTypeBean) surveyqType.get(j);
                surveyType[j] = surveyQuestion_val.getName();

            }
        %>
        <form method="post" action="surveyReceive" >
            <input type="hidden" name="memberID" value="3">
            <%
                for (int j = 0; j < surveyQuestion.size(); j++) {

                    SurveyQuestionBean surveyQuestion_val = (SurveyQuestionBean) surveyQuestion.get(j);

                    out.println("        <br> " + (j + 1) + ". " + surveyQuestion_val.getQuestion() + "<br>");
                    if (surveyType[surveyQuestion_val.getqTypeID() - 1].equals("Short Answer")) {
            %>

            <input type="text" name="<%=surveyQuestion_val.getSqID()%>" value=""><br>
            <%
                }
                if (surveyType[surveyQuestion_val.getqTypeID() - 1].equals("True or False")) {
            %>
            <input type="radio" name="<%=surveyQuestion_val.getSqID()%>" value="true"> True <input type="radio" name="<%=surveyQuestion_val.getSqID()%>" value="false">False<br>
            <%
                }
                if (surveyType[surveyQuestion_val.getqTypeID() - 1].equals("Multiple Choice")) {

                    int index = surveyQuestion_val.getQuestion().indexOf("?");
                    String item = surveyQuestion_val.getQuestion().substring(index + 1, surveyQuestion_val.getQuestion().length());
                    String[] item_ary = item.split(";");
            %>
            <%
                for (int m = 0; m < item_ary.length; m++) {


            %>

            <input type="checkbox" name="<%=surveyQuestion_val.getSqID()%>" value="<%=item_ary[m]%>"><%=item_ary[m]%>
            <%                }%>

            <%
                }
                if (surveyType[surveyQuestion_val.getqTypeID() - 1].equals("Rating")) {
            %>
            <select  name="<%=surveyQuestion_val.getSqID()%>" >
                <%
                    for (int k = surveyQuestion_val.getMinMark(); k <= surveyQuestion_val.getMaxMark(); k++) {
                %>
                <option value="<%=k%>"><%=k%></option>
                <%                    }
                %>

            </select><br>



            <%
                    }

                }

            %>
            <br><br>
         
        </form>
    </body>
</html>
