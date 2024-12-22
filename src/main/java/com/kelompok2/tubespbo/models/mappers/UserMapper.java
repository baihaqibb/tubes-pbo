package com.kelompok2.tubespbo.models.mappers;

import java.util.stream.Collectors;

import static com.kelompok2.tubespbo.models.mappers.TranskripMapper.mapToTranskripDTO;

import com.kelompok2.tubespbo.models.Admin;
import com.kelompok2.tubespbo.models.Mahasiswa;
import com.kelompok2.tubespbo.models.dtos.AdminDTO;
import com.kelompok2.tubespbo.models.dtos.MahasiswaDTO;

public class UserMapper {

    public static Admin mapToAdmin(AdminDTO adminDTO) {
        return Admin.builder()
            .id(adminDTO.getId())
            .email(adminDTO.getEmail())
            .username(adminDTO.getUsername())
            .password(adminDTO.getPassword())
            .fullName(adminDTO.getFullName())
            .role(adminDTO.getRole())
            .nip(adminDTO.getNip())
            .build();
    }

    public static AdminDTO mapToAdminDTO(Admin admin) {
        return AdminDTO.builder()
            .id(admin.getId())
            .email(admin.getEmail())
            .username(admin.getUsername())
            .password(admin.getPassword())
            .fullName(admin.getFullName())
            .role(admin.getRole())
            .nip(admin.getNip())
            .build();
    }

    public static Mahasiswa mapToMahasiswa(MahasiswaDTO mahasiswaDTO) {
        return Mahasiswa.builder()
                .id(mahasiswaDTO.getId())
                .email(mahasiswaDTO.getEmail())
                .username(mahasiswaDTO.getUsername())
                .password(mahasiswaDTO.getPassword())
                .fullName(mahasiswaDTO.getFullName())
                .role(mahasiswaDTO.getRole())
                .nim(mahasiswaDTO.getNim())
                .kelas(mahasiswaDTO.getKelas())
                .rencanaStudi(mahasiswaDTO.getRencanaStudi())
                .build();
    }

    public static MahasiswaDTO mapToMahasiswaDTO(Mahasiswa mahasiswa) {
        return MahasiswaDTO.builder()
                .id(mahasiswa.getId())
                .email(mahasiswa.getEmail())
                .username(mahasiswa.getUsername())
                .password(mahasiswa.getPassword())
                .fullName(mahasiswa.getFullName())
                .role(mahasiswa.getRole())
                .nim(mahasiswa.getNim())
                .kelas(mahasiswa.getKelas())
                .rencanaStudi(mahasiswa.getRencanaStudi())
                .transkrip(mahasiswa.getTranskrip().stream().map((t) -> mapToTranskripDTO(t)).collect(Collectors.toList()))
                .build();
    }

}
