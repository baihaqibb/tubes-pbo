package com.kelompok2.tubespbo.models.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDTO {
    private int id;
    @Size(max = 16, message = "Username must be less than or equal to 16 characters!")
    @NotBlank(message = "Username must not be blank!")
    private String username;
    @Email
    @NotBlank(message = "Email must not be blank!")
    private String email;
    @Size(min = 6, message = "Password must be more than or equal to 6 characters")
    @NotBlank(message = "Password must not be blank!")
    private String password;
    @NotBlank(message = "Full Name must not be blank!")
    private String fullName;
    @NotBlank(message = "NIP must not be blank!")
    private String nip;
}
