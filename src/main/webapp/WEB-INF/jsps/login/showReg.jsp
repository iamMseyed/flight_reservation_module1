<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New Registration</title>
</head>
<body>
   <div style="text-align: center">

       <h2>Create new account</h2>
       <form action="saveReg" method="post"> <!-- calls saveReg method in UserController-->
           <pre>
            <label> First Name <input type="text" name="firstName"/> </label> <!--name should be matched to entityName-->
            <label> Last Name <input type="text" name="lastName"/></label>
            <label> Email <input type="text" name="email"/> </label>
            <label> Password <input type="password" name="password"/> </label>
            <input type="submit" value="Register"/>
        </pre>
       </form>
   </div>
</body>
</html>
