package com.example.tiket_server.DTO;

import lombok.Data;

@Data
public class TiketRequestDTO {
    private String userId;

    private String flightId;

    private String nomorKursi;
}
