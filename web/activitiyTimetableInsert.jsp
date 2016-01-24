<%-- 
    Document   : newjsp
    Created on : Jan 18, 2016, 5:51:54 PM
    Author     : test
--%>

<%@page import="db.bean.ActivityTimetableBean"%>
<%@page import="db.bean.ActivityBudgetBean"%>
<%@page import="ict.caculate.DateCalculate"%>
<%@page import="db.bean.MemberBean"%>
<%@page import="db.bean.ActivitiesRecordBean"%>
<%@page import="db.bean.Atype_ABean"%>
<%@page import="db.bean.ActivityTypeBean"%>
<%@page import="db.bean.DistrictBean"%>
<%@page import="db.bean.UserBean"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Random"%>
<%@page import="db.bean.ActivitiesBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.handle.DB_Select"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/sourceLink.jsp"/>



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            DateCalculate dc = new DateCalculate();
            SimpleDateFormat sdfbd = new SimpleDateFormat("yyyy-MM-dd");
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
            //ArrayList activityBudget = db_select.queryListAll("activityBudget", "activitiesID='" + request.getParameter("actID") + "'");


        %>


        <form method="post" action="insertServlet">
            <input type="hidden" name="activities_id" value="<%=request.getParameter("activities_id")%>">
            <table border="1">
                <input type="hidden" name="table_type" value="activityTimetable" >                          
                <tr><td>detail</td><td><input type="text" name="detail" value=""></td></tr>
                <tr><td>startTime</td><td><input type="text" name="startTime" value=""></td></tr>
                <tr><td>finish</td><td><input type="text" name="finishTime" value=""></td></tr>
                </td>
                </tr>
            </table>
            <input type="submit" value="Update">
        </form>
    </body>
</html>

