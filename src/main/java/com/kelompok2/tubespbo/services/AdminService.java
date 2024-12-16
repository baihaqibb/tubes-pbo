package com.kelompok2.tubespbo.services;

import com.kelompok2.tubespbo.models.UserEntity;
import com.kelompok2.tubespbo.models.dtos.AdminDTO;

public interface AdminService {
    void saveAdmin(AdminDTO adminDTO);

    AdminDTO findByEmail(String email);
}
