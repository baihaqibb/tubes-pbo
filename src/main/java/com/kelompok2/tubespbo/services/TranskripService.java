package com.kelompok2.tubespbo.services;

import java.util.List;

import com.kelompok2.tubespbo.models.dtos.TranskripDTO;

public interface TranskripService {
    List<TranskripDTO> findAllTranskripByMahasiswaId(int id);

    TranskripDTO findTranskripById(int ts_id);
}
