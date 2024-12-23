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
@Table(name = "mata_kuliah_terambil")
public class MataKuliahTerambil {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Column(nullable = false)
    private String kode;
    @Column(nullable = false)
    private String nama;
    @Column(nullable = false)
    private int sks;
    @OneToMany(mappedBy = "mataKuliahTerambil", cascade = CascadeType.REMOVE)
    private final List<KomponenPenilaian> komponenPenilaian = new ArrayList<>();
    @Column(nullable = false)
    private double nilai;
    @Column(nullable = false)
    private char indexNilai;
    @ManyToOne
    @JoinColumn(name = "transkrip_id", nullable = false)
    private Transkrip transkrip;

    public void countNilai() {
        double totalBobot = 0;
        double totalNilai = 0;
        for (KomponenPenilaian kp : komponenPenilaian) {
            totalNilai += kp.getNilai() * kp.getBobot();
            totalBobot += kp.getBobot();
        }
        if (totalBobot != 0) {
            this.nilai = totalNilai/totalBobot;
            if (this.nilai > 80) {
                this.indexNilai = 'A';
            } else if (this.nilai > 70 && 80 >= this.nilai) {
                this.indexNilai = 'B';
            } else if (this.nilai > 60 && 70 >= this.nilai) {
                this.indexNilai = 'C';
            } else if (this.nilai > 50 && 60 >= this.nilai) {
                this.indexNilai = 'D';
            } else if (this.nilai >= 0 && 50 >= this.nilai) {
                this.indexNilai = 'E';
            } else {
                this.indexNilai = '-';
            }
        } else {
            this.nilai = 0;
            this.indexNilai = '-';
        }
    }
}
