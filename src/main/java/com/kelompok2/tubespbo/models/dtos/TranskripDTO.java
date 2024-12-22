package com.kelompok2.tubespbo.models.dtos;

import java.util.List;

import com.kelompok2.tubespbo.models.Mahasiswa;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranskripDTO {
    private int id;    
    private int semester;
    private List<MataKuliahTerambilDTO> listMKTerambil;
    private int totalSKS;
    private double ips; 
    private Mahasiswa mahasiswa;
}
