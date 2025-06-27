package com.example.tiket_server.DTO;
import lombok.Data;

@Data
public class FlightDTO {
    private Long id;
    private String nomorFlight;
    private String arrivalBandara;
    private int kursiKosong;
    private Double harga;
}
