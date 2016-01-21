<%-- 
    Document   : newjsp
    Created on : Jan 18, 2016, 5:51:54 PM
    Author     : test
--%>

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
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">



        <script>
            $(document).ready(function () {

                $("#button_login").click(function () {
                    var dataVal = $.ajax({
                        type: "POST",
                        url: "activity_json",
                        data: "username=" + $("#username").val() + "&password=" + $("#password").val(),
                        success: function (data) {
                            var obj = jQuery.parseJSON(data);
                            if (obj.status === "success") {
                                $("#user_status").html("<i class=\"fa fa-user\"></i>" + obj.username);
                            } else {
                                $("#user_status").html("User name or password is incorrect");
                            }

                            $("#loginForm").css("visibility", "hidden");

                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            alert(xhr.status);
                            alert(thrownError);
                        }

                    });



                });

            });
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <body>
        <a href="activities.jsp">Activities</a>
        <a href="community_center.jsp">Community Center</a>
        <a href="survey.jsp">Survey</a>
        <a href="staff_info.jsp">Personal Setting</a>



        <%
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
        %>


        <%            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            UserBean user = (UserBean) session.getAttribute("userInfo");
            ArrayList aryData = db_select.queryActivitiesBySql("SELECT * FROM activities where staffID=\"" + "2" + "\"");
            for (int i = 0; i < aryData.size(); i++) {
                ActivitiesBean act = (ActivitiesBean) aryData.get(i);
        %>



        <%                    String dateInString = act.getDate();
            Date date = sdf.parse(dateInString);
        %>
        <br>



        <br><span style="text-align:center"><%= act.getName()%></span> <span><%= sdf.format(date)%></div>





        <%   }

        %>










</body>
</html>
