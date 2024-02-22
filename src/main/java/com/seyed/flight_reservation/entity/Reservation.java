package com.seyed.flight_reservation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Reservation extends AbstractEntity{
    private boolean checkedIn;
    private int numberOfBags;
    private String uniqueNumber;
    @OneToOne
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private Passenger passenger;
    @OneToOne
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;
}