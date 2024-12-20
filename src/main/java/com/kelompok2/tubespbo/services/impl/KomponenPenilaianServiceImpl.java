package com.kelompok2.tubespbo.services.impl;

import static com.kelompok2.tubespbo.models.mappers.KomponenPenilaianMapper.mapToKomponenPenilaian;
import static com.kelompok2.tubespbo.models.mappers.KomponenPenilaianMapper.mapToKomponenPenilaianDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.KomponenPenilaian;
import com.kelompok2.tubespbo.models.MataKuliahTerambil;
import com.kelompok2.tubespbo.models.dtos.KomponenPenilaianDTO;
import com.kelompok2.tubespbo.repositories.KomponenPenilaianRepository;
import com.kelompok2.tubespbo.repositories.MataKuliahTerambilRepository;
import com.kelompok2.tubespbo.services.KomponenPenilaianService;

@Service
public class KomponenPenilaianServiceImpl implements KomponenPenilaianService {

    @Autowired
    private MataKuliahTerambilRepository mataKuliahTerambilRepository;
    @Autowired
    private KomponenPenilaianRepository komponenPenilaianRepository;

    @Override
    public KomponenPenilaianDTO findKomponenPenilaianById(int id) {
        KomponenPenilaian komponenPenilaian = komponenPenilaianRepository.findById(id).get();
        return mapToKomponenPenilaianDTO(komponenPenilaian);
    }

    @Override
    public void createKomponenPenilaian(int mataKuliahTerambilId, KomponenPenilaianDTO komponenPenilaianDTO) {
        MataKuliahTerambil mataKuliahTerambil = mataKuliahTerambilRepository.findById(mataKuliahTerambilId).get();
        KomponenPenilaian komponenPenilaian = mapToKomponenPenilaian(komponenPenilaianDTO);
        komponenPenilaian.setMataKuliahTerambil(mataKuliahTerambil);
        komponenPenilaianRepository.save(komponenPenilaian);
    }

    @Override
    public void updateKomponenPenilaian(KomponenPenilaianDTO komponenPenilaianDTO) {
        KomponenPenilaian komponenPenilaian = mapToKomponenPenilaian(komponenPenilaianDTO);
        komponenPenilaianRepository.save(komponenPenilaian);
    }

    @Override
    public void deleteKomponenPenilaianByID(int id) {
        komponenPenilaianRepository.deleteById(id);
    }
    
}
