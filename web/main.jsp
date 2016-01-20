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
                $("#login").click(function () {
                    $("#closeButton").css("visibility", "visible");
                    $("#indexLog").css("visibility", "visible");
                    $("#closeButton").load("login.jsp");

                });
                $("#register").click(function () {
                    $("#closeButton").css("visibility", "visible");
                    $("#indexLog").css("visibility", "visible");
                });
                $("#close").click(function () {
                    $("#closeButton").css("visibility", "hidden");
                    $("#indexLog").css("visibility", "hidden");
                });
                $("#register").click(function () {
                    $("#contentPage").load("Generaluser_regform.jsp");
                });

                $("#activities").click(function () {
                    $("#contentPage").load("activities.jsp");
                });


            });
        </script>
        <%--    <script type="text/javascript">
                function enableOverLay() {
                    var target = document.getElementById("indexLog");
                    target.style.visibility = "visible";
                    var target2 = document.getElementById("closeButton");
                    target2.style.visibility = "visible";
                }

            function disableOverLat() {
                var target = document.getElementById("indexLog");
                target.style.visibility = "hidden";
                var target2 = document.getElementById("closeButton");
                target2.style.visibility = "hidden";
            }
        </script>--%>
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

        <div class="row" style="height:100%">

            <div class="col-1 menu">
                <ul>
                    <li id="activities" style="cursor: pointer"><center>Activities</center></li>
                    <li id="news"><center>News</center></li>
                    <li id="wall"><center>Sharing Wall</center></li>
                </ul>
            </div>

            <div class="col-9" id="contentPage" style="background-color: Cornsilk  ;height:100%;overflow: scroll">
                <div class="content">

                    <h1>Main Content</h1>
                    <p>Maybe set iframe to change content</p>


                </div>
            </div>
            <div class="col-2 right">
                <div class="aside">
                    <center>Sharing on Swimming Lessons (2015-11-12)</center>
                    <center> <i class="fa fa-camera-retro fa-5x"></i></center>
                    <div>Jade Art hold a Swimming Lesson on 2015-11-15.Most of the participats learn how to swimming..........<a href="">Click here</a> </div>


                    <hr>
                    <center>Sharing on Swimming Lessons (2015-11-12)</center>
                    <center> <i class="fa fa-camera-retro fa-5x"></i></center>
                    <div>Jade Art hold a Swimming Lesson on 2015-11-15.Most of the participats learn how to swimming..........<a href="">Click here</a> </div>

                    <hr>
                    <center>Sharing on Swimming Lessons (2015-11-12)</center>
                    <center> <i class="fa fa-camera-retro fa-5x"></i></center>
                    <div>Jade Art hold a Swimming Lesson on 2015-11-15.Most of the participats learn how to swimming..........<a href="">Click here</a> </div>

                </div>
            </div>
        </div>

        <div id="footer" >
        </div>
        <div id="closeButton">

        </div>


        <jsp:include page="/footer.jsp"/>
    </body>
</html>
