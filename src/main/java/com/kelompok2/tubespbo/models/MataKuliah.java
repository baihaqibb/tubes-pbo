package com.kelompok2.tubespbo.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
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
}