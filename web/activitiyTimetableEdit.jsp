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
        <h4> Update Activity TimeTable - Activity TimeTable ID : <%= request.getParameter("timetable_id")%></h4>
        <%              double budget = 0;
            ArrayList activityTimetable = db_select.queryListAll("activityTimetable", " where activityTimeTableID='" + request.getParameter("timetable_id") + "'");
            for (int j = 0; j < activityTimetable.size(); j++) {
                ActivityTimetableBean activityTimetable_val = (ActivityTimetableBean) activityTimetable.get(j);
        %>        
        <table border="1" class="table" >
            <form method="post" action="updateServlet">

                <input type="hidden" name="table_type" value="activityTimetable" >
                <input type="hidden" name="activityTimeTableID" value="<%=activityTimetable_val.getActivityTimeTableID()%>">
                <input type="hidden" name="activities_id" value="<%=activityTimetable_val.getActivitiesID()%>">
                <tr><td>Activity TimeTable ID</td><td><%=activityTimetable_val.getActivityTimeTableID()%></td></tr>
                <tr><td>ActivitiesID</td><td><input type="text" name="activitiesID" value="<%=activityTimetable_val.getActivityTimeTableID()%>"></td></tr>
                <tr><td>Detail</td><td><input type="text" name="detail" value="<%=activityTimetable_val.getDetail()%>"></td></tr>
                <tr><td>Start Time</td><td><input type="text" name="startTime" value="<%=activityTimetable_val.getFinishTime()%>" placeholder="YYYY-MM-DD HH:mm:ss" ></td></tr>
                <tr><td>Finish Time</td><td><input type="text" name="finishTime" value="<%=activityTimetable_val.getStartTime()%>" placeholder="YYYY-MM-DD HH:mm:ss"></td></tr>

                </td>
                </tr>


                <%
                    }
                %>



        </table>
        <input type="submit" value="Update">
    </form>
</body>
</html>

