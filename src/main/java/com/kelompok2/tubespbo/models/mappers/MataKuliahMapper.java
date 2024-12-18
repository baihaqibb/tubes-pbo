package com.kelompok2.tubespbo.models.mappers;

import static com.kelompok2.tubespbo.models.mappers.KomponenPenilaianMapper.mapToKomponenPenilaianDTO;

import java.util.stream.Collectors;

import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.models.dtos.MataKuliahDTO;

public class MataKuliahMapper {
    public static MataKuliah mapToMataKuliah(MataKuliahDTO mataKuliahDTO) {
        return MataKuliah.builder()
                         .id(mataKuliahDTO.getId())
                         .kode(mataKuliahDTO.getKode())
                         .nama(mataKuliahDTO.getNama())
                         .sks(mataKuliahDTO.getSks())
                         .nilai(mataKuliahDTO.getNilai())
                         .indexNilai(mataKuliahDTO.getIndexNilai())
                         .build();
    }

    public static MataKuliahDTO mapToMataKuliahDTO(MataKuliah mataKuliah) {
        return MataKuliahDTO.builder()
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