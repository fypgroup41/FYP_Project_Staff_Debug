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

        <table border="1" class="table">
            <tr><th>TimeTable ID</th><th>Description</th><th>Start Time</th><th>End Time </th><th></th></tr>
                    <%                        double budget = 0;
                        ArrayList activityTimetable = db_select.queryListAll("activityTimetable", " where activitiesID='" + request.getParameter("activities_id") + "' order by startTime");
                        for (int j = 0; j < activityTimetable.size(); j++) {
                            ActivityTimetableBean activityTimetable_val = (ActivityTimetableBean) activityTimetable.get(j);
                    %>
            <tr>
                <td><%=activityTimetable_val.getActivityTimeTableID()%></td>
                <td><%=activityTimetable_val.getDetail()%></td>
                <td><%=activityTimetable_val.getStartTime()%></td>
                <td><%=activityTimetable_val.getFinishTime()%></td>
                <td>
                    <a href="activitiyTimetableEdit.jsp?timetable_id=<%=activityTimetable_val.getActivityTimeTableID()%>">Edit</a>
                    <a href="deleteServlet?table_type=activityTimetable&activities_id=<%=request.getParameter("activities_id")%>&timetable_id=<%=activityTimetable_val.getActivityTimeTableID()%>">Delete</a>


                </td>


                </td>
            </tr>


            <%
                }
            %>


        </table>

        <a href="activitiyTimetableInsert.jsp?activities_id=<%=request.getParameter("activities_id")%>">Insert</a>
    </body>
</html>

