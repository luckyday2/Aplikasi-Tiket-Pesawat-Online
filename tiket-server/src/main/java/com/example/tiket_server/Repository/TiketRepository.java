package com.example.tiket_server.Repository;

import com.example.tiket_server.Model.TiketModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TiketRepository extends MongoRepository<TiketModel, String> {
    List<TiketModel> findByUserId(String userId);
    List<TiketModel> findByFlightId(String flightId);
}
