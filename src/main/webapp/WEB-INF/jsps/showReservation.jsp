<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Reservations </title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<div style="text-align: center">
        <h2>Flight Details</h2>
        <table>
            <tr>
                <th>Flight Number</th>
                <th>Operating Airlines</th>
                <th>Departure City</th>
                <th>Arrival City</th>
                <th>Departure Date</th>
                <th>Departure Time</th>
            </tr>

            <c:forEach items="${flight}" var="flight">
                <tr>
                    <td>${flight.flightNumber}</td> <%-- .entityVariableName--%>
                    <td>${flight.operatingAirlines}</td>
                    <td>${flight.departureCity}</td>
                    <td>${flight.arrivalCity}</td>
                    <td>${flight.dateOfDeparture.format(DateTimeFormatter.ofPattern('yyyy-MMM-dd'))}</td> <!-- showing date as y-m-d format-->
                    <td>${flight.estimatedDepartureTime}</td>
                </tr>
            </c:forEach>
        </table>
    <hr/>
    <form action="confirmReservation" method="post">
        <pre>
            <label>Enter Passenger Details</label>
            <label>First Name <input type="text" name="firstName"/></label>
            <label>Last Name <input type="text" name="lastName"/></label>
            <label>Middle Name <input type="text" name="middleName"/></label>
            <label>Email <input type="email" name="email"/></label>
            <label>Phone Number <input type="number" name="phone"/></label>
            <input type="hidden" name="flightId" value="${flight.id}"/>
            <label>Enter Card Details</label>
            <label>Card Number<input type="text" name="cardNumber"/></label>
            <label>Name on the Card <input type="text" name="cardHolderName"/></label>
            <label>CVV <input type="number" name="cvv"/> </label>
            <label>Expiry Date <input type="date" name="expiryDate"></label>
            <input type="submit" value="Complete Reservation"/>
        </pre>
    </form>
</div>
</body>
</html>