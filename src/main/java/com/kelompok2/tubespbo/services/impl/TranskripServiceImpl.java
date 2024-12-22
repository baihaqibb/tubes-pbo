package com.kelompok2.tubespbo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.kelompok2.tubespbo.models.Transkrip;
import com.kelompok2.tubespbo.models.dtos.TranskripDTO;
import com.kelompok2.tubespbo.repositories.TranskripRepository;
import com.kelompok2.tubespbo.services.TranskripService;

import static com.kelompok2.tubespbo.models.mappers.TranskripMapper.mapToTranskripDTO;

@Service
public class TranskripServiceImpl implements TranskripService {

    @Autowired
    private TranskripRepository transkripRepository;
    @Override
    public List<TranskripDTO> findAllTranskrip() {
        List<Transkrip> transkrips = transkripRepository.findAll(Sort.by(Sort.Direction.ASC, "semester"));
        return transkrips.stream().map((ts) -> mapToTranskripDTO(ts)).collect(Collectors.toList());
    }
    
}
