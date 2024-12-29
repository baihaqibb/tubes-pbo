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
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Column(unique = true, nullable = false)
    private String kode;
    @Column(nullable = false)
    private String nama;
    @Column(nullable = false)
    private int sks;
    @ManyToMany(mappedBy = "listMK")
    private final List<RencanaStudi> rencanaStudi = new ArrayList<>();
}