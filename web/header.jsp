
<%
    if (session.getAttribute("staffInfo") == null) {
        String redirectURL = "login.jsp";
        response.sendRedirect(redirectURL);
    }
%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">Welcome   <%=session.getAttribute("displayName")%></a>
        </div>
        <ul class="nav navbar-nav">

            <li id="activities"><a href="activities.jsp">Activities</a></li>
            <li><a href="survey.jsp">Survey</a></li>
            <li><a href="login?action=logout">Logout</a></li>

        </ul>
    </div>
</nav>