package com.kelompok2.tubespbo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.tubespbo.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByNip(String nip);
}
