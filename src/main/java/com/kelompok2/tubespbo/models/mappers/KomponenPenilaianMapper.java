package com.kelompok2.tubespbo.models.mappers;

import com.kelompok2.tubespbo.models.KomponenPenilaian;
import com.kelompok2.tubespbo.models.dtos.KomponenPenilaianDTO;

public class KomponenPenilaianMapper {
    public static KomponenPenilaian mapToKomponenPenilaian(KomponenPenilaianDTO komponenPenilaianDTO){
        return KomponenPenilaian.builder()
                                .id(komponenPenilaianDTO.getId())
                                .tipe(komponenPenilaianDTO.getTipe())
                                .bobot(komponenPenilaianDTO.getBobot())
                                .nilai(komponenPenilaianDTO.getNilai())
                                .mataKuliahTerambil(komponenPenilaianDTO.getMataKuliahTerambil())
                                .build();
    }

    public static KomponenPenilaianDTO mapToKomponenPenilaianDTO(KomponenPenilaian komponenPenilaian){
        return KomponenPenilaianDTO.builder()
                                .id(komponenPenilaian.getId())
                                .tipe(komponenPenilaian.getTipe())
                                .bobot(komponenPenilaian.getBobot())
                                .nilai(komponenPenilaian.getNilai())
                                .mataKuliahTerambil(komponenPenilaian.getMataKuliahTerambil())
                                .build();
    }
}
