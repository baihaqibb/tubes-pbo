package com.kelompok2.tubespbo.services;

import com.kelompok2.tubespbo.models.dtos.RencanaStudiDTO;

public interface RencanaStudiService {
    RencanaStudiDTO findRencanaStudiById(int id);
    void saveRencanaStudi(int rs_id, int mk_id);
    void removeRencanaStudi(int rs_id, int mk_id);
    void submitRencanaStudi(int rs_id);
}