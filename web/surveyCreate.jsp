<%-- 
    Document   : newjsp
    Created on : Jan 23, 2016, 10:56:51 PM
    Author     : test
--%>

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
        <h4> Create Survey - Activities ID : <%=request.getParameter("activities_id")%> </h4>
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


        %>
        <%            if (request.getParameter("table_type").equals("survey_create")) {
                String activities_id = null;
                activities_id = request.getParameter("activities_id");
                String sql = "INSERT INTO `survey` (`surveyID`, `activitiesID`) VALUES ('" + db_select.getAvailSurveyID() + "', '" + activities_id + "');";
                boolean check = db_select.editRecordBySql(sql);
                if (check == true) {
                    request.setAttribute("status", "Create Survey Success");
                    request.setAttribute("link", "activities.jsp");
                    RequestDispatcher rd
                            = request.getServletContext().getRequestDispatcher("/requestSuccess.jsp");
                    rd.forward(request, response);
                }
            }
        %>
    </body>
</html>
