package com.kelompok2.tubespbo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "mata_kuliah_terambil")
public class MataKuliahTerambil extends MataKuliah {
    @OneToMany(mappedBy = "mataKuliahTerambil", cascade = CascadeType.REMOVE)
    private final List<KomponenPenilaian> komponenPenilaian = new ArrayList<>();
    @Column(nullable = false)
    private double nilai;
    @Column(nullable = false)
    private char indexNilai;
}
