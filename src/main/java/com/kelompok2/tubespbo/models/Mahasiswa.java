package com.kelompok2.tubespbo.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mahasiswa")
public class Mahasiswa extends UserEntity {
    @Column(unique = true, nullable = false)
    private String nim;
    @Column(nullable = false)
    private String kelas;
}
