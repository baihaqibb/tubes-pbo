package com.kelompok2.tubespbo.services.impl;

import static com.kelompok2.tubespbo.models.mappers.MataKuliahTerambilMapper.mapToMataKuliahTerambil;
import static com.kelompok2.tubespbo.models.mappers.MataKuliahTerambilMapper.mapToMataKuliahTerambilDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.MataKuliahTerambil;
import com.kelompok2.tubespbo.models.Transkrip;
import com.kelompok2.tubespbo.models.dtos.MataKuliahTerambilDTO;
import com.kelompok2.tubespbo.repositories.MataKuliahTerambilRepository;
import com.kelompok2.tubespbo.repositories.TranskripRepository;
import com.kelompok2.tubespbo.services.MataKuliahTerambilService;

@Service
public class MataKuliahTerambilServiceImpl implements MataKuliahTerambilService {

    @Autowired
    private MataKuliahTerambilRepository mataKuliahTerambilRepository;
    @Autowired
    private TranskripRepository transkripRepository;

    @Override
    public List<MataKuliahTerambilDTO> findAllMataKuliahTerambil() {
        List<MataKuliahTerambil> mataKuliahTerambils = mataKuliahTerambilRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return mataKuliahTerambils.stream().map(mkt -> mapToMataKuliahTerambilDTO(mkt)).collect(Collectors.toList());
    }

    @Override
    public void createMataKuliahTerambil(int transkripId, MataKuliahTerambilDTO mataKuliahTerambilDTO) {
        Transkrip transkrip = transkripRepository.findById(transkripId).get();
        MataKuliahTerambil mataKuliahTerambil = mapToMataKuliahTerambil(mataKuliahTerambilDTO);
        mataKuliahTerambil.setTranskrip(transkrip);
        mataKuliahTerambilRepository.save(mataKuliahTerambil);
    }

    @Override
    public MataKuliahTerambilDTO findMataKuliahTerambilById(int mkt_id) {
        try {
            MataKuliahTerambil mkt = mataKuliahTerambilRepository.findById(mkt_id).get();
            return mapToMataKuliahTerambilDTO(mkt);
        } catch (Exception e) {
            return null;
        }
    }
    
}
