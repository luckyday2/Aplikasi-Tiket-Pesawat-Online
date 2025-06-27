package com.example.user_service.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.user_service.Model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    
    //caari penumpang berdasarkan nama
    List<UserModel> findByNamaContainingIgnoreCase(String nama);

    // Cari penumpang berdasarkan nomor telepon
    Optional<UserModel> findByNoHp(String noHp);
}
