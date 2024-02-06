package com.seyed.flight_reservation.controller;

import com.seyed.flight_reservation.dto.ReservationRequest;
import com.seyed.flight_reservation.entity.Reservation;
import com.seyed.flight_reservation.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ReservationController {
    ReservationService reservationService;
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    /*constructor based injection. though we think, we are injecting bean dependency of reservationService, which itself is an interface. But internally it injects bean dependency of
    implemented class.
    */

    @RequestMapping("/confirmReservation")
    public String confirmReservation(ReservationRequest reservationRequest, ModelMap modelMap){
        //we can use requestParam or DTO to transfer data from jsp to controller but request param will be lengthy
       Reservation reservationId = reservationService.bookFlight(reservationRequest);
       //this will call service layer, and will return Reservation type object
        modelMap.addAttribute("reservationId",reservationId.getUniqueNumber());
       return "confirmReservation";
    }
}