<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script>
            alert("Login Error");
            
        </script>
        <h1>Login Error! Please try again</h1>
        <form action="login" method="post">
            <input type="hidden" name="action" value="authenticate">
            Username:<input id="username"name="username" type="text" placeholder="Username" value="1234"/></br>
            Password:<input id="password" name="password" type="password"  placeholder="Password" value="1234"/></br>
            <input type="submit" id="button_login" value="Login"  />
        </form>
        <a href="main.jsp">Get Back</a>
    </body>
</html>
