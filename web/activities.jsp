<%-- 
    Document   : newjsp
    Created on : Jan 18, 2016, 5:51:54 PM
    Author     : test
--%>

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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            $(document).ready(function () {
                $('#hover-cap-4col .thumbnail').hover(
                        function () {
                            $(this).find('.caption').slideDown(250); //.fadeIn(250)
                        },
                        function () {
                            $(this).find('.caption').slideUp(250); //.fadeOut(205)
                        }
                );
                $('#hover-cap-4col .thumbnail').click(function () {
            <%--$('#myModal').find(".modal-body").html("Added this Activity in your timetable");
            $("#myModal").modal(); --%>
                    $(".alert").css("display", "block");
                });
                $('.close').click(function () {
                    $(".alert").css("display", "none");
                });

            });
        </script>
        <style>
            .tableShow{
                float: left;
                overflow:hidden;  
                text-align: center;
            }
            input[type="number"] {
                width: 35px;
            }
            .photo{
                width:200px;
                height:200px;
                padding:  10px 10px 10px 10px;
            }
            th{
                color:blue;
                text-decoration: underline; 
                cursor:pointer;
            }
            table{
                background-color: #FF9900;
            }
            .caption {
                display: none;
                position: absolute;
                top:0;
                left: 0;
                background: rgba(0,0,0,0.4);
                width:210px;
                height: 100%;
                color:#fff !important;

            }    
            .caption>i{
                position:absolute;
                top:50%;
                left:50%;
                margin: 0 auto;
                z-index: 2000;
                transform: translate(-50%, -50%);
            }
            #hover-cap-4col .thumbnail {
                position:relative;
                overflow:hidden;
                float:left;
                cursor:pointer;
                margin-right:10px;
            }
        </style>
    </head>
    <body>

        <!-- Alert  -->
        <div class="alert alert-warning alert-dismissible fade in" role="alert" style="display:none">
            <button type="button" class="close"  >
                <span>&times;</span>
            </button>
            <strong>Activity</strong> Added this Activity in your timetable
        </div>

        <!-- Modal -->
        <div class="modal fade" id="myModal" role="dialog">
            <div class="modal-dialog -sm">
                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>   
                    </div>
                    <div class="modal-body">
                        <p>Some text in the modal.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>




        <%
            String dbUser = this.getServletContext().getInitParameter("dbUsername");
            String dbPassword = this.getServletContext().getInitParameter("dbPassword");
            String dbUrl = this.getServletContext().getInitParameter("dbUrl");
            DB_Select db_select = new DB_Select(dbUrl, dbUser, dbPassword);


        %>

        <%           
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ArrayList aryData = db_select.queryActivitiesBySql("SELECT * FROM activities");
            for (int i = 0; i < aryData.size(); i++) {
                ActivitiesBean act = (ActivitiesBean) aryData.get(i);

        %>


        <ul class="thumbnails" id="hover-cap-4col" class="tableShow" >
            <div class="thumbnail">
                <div class="caption">
                    <i class="fa fa-info fa-3x"></i>

                </div>


                <%                    String dateInString = act.getDate();
                    Date date = sdf.parse(dateInString);
                %>
                <div style="background-color:yellow;text-align:center" id="A"><img src="img/activities/<%= act.getActivitiesID()%>.jpg" class="photo" title="<%= act.getName()%>"></img>


                    <br><span style="text-align:center"><%= act.getName()%></span><br> <span style="text-align:center;color:red;font-size:large"><%= sdf.format(date)%></div>

            </div>
        </li>     
    </ul>          



    <%   }

    %>










</body>
</html>
