

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="main.jsp">WebSiteName</a>
        </div>
        <ul class="nav navbar-nav">

            <li id="activities"><a href="activities.jsp">Activities</a></li>
            <li><a href="survey.jsp">Survey</a></li>
            <li><a href="#">Personal Setting</a></li>
            <li><a href="login?action=logout">Logout</a></li>
                <%
                    if (session.getAttribute("staffInfo") == null) {
                        String redirectURL = "login.jsp";
                        response.sendRedirect(redirectURL);
                    } else {
                        out.println("<li><a href='#'>" + session.getAttribute("displayName") + "</a></li>");
                    }
                %>
        </ul>
    </div>
</nav>