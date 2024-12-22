package com.kelompok2.tubespbo.models.mappers;

import static com.kelompok2.tubespbo.models.mappers.MataKuliahMapper.mapToMataKuliahDTO;

import java.util.stream.Collectors;

import com.kelompok2.tubespbo.models.RencanaStudi;
import com.kelompok2.tubespbo.models.dtos.RencanaStudiDTO;

public class RencanaStudiMapper {
    public static RencanaStudi mapToRencanaStudi(RencanaStudiDTO rencanaStudiDTO) {
        return RencanaStudi.builder()
                .id(rencanaStudiDTO.getId())
                .semester(rencanaStudiDTO.getSemester())
                .totalSKS(rencanaStudiDTO.getTotalSKS())
                .mahasiswa(rencanaStudiDTO.getMahasiswa())
                .build();
    }

    public static RencanaStudiDTO mapToRencanaStudiDTO(RencanaStudi rencanaStudi) {
        return RencanaStudiDTO.builder()
                .id(rencanaStudi.getId())
                .semester(rencanaStudi.getSemester())
                .listMK(rencanaStudi.getListMK().stream().map((lmk) -> mapToMataKuliahDTO(lmk))
                        .collect(Collectors.toList()))
                .totalSKS(rencanaStudi.getTotalSKS())
                .mahasiswa(rencanaStudi.getMahasiswa())
                .build();
    }
}
