package com.seyed.flight_reservation.controller;

import com.seyed.flight_reservation.dto.ReservationUpdateRequest;
import com.seyed.flight_reservation.entity.Reservation;
import com.seyed.flight_reservation.repository.ReservationRepository;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ReservationRestController {


    private final ReservationRepository reservationRepository;

    public ReservationRestController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    @RequestMapping("/reservation/{id}")
    public Reservation findReservation(@PathVariable ("id") Long id){
        Optional<Reservation> findById = reservationRepository.findById(id); //gets optional type
        Reservation reservation = null;
        if(findById.isPresent()){
            reservation = findById.get();  //exact matching entity object
        }
        return reservation; //entity object which is a java object, but will be converted into json object  due to @RestController annotation
    }
    /*  If we want to put a message on the postman console rather than showing json data
       @RequestMapping("/reservation")
           public ResponseEntity<String> updateReservation (@RequestBody ReservationUpdateRequest reservationUpdateRequest) { //it binds json object data to java class object
            Reservation reservation = reservationRepository.findById(reservationUpdateRequest.getId()).orElseThrow(
                    () -> new RuntimeException("Post not found with id: " + reservationUpdateRequest.getId())
            );
            reservation = findById.get();
            reservation.setCheckedIn(reservationUpdateRequest.isCheckedIn());
            reservation.setNumberOfBags(reservationUpdateRequest.getNumberOfBags());
            reservationRepository.save(reservation);
            return new ResponseEntity<>("Reservation Data added successfully!", HttpStatus.OK);
        }
        */
    @RequestMapping("/reservation")
    public Reservation updateReservation (@RequestBody ReservationUpdateRequest reservationUpdateRequest) {
        //@RB binds json object data to java class object with the help of @RC
        Reservation reservation;
        Optional<Reservation> findById = reservationRepository.findById(reservationUpdateRequest.getId()); //optional obj
        if(findById.isPresent()){
           reservation = findById.get(); //reservation obj
           reservation.setCheckedIn(reservationUpdateRequest.isCheckedIn());
           reservation.setNumberOfBags(reservationUpdateRequest.getNumberOfBags());
           reservationRepository.save(reservation); //saving data back to db
        }else{
           throw new RuntimeException("Details not found!");
        }
        return reservation;
    }
}