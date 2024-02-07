package com.seyed.flight_reservation.service;

import com.seyed.flight_reservation.dto.ReservationRequest;
import com.seyed.flight_reservation.entity.Flight;
import com.seyed.flight_reservation.entity.Passenger;
import com.seyed.flight_reservation.entity.Reservation;
import com.seyed.flight_reservation.repository.FlightRepository;
import com.seyed.flight_reservation.repository.PassengerRepository;
import com.seyed.flight_reservation.repository.ReservationRepository;
import com.seyed.flight_reservation.utilities.PdfGenerator;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

//service layer is used mostly when we deal with a scenario of storing data to multiple tables
@Service
public class ReservationServiceImpl implements ReservationService{

    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;
    private final ReservationRepository reservationRepository;
    private final PdfGenerator pdfGenerator;
    public ReservationServiceImpl(PassengerRepository passengerRepository, FlightRepository flightRepository,ReservationRepository reservationRepository, PdfGenerator pdfGenerator) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.reservationRepository = reservationRepository;
        this.pdfGenerator = pdfGenerator;
    }

    @Override
    public Reservation bookFlight(ReservationRequest reservationRequest) {
        //inject passenger, flight and these both into reservation table

        Long flightId= reservationRequest.getFlightId();
        Optional<Flight> findById = flightRepository.findById(flightId);
        //we already have saved flight data onto db, but we need to store flight data and passenger data to reservation table
        //Flight flight=findById.get();
        Flight flight;
        if (findById.isPresent())
            flight = findById.get();
        else
            throw new RuntimeException("Flight not found");

        Passenger passenger = new Passenger();
        passenger.setFirstName(reservationRequest.getFirstName());
        passenger.setLastName(reservationRequest.getLastName());
        passenger.setMiddleName(reservationRequest.getMiddleName());
        passenger.setEmail(reservationRequest.getEmail());
        passenger.setPhone(reservationRequest.getPhone());
        passengerRepository.save(passenger); //passenger data saved

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false); //still false, after that we can use true
        reservation.setNumberOfBags(0);
        //to generate unique id when ticket booked
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        uuid = uuid.substring(0, Math.min(10, uuid.length())); // unique id of length 10;
        reservation.setUniqueNumber(uuid);

        String fN = passenger.getFirstName();
        reservationRepository.save(reservation);

        String file= "src/main/java/com/seyed/flight_reservation/tickets/booking_" + fN +"_"+uuid+".pdf";


        pdfGenerator.generateItinerary(reservation,file);
//      emailUtil.sendItinerary(reservation.getPassenger().getEmail(), FILE_PATH);

        return reservation; //is service layer, will return to controller layer
    }
}