package com.kelompok2.tubespbo.models.dtos;

import java.util.ArrayList;
import java.util.List;

import com.kelompok2.tubespbo.models.MataKuliahTerambil;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TranskripDTO {
    private int id;    
    private int semester;
    private List<MataKuliahTerambil> listMKTerambil = new ArrayList<>();
    private int totalSKS;
    private double ips; 
}
