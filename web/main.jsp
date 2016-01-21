<%@page import="db.handle.DB_Select"%>
<%@page import="db.bean.UserBean"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ taglib uri="/WEB-INF/tlds/ict-taglib2.tld" prefix="ict2" %>


        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="css/formcss.css">

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
        <style>
            li {
                cursor: pointer;

            }

        </style>
        <script>
            var dataSet;
            var table;
            $(document).ready(function () {



            <%--    $("#AAA").load("<%=getServletContext().getContextPath() + "/"%>activities.jsp");--%>

                $("#activity").click(function () {



                    $("#home").click(function () {
                        $("#content").load("<%=getServletContext().getContextPath() + "/"%>activities.jsp");
                    });
                    $("#community_center").click(function () {
                        $("#content").load("<%=getServletContext().getContextPath() + "/"%>activities.jsp");
                    });
                    $("#survey").click(function () {
                        $("#content").load("<%=getServletContext().getContextPath() + "/"%>activities.jsp");
                    });
                    $("#staff_info").click(function () {
                        $("#content").load("<%=getServletContext().getContextPath() + "/"%>activities.jsp");
                    });
                    $("#content").html("<table id=\"example\" class=\"display\" width=\"100%\"></table> ");
                    $.ajax({
                        type: "POST",
                        url: "activity_json",
                        data: "id=<%="3"%>",
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
                                    {title: "quota."},
                                    {title: "remain"},
                                    {title: "targetAgeMax"},
                                    {title: "targetAgeMin"},
                                    {title: "deadline"},
                                    {title: "venue"},
                                    {title: "date"},
                                    {title: "tag"},
                                    {title: "staffID"},
                                    {title: "sqID"},
                                    {title: "Description"}
                                ]
                            });
                            $('#example tbody').on('click', 'tr', function () {
                                var rowData = table.row(this).data();
                                alert(rowData[0]);
                                // ... do something with `rowData`
                            });

                        },
                        error: function (xhr, ajaxOptions, thrownError) {
                            alert(xhr.status);
                            alert(thrownError);
                        }

                    });
                });
            });
        </script>
    </head>
    <body style="width:100%;height:100%">
        <div style="width:100%;height:100%;background-color: yellow">

            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <div class="navbar-brand" >Staff Manage</div>
                    </div>
                    <ul class="nav navbar-nav">
                        <li class="navItem" id="home" class="active">Home</li>
                        <li class="navItem" id="activity">Activities</li>
                        <li class="navItem" id="community_center">Community Center</li>
                        <li class="navItem" id="survey">Survey</li>
                        <li class="navItem" id="staff_info">Personal Setting</li>
                    </ul>
                </div>
            </nav>

            <div id="content" style="width:100%;height:100%;background-color: green">

            </div>

        </div>
        <jsp:include page="/footer.jsp"/>
    </body>
</html>
