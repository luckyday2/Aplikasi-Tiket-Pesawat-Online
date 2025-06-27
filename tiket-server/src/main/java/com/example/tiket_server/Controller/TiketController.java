package com.example.tiket_server.Controller;

import com.example.tiket_server.Service.TiketService;
import com.example.tiket_server.DTO.TiketRequestDTO;
import com.example.tiket_server.DTO.TiketResponseDTO;
import com.example.tiket_server.Model.TiketModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tiket")
public class TiketController {

    private final TiketService tiketService;

    public TiketController(TiketService tiketService) {
        this.tiketService = tiketService;
    }

    //create
    @PostMapping
    public ResponseEntity<TiketModel> buatTiket(@RequestBody TiketRequestDTO request) {
        return ResponseEntity.ok(tiketService.buatTiket(request));
    }

    //ambil data
    @GetMapping
    public ResponseEntity<List<TiketResponseDTO>> getAllTikets() {
        return ResponseEntity.ok(tiketService.getAllTikets());
    }

    //cari by id
    @GetMapping("/{id}")
    public ResponseEntity<TiketModel> getTiketById(@PathVariable String id) {
        return tiketService.getTiketById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTiket(@PathVariable String id) {
        tiketService.deleteTiket(id);
        return ResponseEntity.noContent().build();
    }

    //update by id
    @PutMapping("/{id}")
    public ResponseEntity<TiketModel> updateTiket(@PathVariable String id, @RequestBody TiketModel updateData) {
        try {
            TiketModel updated = tiketService.updateTiket(id, updateData);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
