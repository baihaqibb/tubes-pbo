package com.kelompok2.tubespbo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelompok2.tubespbo.models.Mahasiswa;
import com.kelompok2.tubespbo.models.Transkrip;
import java.util.List;


public interface TranskripRepository extends JpaRepository<Transkrip, Integer> {
    List<Transkrip> findByMahasiswa(Mahasiswa mahasiswa);
}
