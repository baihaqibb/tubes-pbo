package com.kelompok2.tubespbo.models.dtos;

import java.util.ArrayList;
import java.util.List;

import com.kelompok2.tubespbo.models.MataKuliah;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RencanaStudiDTO {
    private int id;
    private int semester;
    private List<MataKuliah> listMK = new ArrayList<>();
    private int totalSKS;
}

