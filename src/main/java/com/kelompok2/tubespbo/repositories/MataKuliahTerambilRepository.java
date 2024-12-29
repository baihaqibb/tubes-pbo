package com.kelompok2.tubespbo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kelompok2.tubespbo.models.MataKuliahTerambil;

public interface MataKuliahTerambilRepository extends JpaRepository<MataKuliahTerambil, Integer> {
    Optional<MataKuliahTerambil> findByKode(String kode);
}
