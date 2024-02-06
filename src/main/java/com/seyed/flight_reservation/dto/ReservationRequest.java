package com.seyed.flight_reservation.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ReservationRequest {
    private Long flightId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private Long phone;

    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private Integer cvv;
}