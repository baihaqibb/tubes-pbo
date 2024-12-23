package com.kelompok2.tubespbo.models.dtos;

import com.kelompok2.tubespbo.models.MataKuliahTerambil;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KomponenPenilaianDTO {
    private int id;
    @NotBlank(message = "Tipe must not be blank!")
    private String tipe;
    @Min(value=0, message = "Bobot must be in a following range: (0.0 - 1.0)")
    @Max(value=1, message = "Bobot must be in a following range: (0.0 - 1.0)")
    private double bobot;
    @Min(value = 0, message = "Nilai must be in a following range: (0.0 - 100.0)")
    @Max(value = 100, message = "Nilai must be in a following range: (0.0 - 100.0)")
    private double nilai;
    private MataKuliahTerambil mataKuliahTerambil;
}