package com.example.user_service.Controller;

import java.util.List;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.user_service.Model.UserModel;
import com.example.user_service.Repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    // Tambah user
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserModel userBaru) {
    Optional<UserModel> existing = userRepository.findByNoHp(userBaru.getNoHp());

    if (existing.isPresent()) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "No HP sudah digunakan");
    }

    UserModel saved = userRepository.save(userBaru);
    return ResponseEntity.ok(saved);
}

    // Ambil semua user
    @GetMapping
    public List<UserModel> allUsers() {
        return userRepository.findAll();
    }

    // Cari user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable Long id) {
        Optional<UserModel> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tidak ditemukan"));
    }

    // Cari user by noHp
    @GetMapping("/search/by-noHp")
    public ResponseEntity<UserModel> findUserByNoHp(@RequestParam String noHp) {
        Optional<UserModel> user = userRepository.findByNoHp(noHp);
        return user.map(ResponseEntity::ok)
                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tidak ditemukan"));
    }

    // Cari user by nama
    @GetMapping("/search/by-nama")
    public List<UserModel> findUserByNama(@RequestParam String nama) {
        return userRepository.findByNamaContainingIgnoreCase(nama);
    }

    // Update user
    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long id, @RequestBody UserModel userDetail) {
        return userRepository.findById(id)
            .map(user -> {
                user.setNama(userDetail.getNama());
                user.setNoHp(userDetail.getNoHp());
                user.setAlamat(userDetail.getAlamat());
                UserModel updated = userRepository.save(user);
                return ResponseEntity.ok(updated);
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tidak ditemukan"));
    }

    // Hapus user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
            .map(user -> {
                userRepository.delete(user);
                return ResponseEntity.ok().build();
            })
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User tidak ditemukan"));
    }

}
