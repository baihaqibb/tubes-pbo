package com.kelompok2.tubespbo.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MataKuliahDTO {

    @NotBlank(message = "Please fill this field")
    private String kode;

    @NotBlank(message = "Please fill this field")
    private String nama;
    
    private int sks;
}
