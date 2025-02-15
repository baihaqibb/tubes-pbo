package com.kelompok2.tubespbo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.tubespbo.models.Role;
import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Integer>{
    Optional<Role> findByRole(String role);
}
