package com.seyed.flight_reservation.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
//import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Flight extends AbstractEntity{
    private String flightNumber;
    private String operatingAirlines;
    private String departureCity;
    private String arrivalCity;
    private LocalDate dateOfDeparture;
    private LocalTime estimatedDepartureTime;
}