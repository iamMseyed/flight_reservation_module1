<%@ page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Flights</title>
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

    <h2>Flight Search Results</h2>
    <table>
        <tr>
            <th>Airlines</th>
            <th>Departure City</th>
            <th>Arrival City</th>
            <th>Departure Time</th>
            <th>Select Flight</th>
        </tr>

        <c:forEach items="${foundFlightsJSP}" var="flight">
            <tr>
                <c:choose>
                    <c:when test="${not empty flight.operatingAirlines}">
                        <td>${flight.operatingAirlines}&nbsp;&nbsp;</td>
                    </c:when>
                    <c:otherwise>
                        <td>NA&nbsp;&nbsp;</td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${not empty flight.departureCity}">
                        <td>${flight.departureCity}&nbsp;&nbsp;</td>
                    </c:when>
                    <c:otherwise>
                        <td>NA&nbsp;&nbsp;</td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${not empty flight.arrivalCity}">
                        <td>${flight.arrivalCity}&nbsp;&nbsp;</td>
                    </c:when>
                    <c:otherwise>
                        <td>NA&nbsp;&nbsp;</td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${not empty flight.estimatedDepartureTime}">
                        <td>${flight.estimatedDepartureTime}&nbsp;&nbsp;</td>
                    </c:when>
                    <c:otherwise>
                        <td>NA&nbsp;&nbsp;</td>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${not empty flight.id}">
                        <td>
                            <a href="showCompleteReservation?flightId=${flight.id}">Select</a>
                        </td>
                    </c:when>
                    <c:otherwise>
                        <td>NA</td>
                    </c:otherwise>
                </c:choose>
                <br>

            </tr>

        </c:forEach>


    </table>

</div>
</body>
</html>
