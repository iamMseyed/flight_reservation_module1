<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login </title>
</head>
<body>
<div style="text-align: center">
    <h2>Login Here</h2>
    <div style="height: 20px">
        <!-- Display the message with fade timeout-->
        <span style="color: green" id="sucMsg">${successMsg}</span>
        <span style="color: red" id="errMsg">${errorMsg}</span>
    </div>
    <!-- JavaScript to fade away the message after 3 seconds -->
    <script>
        setTimeout(function() {
            var messageDiv = document.getElementById('sucMsg');
            messageDiv.style.opacity = '0';
            messageDiv.style.transition = 'opacity 1s'; // Fade out over 1 second
        }, 3000); // 3000 milliseconds = 3 seconds
        setTimeout(function() {
            var messageDiv = document.getElementById('errMsg');
            messageDiv.style.opacity = '0';
            messageDiv.style.transition = 'opacity 1s'; // Fade out over 1 second
        }, 3000); // 3000 milliseconds = 3 seconds
    </script>

    <form action="verifyLogin" method="post">
        <pre>
            <label>Email <input type="text" name="emailId" required></label>
            <label>Password <input type="password" name="password" required></label>
            <input type="submit" value="Login">
        </pre>
    </form>

</div>
</body>
</html>