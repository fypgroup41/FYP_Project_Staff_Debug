<%-- 
    Document   : newjsp
    Created on : Jan 18, 2016, 5:51:54 PM
    Author     : test
--%>

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



        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <body>
        <jsp:include page="/sourceLink.jsp"/>
        <jsp:include page="/header.jsp"/>
        <%
            if (session.getAttribute("staffInfo") == null) {
                String redirectURL = "login.jsp";
                response.sendRedirect(redirectURL);
            }
        %>

        <%   String activities_id_temp = null;
            DateCalculate dc = new DateCalculate();
            SimpleDateFormat sdfbd = new SimpleDateFormat("yyyy-MM-dd");
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);
            ArrayList district_data = db_select.queryDistrict();
            ArrayList activityType = db_select.queryListAll("activityType", "");
            ArrayList activityTypeActivities = db_select.queryListAll("atype_A", " where activitiesID='" + "1" + "'");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ArrayList aryData = db_select.queryListAll("activities", " where activitiesID=\"" + "1" + "\"");
            for (int i = 0; i < aryData.size(); i++) {
                ActivitiesBean act = (ActivitiesBean) aryData.get(i);
                String dateInString = act.getDate();
                Date date = sdf.parse(dateInString);
                activities_id_temp = act.getActivitiesID();%>
        <br>
        <h2>Activities ID : 1 </h2>
        <table  align="center"class="table" >
            <tr><td>ActivitiesID</td><td><%=act.getActivitiesID()%></td></tr>
            <tr><td>Name</td><td><%=act.getName()%></td></tr>

            <tr><td>districtNo</td><td>     
                    <%          for (int j = 0; j < district_data.size(); j++) {
                            DistrictBean district = (DistrictBean) district_data.get(j);
                    %>
                    <% if (act.getDistrictNo().toString().equals(district.getDistrictID().toString())) {%>
                    <%=district.getName()%>
                    <%   activities_id_temp = act.getActivitiesID();%>

                    <% } else {%>

                    <%   }
                        }

                    %>
                </td></tr>
            <tr><td>quota</td><td><%=act.getQuota()%></td></tr>

            <tr><td>targetAgeMax</td><td>
                    <%=act.getTargetAgeMax()%>
                </td></tr>
            <tr><td>targetAgeMin</td><td><%=act.getTargetAgeMin()%>
                </td></tr>
            <tr><td>deadline</td><td><%=act.getDeadline()%></td></tr>
            <tr><td>venue</td><td><%=act.getVenue()%></td></tr>
            <tr><td>date</td><td><%=act.getDate()%></td></tr>
            <tr><td>tag</td><td><%=act.getTag()%></td></tr>
            <tr><td>description</td><td><%=act.getDescription()%></td></tr>
            <tr><td>districtNo</td><td>   


                    <%
                        Atype_ABean activityTypeActivities_val = (Atype_ABean) activityTypeActivities.get(0);
                        for (int j = 0; j < activityType.size(); j++) {
                            ActivityTypeBean activityType_val = (ActivityTypeBean) activityType.get(j);
                    %>
                    <% if (!activityType_val.getActivityTypeID().toString().equals(activityTypeActivities_val.getActivityTypeID().toString())) {%>
                    <% } else {%>

                    <%=activityType_val.getTypeName()%>
                    <%   }
                        }

                    %>
                </td></tr>                






        </table>


        <br>







        <%   }

        %>



        <h2>Activities ID : 2 </h2>
        <%              ArrayList district_data2 = db_select.queryDistrict();
            ArrayList activityType2 = db_select.queryListAll("activityType", "");
            ArrayList activityTypeActivities2 = db_select.queryListAll("atype_A", " where activitiesID='" + "2" + "'");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ArrayList aryData2 = db_select.queryListAll("activities", " where activitiesID=\"" + "2" + "\"");
            for (int i = 0; i < aryData2.size(); i++) {
                ActivitiesBean act = (ActivitiesBean) aryData2.get(i);
                String dateInString = act.getDate();
                Date date = sdf2.parse(dateInString);
                activities_id_temp = act.getActivitiesID();%>
        <br>


        <table  align="center"class="table" >
            <tr><td>ActivitiesID</td><td><%=act.getActivitiesID()%></td></tr>
            <tr><td>Name</td><td><%=act.getName()%></td></tr>

            <tr><td>districtNo</td><td>     
                    <%          for (int j = 0; j < district_data2.size(); j++) {
                            DistrictBean district = (DistrictBean) district_data2.get(j);
                    %>
                    <% if (act.getDistrictNo().toString().equals(district.getDistrictID().toString())) {%>
                    <%=district.getName()%>
                    <%   activities_id_temp = act.getActivitiesID();%>

                    <% } else {%>

                    <%   }
                        }

                    %>
                </td></tr>
            <tr><td>quota</td><td><%=act.getQuota()%></td></tr>

            <tr><td>targetAgeMax</td><td>
                    <%=act.getTargetAgeMax()%>
                </td></tr>
            <tr><td>targetAgeMin</td><td><%=act.getTargetAgeMin()%>
                </td></tr>
            <tr><td>deadline</td><td><%=act.getDeadline()%></td></tr>
            <tr><td>venue</td><td><%=act.getVenue()%></td></tr>
            <tr><td>date</td><td><%=act.getDate()%></td></tr>
            <tr><td>tag</td><td><%=act.getTag()%></td></tr>
            <tr><td>description</td><td><%=act.getDescription()%></td></tr>
            <tr><td>districtNo</td><td>   


                    <%
                        Atype_ABean activityTypeActivities_val = (Atype_ABean) activityTypeActivities2.get(0);
                        for (int j = 0; j < activityType2.size(); j++) {
                            ActivityTypeBean activityType_val = (ActivityTypeBean) activityType2.get(j);
                    %>
                    <% if (!activityType_val.getActivityTypeID().toString().equals(activityTypeActivities_val.getActivityTypeID().toString())) {%>
                    <% } else {%>

                    <%=activityType_val.getTypeName()%>
                    <%   }
                        }

                    %>
                </td></tr>                






        </table>


        <br>







        <%   }

        %>