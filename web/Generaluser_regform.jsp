<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Public User Register form</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/formcss.css">
            <link rel="stylesheet"type="text/css"  href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
                <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
                    <link  href="http://code.jquery.com/ui/1.10.0/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
                    <script src="http://code.jquery.com/jquery-1.9.0.js"></script>
                    <script src="http://code.jquery.com/ui/1.10.0/jquery-ui.js"></script>
                    <link rel="stylesheet" type="text/css" href="css/formcss.css">
                        <script type="text/javascript">
                            $(function () {
                                $('.datepicker').datepicker({showAnim: "fadeIn"});
                            })
                        </script>
                        <script type="text/javascript">
                            function ValidateEmail() {
                                var email = document.getElementById("email");
                                var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
                                if (email.value.match(mailformat)) {
                                    return true;
                                } else {
                                    alert("You have entered an invalid email address!");
                                    email.focus();
                                    return false;
                                }
                            }

                            function LoadDateTime() {
                                var year = document.getElementById("year");
                                var month = document.getElementById("month");
                                var day = document.getElementById("day");

                                var option = document.createElement("option");
                                for (i = 1900; i < 2015; i++) {
                                    option.text = i + "";
                                    year.add(option);
                                }

                            }
                        </script>

                        <style>



                        </style>
                        </head>
                        <body>

                            <h1 align="center">Register Form</h1>
                            <form action="register" method="get" class="regform">
                                <h3>User Information</h3>
                                <div class="panel-group" id="accordion">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color:#E9967A">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" 
                                                   href="#collapseOne">
                                                    <center>Sign In</center>


                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseOne" class="panel-collapse collapse in">
                                            <div class="panel-body">
                                                <p>
                                                    <label for="username">Username:</label>
                                                    <input type="text" name="uname" size="12"  />

                                                    <label for="passwd">Password:</label>                                                 
                                                    <input type="password" name="passwd" size="12" /><br/>

                                                    <label for="re_passwd">Re-Type Password:</label>
                                                    <input type="password" name="re_passwd" size="12" /><br/>

                                                    <label for="surname">Surname:</label>
                                                    <input type="text" name="surname" placeholder="e.g. Chan" maxlength="30" /><br />

                                                    <label for="givenname">     Given name:</label>
                                                    <input type="text" name="givenname" placeholder="e.g. Tai Man" maxlength="20" /><br />

                                                    <label for="gender">Gender:</label>

                                                    <input type="radio" name="gender" value="m" checked />Male<br/>
                                                    <input type="radio" name="gender" value="f" />Female 

                                                    <label for="tel">Telephone Number:</label>
                                                    <input type="text" name="tel" placeholder="e.g. 91234567" maxlength="8" /><br />

                                                    <label for="email">Email Address: </label>
                                                    <input type="email" name="email" id="email" placeholder="e.g. taiman@email.com" maxlength="50" size="50" onchange="ValidateEmail()" />


                                                    <label for="chiname">Chinese Name: </label>
                                                    <input type="text" name="chiname" placeholder="e.g. Tai Man" maxlength="20" /><br />


                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="background-color:#E9967A">
                                            <h4 class="panel-title">
                                                <a data-toggle="collapse" data-parent="#accordion" 
                                                   href="#collapseTwo">
                                                    <center>   Advanced Information (Optional)</center>
                                                </a>
                                            </h4>
                                        </div>
                                        <div id="collapseTwo" class="panel-collapse collapse">
                                            <div class="panel-body">

                                               


                                                    <br />

                                                    <h3>Address</h3>
                                                    <p>
                                                        <label for="add_room">Flat/Room, Floor, Block   </label>
                                                        <input type="text" name="add_room" placeholder="e.g. Flat 401, 4/F, Block 2" size="20" />
                                                        <br />
                                                        <label for="street">Garden/Mansion/Estate, Road/Street/Avenue  </label><input type="text" name="street" size="20" placeholder="e.g. Hong Kong Estate, 1 Central Street"/>
                                                        <br />
                                                        <label for="district">District: </label><input type="text" name="district" size="10" placeholder="e.g. Central" />
                                                        <br />

                                                        <label for="location">Location</label><br/>
                                                        <select name="location">
                                                            <option selected="selected">(Please select a location)</option>
                                                            <option>Hong Kong Island</option>
                                                            <option>Kowloon</option>
                                                            <option>New Territories and Islands District</option>
                                                        </select><br/>
                                                    </p>
                                                    <h3>Parent</h3>
                                                    <p>
                                                        <label for="parent">Parent Name: </label>  <input type="text" name="parent" placeholder="e.g. Chan Tai Tai" maxlength="50" /><br />
                                                        <label for="relation">Relation:  </label>   <input type="text" name="relation" placeholder="e.g. Father" maxlength="10" /><br />
                                                        <label for="surname">Emergency Call: </label>   <input type="text" name="surname" placeholder="e.g. 94567891" maxlength="30" /><br />
                                                    </p>

                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <input type="submit" value="Submit" /> <input type="reset" value="Clear Information" />
                            </form>
                        </body>
                        </html>