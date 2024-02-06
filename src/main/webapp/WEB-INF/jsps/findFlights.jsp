<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Find Flights </title>
</head>
<body>
<div style="text-align: center">
    <h2>Search for flights</h2>
    <form action="findFlights" method="post">
        <pre>
            <label>From <input type="text" name="from" required/></label>
            <label>To <input type="text" name="to" required/></label>
            <label>Departure Date<input type="date" name="departureDate" required/></label> <!-- for date and time-->
            <input type="submit" value="Search" required/>
        </pre>
    </form>
</div>
</body>
</html>