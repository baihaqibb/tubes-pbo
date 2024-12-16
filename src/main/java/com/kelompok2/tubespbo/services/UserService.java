package com.kelompok2.tubespbo.services;

import com.kelompok2.tubespbo.models.UserEntity;
import com.kelompok2.tubespbo.models.dtos.AdminDTO;
import com.kelompok2.tubespbo.models.dtos.MahasiswaDTO;

public interface UserService {
    void createAdmin(AdminDTO adminDTO);
    void createMahasiswa(MahasiswaDTO mahasiswaDTO);
    UserEntity findUserByEmail(String email);
    UserEntity findUserByUsername(String username);
    AdminDTO findAdminByEmail(String email);
    AdminDTO findAdminByUsername(String username);
    AdminDTO findAdminByNip(String nip);
    MahasiswaDTO findMahasiswaByEmail(String email);
    MahasiswaDTO findMahasiswaByUsername(String username);
    MahasiswaDTO findMahasiswaByNim(String nim);
}
