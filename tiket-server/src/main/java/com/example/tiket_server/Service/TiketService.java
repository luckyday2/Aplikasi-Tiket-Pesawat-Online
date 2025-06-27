package com.example.tiket_server.Service;

import com.example.tiket_server.Model.TiketModel;
import com.example.tiket_server.Repository.TiketRepository;
import com.example.tiket_server.DTO.TiketRequestDTO;
import com.example.tiket_server.DTO.TiketResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TiketService {

    private final TiketRepository tiketRepository;

    public TiketService(TiketRepository tiketRepository) {
        this.tiketRepository = tiketRepository;
    }

    //create dari dto
    public TiketModel buatTiket(TiketRequestDTO request) {
        TiketModel tiket = new TiketModel();
        tiket.setUserId(request.getUserId());
        tiket.setFlightId(request.getFlightId());
        tiket.setNomorKursi(request.getNomorKursi());
        tiket.setTanggalPesan(LocalDate.now());
        tiket.setStatus("SUKSES");
        return tiketRepository.save(tiket);
    }

    //get all tiket -> response dto list
    public List<TiketResponseDTO> getAllTikets() {
        return tiketRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<TiketModel> getTiketById(String id) {
        return tiketRepository.findById(id);
    }

    public void deleteTiket(String id) {
        tiketRepository.deleteById(id);
    }

    public TiketModel updateTiket(String id, TiketModel updateData) {
        return tiketRepository.findById(id)
                .map(existing -> {
                    existing.setUserId(updateData.getUserId());
                    existing.setFlightId(updateData.getFlightId());
                    existing.setNomorKursi(updateData.getNomorKursi());
                    existing.setTanggalPesan(updateData.getTanggalPesan());
                    existing.setStatus(updateData.getStatus());
                    return tiketRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Tiket tidak ditemukan dengan id: " + id));
    }

    private TiketResponseDTO convertToDTO(TiketModel model) {
        TiketResponseDTO dto = new TiketResponseDTO();
        dto.setUserId(model.getUserId());
        dto.setFlightId(model.getFlightId());
        dto.setNomorKursi(model.getNomorKursi());
        dto.setTanggalPesan(model.getTanggalPesan());
        dto.setStatus(model.getStatus());
        return dto;
    }
}
