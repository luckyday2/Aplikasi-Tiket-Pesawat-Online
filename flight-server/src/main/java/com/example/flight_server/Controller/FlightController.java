package com.example.flight_server.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.flight_server.Model.FlightModel;
import com.example.flight_server.Repository.FlightRepository;

@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    //create
    @PostMapping
    public FlightModel tambahPenerbangan(@RequestBody FlightModel flight){
        return flightRepository.save(flight);
    }

    //ambil data
    @GetMapping
    public List<FlightModel> allPenerbangan(){
        return flightRepository.findAll();
    }

    //search data by nomer flight
    @GetMapping("/search/by-nomor")
    public ResponseEntity<List<FlightModel>> cariBerdasarkanNomor(@RequestParam String nomorFlight){
        List<FlightModel> hasil = flightRepository.findByNomorFlight(nomorFlight);

        if (hasil.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(hasil);
    }

    //update data penerbangan
    @PutMapping("/{id}")
    public ResponseEntity <FlightModel> updatePenerbangan(@PathVariable Long id, @RequestBody FlightModel flightDetail){
        return flightRepository.findById(id)
            .map(flight -> {
                flight.setNomorFlight(flightDetail.getNomorFlight());
                flight.setArrivalBandara(flightDetail.getArrivalBandara());
                flight.setHarga(flightDetail.getHarga());
                flight.setKursiKosong(flightDetail.getKursiKosong());
                FlightModel updated = flightRepository.save(flight);
                return ResponseEntity.ok(updated);
            })
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan"));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?>cariBerdasarkanId(@PathVariable Long id){
        try {
            Optional<FlightModel> flight = flightRepository.findById(id);
            if (flight.isPresent()) {
                return ResponseEntity.ok(flight.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }


    //hapus data penerbangan
    @DeleteMapping("/{id}")
    public ResponseEntity<?> hapusPenerbangan(@PathVariable Long id){
        return flightRepository.findById(id)
            .map(flight ->{
                flightRepository.delete(flight);
                return ResponseEntity.ok().build();
            })
            .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Tidak Ditemukan"));
    }

    @PatchMapping("/{id}/kurangi-kursi")
    public ResponseEntity<FlightModel> kurangiKursi(@PathVariable Long id) {
        return flightRepository.findById(id)
            .map(flight -> {
                if (flight.getKursiKosong() <= 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kursi sudah habis");
                }
                flight.setKursiKosong(flight.getKursiKosong() - 1);
                FlightModel updated = flightRepository.save(flight);
                return ResponseEntity.ok(updated);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight tidak ditemukan"));
    }


}
