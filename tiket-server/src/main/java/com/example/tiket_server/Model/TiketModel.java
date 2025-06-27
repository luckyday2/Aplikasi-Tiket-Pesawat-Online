package com.example.tiket_server.Model;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "tiket")
@Data
public class TiketModel {
    @Id
    private String id;
    private String userId;
    private String flightId;
    private String nomorKursi;
    private LocalDate tanggalPesan;
    private String status;
}
