package com.kelompok2.tubespbo.models.dtos;

import java.util.List;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MataKuliahDTO {
    private int id;
    @NotBlank(message = "Kode must not be blank!")
    private String kode;
    @NotBlank(message = "Nama must not be blank!")
    private String nama;
    @Min(value=1, message = "SKS must be in a following range: (1 - 99)")
    @Max(value=99, message = "SKS must be in a following range: (1 - 99)")
    private int sks;
    private List<KomponenPenilaianDTO> komponenPenilaian;
}
