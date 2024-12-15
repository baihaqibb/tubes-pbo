package com.kelompok2.tubespbo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kelompok2.tubespbo.models.MataKuliah;

public interface MataKuliahRepository extends JpaRepository<MataKuliah, Integer> {
    Optional<MataKuliah> findByKode(String kode);
    @Query("SELECT mk FROM MataKuliah mk WHERE mk.kode LIKE CONCAT('%', :query, '%') OR mk.nama LIKE CONCAT('%', :query, '%')")
    List<MataKuliah> searchMataKuliahs(String query);
}
