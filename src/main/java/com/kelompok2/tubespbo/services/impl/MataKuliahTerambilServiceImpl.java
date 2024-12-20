package com.kelompok2.tubespbo.services.impl;

import static com.kelompok2.tubespbo.models.mappers.MataKuliahTerambilMapper.mapToMataKuliahTerambilDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.kelompok2.tubespbo.models.MataKuliahTerambil;
import com.kelompok2.tubespbo.models.dtos.MataKuliahTerambilDTO;
import com.kelompok2.tubespbo.repositories.MataKuliahTerambilRepository;
import com.kelompok2.tubespbo.services.MataKuliahTerambilService;

public class MataKuliahTerambilServiceImpl implements MataKuliahTerambilService {

    @Autowired
    private MataKuliahTerambilRepository mataKuliahTerambilRepository;

    @Override
    public List<MataKuliahTerambilDTO> findAllMataKuliahTerambil() {
        List<MataKuliahTerambil> mataKuliahTerambils = mataKuliahTerambilRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return mataKuliahTerambils.stream().map(mkt -> mapToMataKuliahTerambilDTO(mkt)).collect(Collectors.toList());
    }
    
}
