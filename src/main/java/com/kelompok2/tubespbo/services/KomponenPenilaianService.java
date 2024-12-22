package com.kelompok2.tubespbo.services;

import com.kelompok2.tubespbo.models.dtos.KomponenPenilaianDTO;

public interface KomponenPenilaianService {
    KomponenPenilaianDTO findKomponenPenilaianById(int id);
    void createKomponenPenilaian(int mataKuliahTerambilId, KomponenPenilaianDTO komponenPenilaianDTO);
    void updateKomponenPenilaian(KomponenPenilaianDTO komponenPenilaianDTO);
    void deleteKomponenPenilaianByID(int id);
}
