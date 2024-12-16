package com.kelompok2.tubespbo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.tubespbo.models.Mahasiswa;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Integer> {
    Mahasiswa findByEmail(String email);
    Mahasiswa findByUsername(String username);
    Mahasiswa findByNim(String nim);
}
