package com.example.flight_server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import com.example.flight_server.Model.FlightModel;

public interface FlightRepository extends JpaRepository<FlightModel, Long>  {
    //cari dari nomer flight
    List<FlightModel> findByNomorFlight(String nomorFlight);
    //cari dari id
    Optional<FlightModel> findById(Long id);
}
