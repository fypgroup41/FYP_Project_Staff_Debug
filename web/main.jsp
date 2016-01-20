<%@page import="db.handle.DB_Select"%>
<%@page import="db.bean.UserBean"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="/WEB-INF/tlds/ict-taglib2.tld" prefix="ict2" %>

        <%
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);


        %>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/formcss.css">

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
        <script>
            $(document).ready(function () {
                
                $("#AAA").load("activities.jsp");
            });
        </script>

    </head>
    <body >



        <%            if (request.getAttribute("status") != null) {
                if (request.getAttribute("status").equals("fail")) {
        %>     

        <script>
            alert("User name or password is incorrect");
            document.getElementById("closeButton").style.visibility = "visible";
            document.getElementById("indexLog").style.visibility = "visible";
            document.getElementById("closeButton").style.visibility = "visible";
        </script>
        <%
                }
            }
        %>
        
        
        <div class="header"  style="background-color: green">
            <div id="Left" style="display:initine">
                <img src="img/logo.jpg" style="width:100px;height:100px;cursor: pointer"  onclick="window.location.href = '<%=getServletContext().getContextPath() + "/"%>main.jsp'">


                <div id="user_status"></div>

            </div>
            <div id="Center">
            </div>
            <%
                if (session.getAttribute("userInfo") == null) {%>

            <div id="Right" style="position:absolute;top:5px;right:0px">
                <button  id="login" >Login</button>
                <button id="register">Register</button>
            </div>
            <%

            } else {
            %>

            <div id="Right" style="position:absolute;top:5px;right:0px">
                <i class="fa fa-user fa-2x"  style="cursor:pointer" >
                    <%
                        if (session.getAttribute("userInfo") != null) {
                            UserBean user = (UserBean) session.getAttribute("userInfo");
                            out.println("" + session.getAttribute("userName"));
                        }
                    %>
                    <i class="fa fa-sign-out fa-2x" style="cursor:pointer" onclick="window.location.href = '<%=getServletContext().getContextPath() + "/"%>login?action=logout'"></i>
                </i>




            </div>

            <%
                }
            %>


        </div>
            <div id="AAA">
                
                
                
            </div>
        
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
