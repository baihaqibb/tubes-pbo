package com.kelompok2.tubespbo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.dtos.MataKuliahDTO;
import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.repositories.MataKuliahRepository;
import com.kelompok2.tubespbo.services.MataKuliahService;

@Service
public class MataKuliahServiceImpl implements MataKuliahService {
    
    @Autowired
    private MataKuliahRepository mataKuliahRepository;

    private MataKuliahDTO mapToMataKuliahDTO(MataKuliah mataKuliah) {
        return MataKuliahDTO.builder()
                            .id(mataKuliah.getId())
                            .kode(mataKuliah.getKode())
                            .nama(mataKuliah.getNama())
                            .sks(mataKuliah.getSks())
                            .build();
    }

    private MataKuliah mapToMataKuliah(MataKuliahDTO mataKuliahDTO) {
        return MataKuliah.builder()
                         .id(mataKuliahDTO.getId())
                         .kode(mataKuliahDTO.getKode())
                         .nama(mataKuliahDTO.getNama())
                         .sks(mataKuliahDTO.getSks())
                         .build();
    }

    @Override
    public List<MataKuliahDTO> findAllMataKuliah() {
        List<MataKuliah> mataKuliahs = mataKuliahRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return mataKuliahs.stream().map((mataKuliah) -> mapToMataKuliahDTO(mataKuliah)).collect(Collectors.toList());
    }

    @Override
    public MataKuliah saveMataKuliah(MataKuliahDTO mataKuliahDTO) {     
        MataKuliah mataKuliah = mapToMataKuliah(mataKuliahDTO);
        return mataKuliahRepository.save(mataKuliah);
    }

    @Override
    public MataKuliahDTO findMataKuliahById(int id) {
        MataKuliah mataKuliah = mataKuliahRepository.findById(id).get();
        return mapToMataKuliahDTO(mataKuliah);
    }

    @Override
    public MataKuliahDTO findMataKuliahByKode(String kode) {
        MataKuliah mataKuliah = mataKuliahRepository.findByKode(kode).get();
        return mapToMataKuliahDTO(mataKuliah);
    }

    @Override
    public void updateMataKuliah(MataKuliahDTO mataKuliahDTO) {
        MataKuliah mataKuliah = mapToMataKuliah(mataKuliahDTO);
        mataKuliahRepository.save(mataKuliah);
    }

    @Override
    public void deleteMataKuliahById(int id) {
        mataKuliahRepository.deleteById(id);
    }

    @Override
    public List<MataKuliahDTO> searchMataKuliahs(String query) {
        List<MataKuliah> mataKuliahs = mataKuliahRepository.searchMataKuliahs(query);
        return mataKuliahs.stream().map((mk) -> mapToMataKuliahDTO(mk)).collect(Collectors.toList());
    }

}
