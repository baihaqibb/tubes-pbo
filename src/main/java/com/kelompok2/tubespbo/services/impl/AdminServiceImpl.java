package com.kelompok2.tubespbo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.Admin;
import com.kelompok2.tubespbo.models.dtos.AdminDTO;
import com.kelompok2.tubespbo.repositories.AdminRepository;
import com.kelompok2.tubespbo.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
    
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public void saveAdmin(AdminDTO adminDTO) {
        Admin admin = new Admin();
        admin.setUsername(adminDTO.getUsername());
        admin.setEmail(adminDTO.getEmail());
        admin.setPassword(adminDTO.getPassword());
        admin.setFullName(adminDTO.getFullName());
        admin.setNip(adminDTO.getNip());
        adminRepository.save(admin);
    }

    @Override
    public AdminDTO findByEmail(String email) {
        try{
            Admin admin = adminRepository.findByEmail(email).get();
            return AdminDTO.builder()
                .email(admin.getEmail())
                .username(admin.getUsername())
                .password(admin.getPassword())
                .fullName(admin.getFullName())
                .nip(admin.getNip())
                .build();
        } catch (Exception e) {
            return null;
        }
        
    }

}
