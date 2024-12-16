package com.kelompok2.tubespbo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.tubespbo.models.Mahasiswa;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Integer> {
    Optional<Mahasiswa> findByEmail(String email);
    Optional<Mahasiswa> findByUsername(String username);
    Optional<Mahasiswa> findByNim(String nim);
}
