package com.seyed.flight_reservation.repository;

import com.seyed.flight_reservation.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    /*when we need to search based on multiple parameters, then we use @Query() annotation
      If, we need to search based on a single query, then we can build a method which is in plain english, like findByEmail(anyEmail),
      findByPhoneNumber(anyPhoneNumber) and Spring is intelligent enough to get data based on the parameter provided, But if we want to
      search based on multiple queries, then we need to use @Query().
     */

    @Query("from Flight where departureCity=:from and arrivalCity=:to and dateOfDeparture=:departureDate")
        //sql query here, name should match with db table column name
    List<Flight> findFlights(
            @Param("from") String from,
            @Param("to") String to,
            @Param("departureDate") LocalDate departureDate
    );

    /*once findFlight() is called, the variable names will carry the data and will supply to @Param's parameter name, that is variable "from" will store
    data to departureCity, variable to will store data to arrivalCity and departureData will store data to dateOfDeparture. This is the same data supplied
    from form, and then to controller and to here (repository). Once this is done, @Query() will be executed, and will act as a filter. It is sql query which
    search's in Flight table(name should be same as Entity class not of database table) and will retrieve flights based on departureCity, arrivalCity and
    departureDate. This will return none, one or more than one values, thus storing back to a List in the next line of code i.e. List<Flight>
    */
}
