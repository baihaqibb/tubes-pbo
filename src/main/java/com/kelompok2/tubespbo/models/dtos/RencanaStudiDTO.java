package com.kelompok2.tubespbo.models.dtos;

import java.util.List;

import com.kelompok2.tubespbo.models.Mahasiswa;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RencanaStudiDTO {
    private int id;
    private int semester;
    private List<MataKuliahDTO> listMK;
    private int totalSKS;
    private Mahasiswa mahasiswa;
}

