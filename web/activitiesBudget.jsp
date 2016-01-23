<%-- 
    Document   : newjsp
    Created on : Jan 18, 2016, 5:51:54 PM
    Author     : test
--%>

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
                <jsp:include page="/header.jsp"/>
        <%
            if (session.getAttribute("staffInfo") == null) {
                String redirectURL = "login.jsp";
                response.sendRedirect(redirectURL);
            }
        %>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <body>

        <%
            DateCalculate dc = new DateCalculate();
            SimpleDateFormat sdfbd = new SimpleDateFormat("yyyy-MM-dd");
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
            //ArrayList activityBudget = db_select.queryListAll("activityBudget", "activitiesID='" + request.getParameter("actID") + "'");


        %>
        <a href="activitiesBudgetInsert.jsp?activities_id=<%=request.getParameter("activities_id")%>">Insert</a>
        <table border="1">
            <tr><th>Item ID</th><th>Item Name</th><th>Item Type</th><th>Cost </th><th>Qty</th><th>Remark</th></tr>
                    <%
                        double budget = 0;
                        ArrayList activityBudget = db_select.queryListAll("activityBudget", " where activitiesID='" + request.getParameter("activities_id") + "'");
                        for (int j = 0; j < activityBudget.size(); j++) {
                            ActivityBudgetBean activityBudget_val = (ActivityBudgetBean) activityBudget.get(j);
                    %>

            <tr>

                <td><%=activityBudget_val.getItemID()%></td>
                <td><%=activityBudget_val.getItemName()%></td>
                <td><%=activityBudget_val.getItemType()%></td>
                <td>$ <%=activityBudget_val.getCost()%></td>
                <td><%=activityBudget_val.getNumber()%></td>
                <td><%=activityBudget_val.getRemark()%></td>
                <%
                    budget += activityBudget_val.getCost() * activityBudget_val.getNumber();
                %>
                <td>$ <%=activityBudget_val.getCost() * activityBudget_val.getNumber()%></td>
                <td><a href="budgetEdit.jsp?item_id=<%=activityBudget_val.getItemID()%>&activities_id=<%=request.getParameter("activities_id")%>">Edit</a></td>
                <td><a href="deleteServlet?table_type=activityBudget&item_id=<%=activityBudget_val.getItemID()%>&activities_id=<%=request.getParameter("activities_id")%>">Delete</a>

                </td>
            </tr>


            <%
                }
            %>
            <tr><td></td><td></td><td></td><td></td><td></td><td></td><td>  $ <%=budget%></td></tr>


        </table>

    </body>
</html>

