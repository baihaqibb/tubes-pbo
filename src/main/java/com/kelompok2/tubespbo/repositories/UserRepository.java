package com.kelompok2.tubespbo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.tubespbo.models.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}
