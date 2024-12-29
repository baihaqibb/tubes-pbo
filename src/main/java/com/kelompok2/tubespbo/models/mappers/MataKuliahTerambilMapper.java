package com.kelompok2.tubespbo.models.mappers;

import static com.kelompok2.tubespbo.models.mappers.KomponenPenilaianMapper.mapToKomponenPenilaianDTO;

import java.util.stream.Collectors;

import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.models.MataKuliahTerambil;
import com.kelompok2.tubespbo.models.dtos.MataKuliahTerambilDTO;

public class MataKuliahTerambilMapper {
    public static MataKuliahTerambil mapToMataKuliahTerambil(MataKuliahTerambilDTO mataKuliahTerambilDTO) {
        return MataKuliahTerambil.builder()
                         .id(mataKuliahTerambilDTO.getId())
                         .kode(mataKuliahTerambilDTO.getKode())
                         .nama(mataKuliahTerambilDTO.getNama())
                         .sks(mataKuliahTerambilDTO.getSks())
                         .nilai(mataKuliahTerambilDTO.getNilai())
                         .indexNilai(mataKuliahTerambilDTO.getIndexNilai())
                         .transkrip(mataKuliahTerambilDTO.getTranskrip())
                         .build();
    }

    public static MataKuliahTerambil mapToMataKuliahTerambilFromMataKuliah(MataKuliah mataKuliah) {
        return MataKuliahTerambil.builder()
                         .kode(mataKuliah.getKode())
                         .nama(mataKuliah.getNama())
                         .sks(mataKuliah.getSks())
                         .nilai(0)
                         .indexNilai('-')
                         .build();
    }

    public static MataKuliahTerambilDTO mapToMataKuliahTerambilDTO(MataKuliahTerambil mataKuliahTerambil) {
        return MataKuliahTerambilDTO.builder()
                            .id(mataKuliahTerambil.getId())
                            .kode(mataKuliahTerambil.getKode())
                            .nama(mataKuliahTerambil.getNama())
                            .sks(mataKuliahTerambil.getSks())
                            .komponenPenilaian(mataKuliahTerambil.getKomponenPenilaian()
                                                         .stream()
                                                         .map((kp) -> mapToKomponenPenilaianDTO(kp))
                                                         .collect(Collectors.toList()))
                            .nilai(mataKuliahTerambil.getNilai())
                            .indexNilai(mataKuliahTerambil.getIndexNilai())
                            .transkrip(mataKuliahTerambil.getTranskrip())
                            .build();
    }
}
