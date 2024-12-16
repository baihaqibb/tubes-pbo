package com.kelompok2.tubespbo.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "admin")
public class Admin extends UserEntity{
    @Column(unique = true, nullable = false)
    private String nip = "";
}
