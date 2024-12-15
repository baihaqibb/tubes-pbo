package com.kelompok2.tubespbo.dtos;

import lombok.*;

@Data
@Builder
public class KomponenPenilaianDTO {
    private int id;
    private String tipe;
    private double bobot;
}