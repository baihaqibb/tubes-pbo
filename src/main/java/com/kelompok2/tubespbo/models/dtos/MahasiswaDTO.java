package com.kelompok2.tubespbo.models.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MahasiswaDTO {
    private int id;
    @Size(max = 16, message = "Username must be less than or equals to 16 characters!")
    @NotBlank(message = "Username must not be blank!")
    private String username;
    @Email
    @NotBlank(message = "Email must not be blank!")
    private String email;
    @Size(min = 6, message = "Password must be more than or equals to 6 characters")
    @NotBlank(message = "Password must not be blank!")
    private String password;
    @NotBlank(message = "Full Name must not be blank!")
    private String fullName;
    @NotBlank(message = "NIM must not be blank!")
    private String nim;
    @NotBlank(message = "Kelas must not be blank!")
    private String kelas;
}
