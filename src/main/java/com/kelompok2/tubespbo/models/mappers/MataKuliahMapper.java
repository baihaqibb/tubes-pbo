package com.kelompok2.tubespbo.models.mappers;

import static com.kelompok2.tubespbo.models.mappers.RencanaStudiMapper.mapToRencanaStudiDTO;

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
                .build();
    }

    public static MataKuliahDTO mapToMataKuliahDTO(MataKuliah mataKuliah) {
        return MataKuliahDTO.builder()
                .id(mataKuliah.getId())
                .kode(mataKuliah.getKode())
                .nama(mataKuliah.getNama())
                .sks(mataKuliah.getSks())
                .build();
    }

    public static MataKuliahDTO mapToMataKuliahDTO2(MataKuliah mataKuliah) {
        return MataKuliahDTO.builder()
                .id(mataKuliah.getId())
                .kode(mataKuliah.getKode())
                .nama(mataKuliah.getNama())
                .sks(mataKuliah.getSks())
                .rencanaStudi(mataKuliah.getRencanaStudi().stream().map((rs) -> mapToRencanaStudiDTO(rs)).collect(Collectors.toList()))
                .build();
    }
}
