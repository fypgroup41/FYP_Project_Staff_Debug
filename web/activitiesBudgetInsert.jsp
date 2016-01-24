<%-- 
    Document   : budgetEdit
    Created on : Jan 23, 2016, 2:55:04 AM
    Author     : test
--%>

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



        <form method="post" action="insertServlet">
            <input type="hidden" name="activities_id" value="<%=request.getParameter("activities_id")%>">
            <input type="hidden" name="table_type" value="activityBudget">



            <table class="table">


                <tr><td>ActivitiesID</td><td><%=request.getParameter("activities_id")%></td></tr>
                <tr><td>ItemName</td><td>   <input type="text" name="itemName"  value=""><br></td></tr>
                <tr><td>ItemType</td><td>  <input type="text" name="itemType"  value=""><br></td></tr>
                <tr><td>Cost $</td><td> <input type="text" name="cost"  value=""><br></td></tr>
                <tr><td>Qty</td><td>  <input type="text" name="number"  value=""><br></td></tr>
                <tr><td>Remark</td><td><input type="text" name="remark"  value=""><br></td></tr>
            </table>
            <input type="submit" value="Update">
            <br>
        </form>

    </body>
</html>
