package com.kelompok2.tubespbo.models.dtos;
import java.util.List;

import com.kelompok2.tubespbo.models.Transkrip;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MataKuliahTerambilDTO {
    private int id;
    @NotBlank(message = "Kode must not be blank!")
    private String kode;
    @NotBlank(message = "Nama must not be blank!")
    private String nama;
    @Min(value=1, message = "SKS must be in a following range: (1 - 99)")
    @Max(value=99, message = "SKS must be in a following range: (1 - 99)")
    private int sks;
    private List<KomponenPenilaianDTO> komponenPenilaian;
    private double nilai;
    private char indexNilai;
    private Transkrip transkrip;
}
