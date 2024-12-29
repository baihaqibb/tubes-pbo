package com.kelompok2.tubespbo.models.mappers;

import java.util.stream.Collectors;

import static com.kelompok2.tubespbo.models.mappers.MataKuliahTerambilMapper.mapToMataKuliahTerambilDTO;

import com.kelompok2.tubespbo.models.Transkrip;
import com.kelompok2.tubespbo.models.dtos.TranskripDTO;

public class TranskripMapper {
    public static Transkrip mapToTranskrip(TranskripDTO transkripDTO) {
        return Transkrip.builder()
                .id(transkripDTO.getId())
                .semester(transkripDTO.getSemester())
                .totalSKS(transkripDTO.getTotalSKS())
                .ips(transkripDTO.getIps())
                .mahasiswa(transkripDTO.getMahasiswa())
                .build();
    }

    public static TranskripDTO mapToTranskripDTO(Transkrip transkrip) {
        return TranskripDTO.builder()
                .id(transkrip.getId())
                .semester(transkrip.getSemester())
                .listMKTerambil(transkrip.getListMKTerambil().stream().map((lmkt) -> mapToMataKuliahTerambilDTO(lmkt))
                        .collect(Collectors.toList()))
                .totalSKS(transkrip.getTotalSKS())
                .ips(transkrip.getIps())
                .mahasiswa(transkrip.getMahasiswa())
                .build();
    }
}
