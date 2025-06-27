package com.example.flight_server.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class FlightModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomorFlight;
    private String arrivalBandara;
    private Double harga;
    private Integer kursiKosong;

    public FlightModel() {
    // Constructor kosong untuk Hibernate
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNomorFlight() {
        return nomorFlight;
    }
    public void setNomorFlight(String nomorFlight) {
        this.nomorFlight = nomorFlight;
    }

    public String getArrivalBandara() {
        return arrivalBandara;
    }
    public void setArrivalBandara(String arrivalBandara) {
        this.arrivalBandara = arrivalBandara;
    }
    
    public Double getHarga() {
        return harga;
    }
    public void setHarga(Double harga) {
        this.harga = harga;
    }
    
    public Integer getKursiKosong() {
        return kursiKosong;
    }
    public void setKursiKosong(Integer kursiKosong) {
        this.kursiKosong = kursiKosong;
    }

}
