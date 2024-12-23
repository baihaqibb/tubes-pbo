package com.kelompok2.tubespbo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.kelompok2.tubespbo.models.Mahasiswa;
import com.kelompok2.tubespbo.models.Transkrip;
import com.kelompok2.tubespbo.models.dtos.TranskripDTO;
import com.kelompok2.tubespbo.repositories.MahasiswaRepository;
import com.kelompok2.tubespbo.repositories.TranskripRepository;
import com.kelompok2.tubespbo.services.TranskripService;

import static com.kelompok2.tubespbo.models.mappers.TranskripMapper.mapToTranskripDTO;

@Service
public class TranskripServiceImpl implements TranskripService {

    @Autowired
    private TranskripRepository transkripRepository;
    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Override
    public List<TranskripDTO> findAllTranskripByMahasiswaId(int id) {
        try {
            Mahasiswa mhs = mahasiswaRepository.findById(id).get();
            List<Transkrip> transkrips = transkripRepository.findByMahasiswa(mhs);
            return transkrips.stream().map((ts) -> mapToTranskripDTO(ts)).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TranskripDTO findTranskripById(int ts_id) {
        try {
            Transkrip ts = transkripRepository.findById(ts_id).get();
            return mapToTranskripDTO(ts);
        } catch (Exception e) {
            return null;
        }
    }
    
    
}
