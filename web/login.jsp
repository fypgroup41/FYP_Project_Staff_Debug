
<script>

    $("#close").click(function () {
        $("#closeButton").css("visibility", "hidden");
        $("#indexLog").css("visibility", "hidden");
    });
</script>
<link rel="stylesheet" type="text/css" href="css/loginform.css">


<div id="loginForm">
    <div id="triangle"></div>
    <i class="fa fa-times-circle  fa-2x"  id="close" style="float:right;cursor: pointer" ></i>
    <h1>Welcome!</h1>

    <form action="login" method="post">
        <input type="hidden" name="action" value="authenticate">
        Username:<input id="username"name="username" type="text" placeholder="Username" value="1234"/></br>
        Password:<input id="password" name="password" type="password"  placeholder="Password" value="1234"/></br>
        <input type="submit" id="button_login" value="Login"  />
    </form>
</div>
