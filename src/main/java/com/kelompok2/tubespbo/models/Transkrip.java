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
@Table(name = "transkrip")
public class Transkrip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;    
    @Column(name = "semester", nullable = false)
    private int semester;
    @OneToMany(mappedBy = "transkrip", cascade = CascadeType.REMOVE)
    private List<MataKuliahTerambil> listMKTerambil = new ArrayList<>();
    @Column(name = "total_sks", nullable = false)
    private int totalSKS;
    @Column(name = "ips", nullable = false)
    private double ips;
}
