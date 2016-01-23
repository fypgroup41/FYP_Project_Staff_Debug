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

        <jsp:include page="/header.jsp"/>
        <%
            DateCalculate dc = new DateCalculate();
            SimpleDateFormat sdfbd = new SimpleDateFormat("yyyy-MM-dd");
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
            //ArrayList activityBudget = db_select.queryListAll("activityBudget", "activitiesID='" + request.getParameter("actID") + "'");


        %>

        <%              double budget = 0;
            ArrayList activityTimetable = db_select.queryListAll("activityTimetable", " where activityTimeTableID='" + request.getParameter("timetable_id") + "'");
            for (int j = 0; j < activityTimetable.size(); j++) {
                ActivityTimetableBean activityTimetable_val = (ActivityTimetableBean) activityTimetable.get(j);
        %>        
        <form method="post" action="updateServlet">
            <table border="1">
                <input type="hidden" name="table_type" value="activityTimetable") >
                <input type="text" name="activityTimeTableID" value="<%=activityTimetable_val.getActivityTimeTableID()%>">
                <input type="text" name="activities_id" value="<%=activityTimetable_val.getActivities_activitiesID()%>">
                <tr><td>activityTimeTableID</td><td><%=activityTimetable_val.getActivityTimeTableID()%></td></tr>
                <tr><td>activitiesID</td><td><input type="text" name="activitiesID" value="<%=activityTimetable_val.getActivityTimeTableID()%>"></td></tr>
                <tr><td>detail</td><td><input type="text" name="detail" value="<%=activityTimetable_val.getDetail()%>"></td></tr>
                <tr><td>startTime</td><td><input type="text" name="startTime" value="<%=activityTimetable_val.getFinish()%>"></td></tr>
                <tr><td>finish</td><td><input type="text" name="finish" value="<%=activityTimetable_val.getStartTime()%>"></td></tr>




                </td>
                </tr>


                <%
                    }
                %>

                <a href="">Insert</a>

            </table>
            <input type="submit" value="Update">
        </form>
    </body>
</html>

