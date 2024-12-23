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
    private final List<MataKuliahTerambil> listMKTerambil = new ArrayList<>();
    @Column(name = "total_sks", nullable = false)
    private int totalSKS;
    @Column(name = "ips", nullable = false)
    private double ips;
    @ManyToOne
    @JoinColumn(name = "mahasiswa_id", nullable = false)
    private Mahasiswa mahasiswa;

    public void countIPS() {
        double totalNilai = 0;
        for (MataKuliahTerambil mkt : listMKTerambil) {
            switch (mkt.getIndexNilai()) {
                case 'A':
                    totalNilai += 4 * mkt.getSks();
                    break;
                case 'B':
                    totalNilai += 3 * mkt.getSks();
                    break;
                case 'C':
                    totalNilai += 2 * mkt.getSks();
                    break;
                case 'D':
                    totalNilai += 1 * mkt.getSks();
                    break;
                case 'E':
                    totalNilai += 0 * mkt.getSks();
                    break;
                default:
                    break;
            }
        }
        if (this.totalSKS != 0) {
            this.ips = totalNilai/this.totalSKS;
        } else {
            this.ips = 0;
        }
    }
}
