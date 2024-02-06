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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

//service layer is used mostly when we deal with a scenario of storing data to multiple tables
@Service
public class ReservationServiceImpl implements ReservationService{

    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    private final ReservationRepository reservationRepository;
    public ReservationServiceImpl(PassengerRepository passengerRepository, FlightRepository flightRepository,ReservationRepository reservationRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation bookFlight(ReservationRequest reservationRequest) {
        //inject passenger, flight and these both into reservation table

        String filePath ="E:\\Programming Codes\\NareshIT_P_N\\JAVA\\Code\\SpringBoot\\flight_reservation\\src\\main\\java\\com\\seyed\\flight_reservation\\tickets\\booking";
        Passenger passenger = new Passenger();
        passenger.setFirstName(reservationRequest.getFirstName());
        passenger.setLastName(reservationRequest.getLastName());
        passenger.setMiddleName(reservationRequest.getMiddleName());
        passenger.setEmail(reservationRequest.getEmail());
        passenger.setPhone(reservationRequest.getPhone());
        passengerRepository.save(passenger); //passenger data saved

        Long flightId= reservationRequest.getFlightId();
        Optional<Flight> findById = flightRepository.findById(flightId);
        Flight flight=findById.get(); //we already have saved flight data onto db, but we need to store flight data and passenger data to reservation table
        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(passenger);
        reservation.setCheckedIn(false); //still false, after that we can use true
        reservation.setNumberOfBags(0);
        String uuid = UUID.randomUUID().toString().replaceAll("-", ""); //to generate unique id when ticket booked
        reservation.setUniqueNumber(uuid.substring(0, Math.min(10, uuid.length()))); // unique id of length 10;

        reservationRepository.save(reservation);

        PdfGenerator pdfGenerator = new PdfGenerator();
        pdfGenerator.generatePdf(filePath+"_"+passenger.getFirstName()+".pdf",reservationRequest.getFirstName(),reservationRequest.getEmail(),reservationRequest.getPhone(),
                flight.getOperatingAirlines(),flight.getDateOfDeparture(),flight.getDepartureCity(),flight.getArrivalCity());

        return reservation; //is service layer, will return to controller layer
    }
}