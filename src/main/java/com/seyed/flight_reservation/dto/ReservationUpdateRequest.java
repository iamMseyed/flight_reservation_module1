package com.seyed.flight_reservation.dto;

import lombok.Data;

@Data
public class ReservationUpdateRequest {
    private Long id;
    private boolean checkedIn;
    private int numberOfBags;
}
