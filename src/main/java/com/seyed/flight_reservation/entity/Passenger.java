package com.seyed.flight_reservation.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Passenger extends  AbstractEntity{
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private Long phone;
}
