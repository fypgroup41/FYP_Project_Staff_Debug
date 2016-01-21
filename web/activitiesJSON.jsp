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

        <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.10/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.10/css/jquery.dataTables.min.css">
        <script>
            $(document).ready(function () {
                $.ajax({
                    type: "POST",
                    url: "activity_json",
                    success: function (data) {
                        var obj = jQuery.parseJSON(data);
                        var dataSet = obj;
                        $('#example').DataTable({
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
    <body >
        <table id="example" class="display" width="100%"></table>
        
    </body>
</html>
