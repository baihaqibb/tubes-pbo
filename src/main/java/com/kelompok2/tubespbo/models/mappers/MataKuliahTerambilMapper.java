package com.kelompok2.tubespbo.models.mappers;

import static com.kelompok2.tubespbo.models.mappers.KomponenPenilaianMapper.mapToKomponenPenilaianDTO;

import java.util.stream.Collectors;

import com.kelompok2.tubespbo.models.MataKuliahTerambil;
import com.kelompok2.tubespbo.models.dtos.MataKuliahTerambilDTO;

public class MataKuliahTerambilMapper {
    public static MataKuliahTerambil mapToMataKuliahTerambil(MataKuliahTerambilDTO mataKuliahDTO) {
        return MataKuliahTerambil.builder()
                         .id(mataKuliahDTO.getId())
                         .kode(mataKuliahDTO.getKode())
                         .nama(mataKuliahDTO.getNama())
                         .sks(mataKuliahDTO.getSks())
                         .nilai(mataKuliahDTO.getNilai())
                         .indexNilai(mataKuliahDTO.getIndexNilai())
                         .build();
    }

    public static MataKuliahTerambilDTO mapToMataKuliahTerambilDTO(MataKuliahTerambil mataKuliah) {
        return MataKuliahTerambilDTO.builder()
                            .id(mataKuliah.getId())
                            .kode(mataKuliah.getKode())
                            .nama(mataKuliah.getNama())
                            .sks(mataKuliah.getSks())
                            .komponenPenilaian(mataKuliah.getKomponenPenilaian()
                                                         .stream()
                                                         .map((kp) -> mapToKomponenPenilaianDTO(kp))
                                                         .collect(Collectors.toList()))
                            .nilai(mataKuliah.getNilai())
                            .indexNilai(mataKuliah.getIndexNilai())
                            .build();
    }
}
