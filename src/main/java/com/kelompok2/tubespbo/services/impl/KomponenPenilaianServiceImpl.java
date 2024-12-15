package com.kelompok2.tubespbo.services.impl;

import static com.kelompok2.tubespbo.models.mappers.KomponenPenilaianMapper.mapToKomponenPenilaian;
import static com.kelompok2.tubespbo.models.mappers.KomponenPenilaianMapper.mapToKomponenPenilaianDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.KomponenPenilaian;
import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.models.dtos.KomponenPenilaianDTO;
import com.kelompok2.tubespbo.repositories.KomponenPenilaianRepository;
import com.kelompok2.tubespbo.repositories.MataKuliahRepository;
import com.kelompok2.tubespbo.services.KomponenPenilaianService;

@Service
public class KomponenPenilaianServiceImpl implements KomponenPenilaianService {

    @Autowired
    private MataKuliahRepository mataKuliahRepository;
    @Autowired
    private KomponenPenilaianRepository komponenPenilaianRepository;

    @Override
    public KomponenPenilaianDTO findKomponenPenilaianById(int id) {
        KomponenPenilaian komponenPenilaian = komponenPenilaianRepository.findById(id).get();
        return mapToKomponenPenilaianDTO(komponenPenilaian);
    }

    @Override
    public void createKomponenPenilaian(int mataKuliahId, KomponenPenilaianDTO komponenPenilaianDTO) {
        MataKuliah mataKuliah = mataKuliahRepository.findById(mataKuliahId).get();
        KomponenPenilaian komponenPenilaian = mapToKomponenPenilaian(komponenPenilaianDTO);
        komponenPenilaian.setMataKuliah(mataKuliah);
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
