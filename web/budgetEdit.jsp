<%-- 
    Document   : budgetEdit
    Created on : Jan 23, 2016, 2:55:04 AM
    Author     : test
--%>

<%@page import="db.bean.ActivityBudgetBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.handle.DB_Select"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>



    <%

        String dbUser = this.getServletContext().getInitParameter("dbUsername");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
        String item_id = request.getParameter("item_id");
        ArrayList activityBudget = db_select.queryListAll("activityBudget", " where itemID='" + request.getParameter("item_id") + "'");

        for (int i = 0; i < activityBudget.size(); i++) {
            ActivityBudgetBean activityBudget_val = (ActivityBudgetBean) activityBudget.get(i);
    %>
    <form method="post" action="updateServlet">


        <input type="hidden" name="activities_id" value="<%=request.getParameter("activities_id")%>">
        <input type="hidden" name="table_type" value="activityBudget">
        <input type="hidden" name="item_id" value="<%=activityBudget_val.getItemID()%>">




        <table>


            <tr><td>ActivitiesID</td><td><%=activityBudget_val.getActivitiesID()%></td></tr>
            <tr><td>ItemID</td><td><%=activityBudget_val.getItemID()%></td></tr>
            <tr><td>ItemName</td><td>   <input type="text" name="itemName"  value="<%=activityBudget_val.getItemName()%>"><br></td></tr>
            <tr><td>ItemType</td><td>  <input type="text" name="itemType"  value="<%=activityBudget_val.getItemType()%>"><br></td></tr>
            <tr><td>Cost $</td><td> <input type="text" name="cost"  value="<%=activityBudget_val.getCost()%>"><br></td></tr>
            <tr><td>Qty</td><td>  <input type="text" name="number"  value="<%=activityBudget_val.getNumber()%>"><br></td></tr>
            <tr><td>Remark</td><td><input type="text" name="remark"  value="<%=activityBudget_val.getRemark()%>"><br></td></tr>
        </table>
        <input type="submit" value="Update">
        <br>
    </form>


    <%        }

    %>
</html>
