package com.kelompok2.tubespbo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.tubespbo.models.MataKuliah;

public interface MataKuliahRepository extends JpaRepository<MataKuliah, Integer> {
    
    public MataKuliah findByKode(String kode);
    
}
