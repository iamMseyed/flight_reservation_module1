package com.seyed.flight_reservation.controller;

import com.seyed.flight_reservation.entity.Flight;
import com.seyed.flight_reservation.repository.FlightRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class FlightController {
    //    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @RequestMapping("/findFlights")
    public String findFlights(
            @RequestParam("from") String from, //retrieving data from "form field" and initializing here to a new variable
            @RequestParam("to") String to,
            @RequestParam("departureDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate departureDate,
            ModelMap modelMap) {
//        LOGGER.info("You are in findFlights()");
        List<Flight> foundFlights = flightRepository.findFlights(from, to, departureDate); //so findFlights() will be called which is in flightRepo
        modelMap.addAttribute("foundFlightsJSP", foundFlights); //so foundFlightsJSP will hold the data from controller, and we will use this in jsp to display the data
        return "displayFlights";
    }

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        Optional<Flight> findById=flightRepository.findById(flightId);
        Flight flight;
        if (findById.isPresent()) {
            flight = findById.get();
        }else
            throw new RuntimeException("Flight not found");
        modelMap.addAttribute("flight",flight);//pass to jsp file
        return "showReservation";
    }
}