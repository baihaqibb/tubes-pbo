package com.kelompok2.tubespbo.services;

import java.util.List;

import com.kelompok2.tubespbo.models.dtos.MataKuliahTerambilDTO;

public interface MataKuliahTerambilService {
    List<MataKuliahTerambilDTO> findAllMataKuliahTerambil();
    void createMataKuliahTerambil(int transkripId, MataKuliahTerambilDTO mataKuliahTerambilDTO);
    MataKuliahTerambilDTO findMataKuliahTerambilById(int mkt_id);
}
