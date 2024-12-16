package com.kelompok2.tubespbo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.Mahasiswa;
import com.kelompok2.tubespbo.models.dtos.MahasiswaDTO;
import com.kelompok2.tubespbo.repositories.MahasiswaRepository;
import com.kelompok2.tubespbo.services.MahasiswaService;

@Service
public class MahasiswaServiceImpl implements MahasiswaService {
    
    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Override
    public void saveMahasiswa(MahasiswaDTO mahasiswaDTO) {
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setUsername(mahasiswaDTO.getUsername());
        mahasiswa.setEmail(mahasiswaDTO.getEmail());
        mahasiswa.setPassword(mahasiswaDTO.getPassword());
        mahasiswa.setFullName(mahasiswaDTO.getFullName());
        mahasiswa.setNim(mahasiswa.getNim());
        mahasiswaRepository.save(mahasiswa);
    }

    
}
