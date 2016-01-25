<%-- 
    Document   : newjsp
    Created on : Jan 18, 2016, 5:51:54 PM
    Author     : test
--%>


<%@page import="db.bean.StaffBean"%>
<%@page import="db.handle.DB_Select"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <jsp:include page="/sourceLink.jsp"/>


        <%
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);

            StaffBean staffInfo = null;
            if (session.getAttribute("staffInfo") == null) {
                String redirectURL = "login.jsp";
                response.sendRedirect(redirectURL);
            } else {
                staffInfo = (StaffBean) session.getAttribute("staffInfo");
            }

        %>
        <script>
            var dataSet;
            var table;
            $(document).ready(function () {
                $("#activities").addClass("active");
                $.ajax({
                    type: "POST",
                    data: "id=<%=staffInfo.getStaffID()%>",
                    url: "activity_json",
                    success: function (data) {
                        var obj = jQuery.parseJSON(data);
                        var dataSet = obj;

                        var table = $('#example').DataTable({
                            data: dataSet,
                            rowId: "Activity ID",
                            'fnCreatedRow': function (nRow, aData, iDataIndex) {
                                $(nRow).attr('id', aData[0]); // or whatever you choose to set as the id
                            },
                            columns: [
                                {title: "Activity ID"},
                                {title: "Name"},
                                {title: "districtNo"},
                                {title: "Quota."},
                                {title: "Remain"},
                                {title: "TargetAgeMax"},
                                {title: "TargetAgeMin"},
                                {title: "Deadline"},
                                {title: "Venue"},
                                {title: "Date"},
                                {title: "tag"},
                                {title: "staffID"},
                                {title: "Description"}
                            ]
                        });
                        $('#example tbody').on('click', 'tr', function () {
                            var rowData = table.row(this).data();
                            //alert(rowData[0]);
                            $(location).attr('href', '<%=getServletContext().getContextPath() + "/"%>activitiesDetail.jsp?actID=' + rowData[0]);
                            // ... do something with `rowData`
                        });
                    },
                    error: function (xhr, ajaxOptions, thrownError) {
                        alert(xhr.status);
                        alert(thrownError);
                    }
                });




            });
        </script>


        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <body>
        <jsp:include page="/header.jsp"/>
                <a href="actvitiesInsert.jsp"><i class="fa fa-plus fa-2x"></i></a><table id="example" class="display" width="100%" style="cursor: pointer"></table>

    </body>
</html>
