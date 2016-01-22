<%-- 
    Document   : newjsp
    Created on : Jan 18, 2016, 5:51:54 PM
    Author     : test
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/sourceLink.jsp"/>

        <script>
            var dataSet;
            var table;
            $(document).ready(function () {
                $("#activities").addClass("active");
                $.ajax({
                    type: "POST",
                    data: "id=<%="2"%>",
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
                                {title: "quota."},
                                {title: "remain"},
                                {title: "targetAgeMax"},
                                {title: "targetAgeMin"},
                                {title: "deadline"},
                                {title: "venue"},
                                {title: "date"},
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
        <table id="example" class="display" width="100%"></table>

    </body>
</html>
