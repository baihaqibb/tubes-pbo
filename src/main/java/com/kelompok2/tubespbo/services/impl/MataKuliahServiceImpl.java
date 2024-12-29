package com.kelompok2.tubespbo.services.impl;

import static com.kelompok2.tubespbo.models.mappers.MataKuliahMapper.mapToMataKuliah;
import static com.kelompok2.tubespbo.models.mappers.MataKuliahMapper.mapToMataKuliahDTO;
import static com.kelompok2.tubespbo.models.mappers.MataKuliahMapper.mapToMataKuliahDTO2;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.models.dtos.MataKuliahDTO;
import com.kelompok2.tubespbo.repositories.MataKuliahRepository;
import com.kelompok2.tubespbo.services.MataKuliahService;

@Service
public class MataKuliahServiceImpl implements MataKuliahService {
    
    @Autowired
    private MataKuliahRepository mataKuliahRepository;

    @Override
    public List<MataKuliahDTO> findAllMataKuliah() {
        List<MataKuliah> mataKuliahs = mataKuliahRepository.findAll(Sort.by(Sort.Direction.ASC, "kode"));
        return mataKuliahs.stream().map((mataKuliah) -> mapToMataKuliahDTO(mataKuliah)).collect(Collectors.toList());
    }

    @Override
    public MataKuliahDTO findMataKuliahById(int id) {
        try {
            MataKuliah mataKuliah = mataKuliahRepository.findById(id).get();
            return mapToMataKuliahDTO(mataKuliah);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MataKuliahDTO findMataKuliahById2(int id) {
        try {
            MataKuliah mataKuliah = mataKuliahRepository.findById(id).get();
            return mapToMataKuliahDTO2(mataKuliah);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MataKuliahDTO findMataKuliahByKode(String kode) {
        try {
            MataKuliah mataKuliah = mataKuliahRepository.findByKode(kode).get();
            return mapToMataKuliahDTO(mataKuliah);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MataKuliah createMataKuliah(MataKuliahDTO mataKuliahDTO) {     
        MataKuliah mataKuliah = mapToMataKuliah(mataKuliahDTO);
        return mataKuliahRepository.save(mataKuliah);
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
