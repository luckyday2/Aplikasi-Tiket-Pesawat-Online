package com.example.tiket_server.DTO;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TiketResponseDTO {
    private String userId;
    private String flightId;
    private String nomorKursi;
    private LocalDate tanggalPesan;
    private String status;
}
