package com.kelompok2.tubespbo.services;

import java.util.List;

import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.models.dtos.MataKuliahDTO;

public interface MataKuliahService {
    public List<MataKuliahDTO> findAllMataKuliah();
    public MataKuliahDTO findMataKuliahById(int id);
    public MataKuliahDTO findMataKuliahById2(int id);
    public MataKuliahDTO findMataKuliahByKode(String kode);
    public MataKuliah createMataKuliah(MataKuliahDTO mataKuliahDTO);
    public void updateMataKuliah(MataKuliahDTO mataKuliahDTO);
    public void deleteMataKuliahById(int id);
    public List<MataKuliahDTO> searchMataKuliahs(String query);
}
