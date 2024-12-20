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
@Table(name = "rencana_studi")
public class RencanaStudi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "semester", nullable = false)
    private int semester;
    @ManyToMany
    @JoinTable(name = "rencana_studi_mata_kuliah", joinColumns = {
        @JoinColumn(name = "rencana_studi_id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "mata_kuliah_id")
    })
    private List<MataKuliah> listMK = new ArrayList<>();
    @Column(name = "total_sks", nullable = false)
    private int totalSKS;


}


