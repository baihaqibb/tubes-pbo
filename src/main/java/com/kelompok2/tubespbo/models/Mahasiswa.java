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
@Table(name = "mahasiswa")
public class Mahasiswa extends UserEntity {
    @Column(unique = true, nullable = false)
    private String nim;
    @Column(nullable = false)
    private String kelas;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "mahasiswa_rencana_studi", joinColumns = {
        @JoinColumn(name = "mahasiswa_id", referencedColumnName = "id")
    }, inverseJoinColumns = {
        @JoinColumn(name = "rencana_studi_id", referencedColumnName = "id")
    })
    private RencanaStudi rencanaStudi;
    @OneToMany(cascade = CascadeType.ALL)
    private final List<Transkrip> transkrip = new ArrayList<>();
}
