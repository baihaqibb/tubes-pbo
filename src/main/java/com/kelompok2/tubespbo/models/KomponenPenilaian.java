package com.kelompok2.tubespbo.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="komponen_penilaian")
public class KomponenPenilaian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String tipe;
    @Column(nullable = false)
    private double bobot;
    @Column(nullable = false)
    private double nilai;
    @ManyToOne
    @JoinColumn(name = "mata_kuliah_terambil_id", nullable = false)
    private MataKuliahTerambil mataKuliahTerambil;
}
