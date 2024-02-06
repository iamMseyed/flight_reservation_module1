package com.seyed.flight_reservation.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class User extends  AbstractEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}