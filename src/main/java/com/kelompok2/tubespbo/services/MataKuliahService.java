package com.kelompok2.tubespbo.services;

import java.util.List;

import com.kelompok2.tubespbo.dtos.MataKuliahDTO;
import com.kelompok2.tubespbo.models.MataKuliah;

public interface MataKuliahService {
    public List<MataKuliahDTO> findAllMataKuliah();
    public MataKuliah saveMataKuliah(MataKuliahDTO mataKuliahDTO);
    public MataKuliahDTO findMataKuliahById(int id);
    public MataKuliahDTO findMataKuliahByKode(String kode);
    public void updateMataKuliah(MataKuliahDTO mataKuliahDTO);
    public void deleteMataKuliahById(int id);
    public List<MataKuliahDTO> searchMataKuliahs(String query);
}
