package com.seyed.flight_reservation.service;

import com.seyed.flight_reservation.dto.ReservationRequest;
import com.seyed.flight_reservation.entity.Reservation;

public interface ReservationService {
    Reservation bookFlight(ReservationRequest reservationRequest);
}