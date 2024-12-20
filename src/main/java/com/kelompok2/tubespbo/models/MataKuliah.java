package com.kelompok2.tubespbo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="mata_kuliah")
public class MataKuliah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String kode;
    @Column(nullable = false)
    private String nama;
    @Column(nullable = false)
    private int sks;
    @OneToMany(mappedBy = "mataKuliah", cascade = CascadeType.REMOVE)
    private final List<KomponenPenilaian> komponenPenilaian = new ArrayList<>();
    @Column(nullable = false)
    private double nilai;
    @Column(nullable = false)
    private char indexNilai;
    @ManyToMany(mappedBy = "listMK")
    private List<RencanaStudi> listRS = new ArrayList<>();
}