package com.kelompok2.tubespbo.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MataKuliahDTO {

    @NotBlank(message = "Please fill this field")
    private String kode;

    @NotBlank(message = "Please fill this field")
    private String nama;

    @Min(1)
    @Max(99)
    private int sks;
}
