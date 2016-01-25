<%-- 
    Document   : newjsp
    Created on : Jan 23, 2016, 3:29:23 AM
    Author     : test
--%>

<%@page import="db.bean.ActivityTypeBean"%>
<%@page import="db.bean.Atype_ABean"%>
<%@page import="db.bean.DistrictBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="db.handle.DB_Select"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <%

            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
            ArrayList district_data = db_select.queryDistrict();
            ArrayList activityType = db_select.queryListAll("activityType", "");

        %>
        <title>JSP Page</title>
    </head>
    <body>

        <jsp:include page="/sourceLink.jsp"/>
        <jsp:include page="/header.jsp"/>
        <%          if (session.getAttribute("staffInfo") == null) {
                String redirectURL = "login.jsp";
                response.sendRedirect(redirectURL);
            }
        %>
        <h4> Insert Activity </h4>
        <form method="post" action="insertServlet">

            <table  align="center" class="table">

                <input type="hidden" name="table_type" value="activities") >
                <tr><td>Name</td><td><input type="text" name="name" value="" size="35"></td></tr>

                <tr><td>District No</td><td>       <select name="districtNo">
                            <%          for (int j = 0; j < district_data.size(); j++) {
                                    DistrictBean district = (DistrictBean) district_data.get(j);
                            %>

                            <option value="<%=district.getDistrictID()%>"><%=district.getName()%></option>
                            <% }%>
                        </select></td></tr>
                <tr><td>Quota</td><td><input type="text" name="quota" value="" size="35" min="0"></td></tr>


                <tr><td>TargetAgeMin</td><td><input type="range" style="width:auto;display: initial;" name="targetAgeMin" value="0" size="35"  min="3" max="99" onchange="targetAgeMin_show(this.value)"><span id ="targetAgeMin_show">0</span>
                        <script type="text/javascript">
                            function targetAgeMin_show(newValue)
                            {
                                document.getElementById("targetAgeMin_show").innerHTML = newValue;
                            }
                        </script>

                    </td></tr>
                <tr><td>TargetAgeMax</td><td>
                        <input type="range" style="width:auto;display: initial;" name="targetAgeMax"  value="0"  size="35" min="3" max="99"  onchange="targetAgeMax_show(this.value)"><span id ="targetAgeMax_show">0</span>
                        <script type="text/javascript">
                            function targetAgeMax_show(newValue)
                            {
                                document.getElementById("targetAgeMax_show").innerHTML = newValue;
                            }
                        </script>
                    </td></tr>
                <tr><td>Deadline</td><td><input type="text" name="deadline" value="" size="35" placeholder="YYYY-MM-DD HH:mm:ss"></td></tr>
                <tr><td>Venue</td><td><input type="text" name="venue" value="" size="35" ></td></tr>
                <tr><td>Date</td><td><input type="text" name="date" value="" size="35" placeholder="YYYY-MM-DD HH:mm:ss"></td></tr>
                <tr><td>Tag</td><td><input type="text" name="tag" value="" size="35"></td></tr>
                <tr><td>Description</td><td><textarea name="description" ></textarea></td></tr>
                <tr><td>Activity Type</td><td>       <select name="activityType">
                            <%

                                for (int j = 0; j < activityType.size(); j++) {
                                    ActivityTypeBean activityType_val = (ActivityTypeBean) activityType.get(j);
                            %>

                            <option value="<%=activityType_val.getActivityTypeID()%>"><%=activityType_val.getTypeName()%></option>
                            <% }

                            %>
                        </select></td></tr>                


                <tr><td>       <br><br>    <input type="submit" value="Update"></td><td></td></tr>



            </table>


            <br>





            </table>

            </body>
            </html>
