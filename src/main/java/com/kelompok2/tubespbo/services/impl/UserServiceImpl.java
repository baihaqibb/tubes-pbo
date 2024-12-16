package com.kelompok2.tubespbo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.Admin;
import com.kelompok2.tubespbo.models.Mahasiswa;
import com.kelompok2.tubespbo.models.UserEntity;
import com.kelompok2.tubespbo.models.dtos.AdminDTO;
import com.kelompok2.tubespbo.models.dtos.MahasiswaDTO;
import com.kelompok2.tubespbo.repositories.AdminRepository;
import com.kelompok2.tubespbo.repositories.MahasiswaRepository;
import com.kelompok2.tubespbo.repositories.RoleRepository;
import com.kelompok2.tubespbo.repositories.UserRepository;
import com.kelompok2.tubespbo.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private MahasiswaRepository mahasiswaRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Admin mapToAdmin(AdminDTO adminDTO) {
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

    private AdminDTO mapToAdminDTO(Admin admin) {
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

    private Mahasiswa mapToMahasiswa(MahasiswaDTO mahasiswaDTO) {
        return Mahasiswa.builder()
                .id(mahasiswaDTO.getId())
                .email(mahasiswaDTO.getEmail())
                .username(mahasiswaDTO.getUsername())
                .password(mahasiswaDTO.getPassword())
                .fullName(mahasiswaDTO.getFullName())
                .role(mahasiswaDTO.getRole())
                .nim(mahasiswaDTO.getNim())
                .kelas(mahasiswaDTO.getKelas())
                .build();
    }

    private MahasiswaDTO mapToMahasiswaDTO(Mahasiswa mahasiswa) {
        return MahasiswaDTO.builder()
                .id(mahasiswa.getId())
                .email(mahasiswa.getEmail())
                .username(mahasiswa.getUsername())
                .password(mahasiswa.getPassword())
                .fullName(mahasiswa.getFullName())
                .role(mahasiswa.getRole())
                .nim(mahasiswa.getNim())
                .kelas(mahasiswa.getKelas())
                .build();
    }


    @Override
    public void createAdmin(AdminDTO adminDTO) {
        Admin admin = mapToAdmin(adminDTO);
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        admin.setRole(roleRepository.findByRole("ADMIN").get());
        adminRepository.save(admin);
    }

    @Override
    public void createMahasiswa(MahasiswaDTO mahasiswaDTO) {
        Mahasiswa mahasiswa = mapToMahasiswa(mahasiswaDTO);
        mahasiswa.setPassword(passwordEncoder.encode(mahasiswaDTO.getPassword()));
        mahasiswa.setRole(roleRepository.findByRole("MAHASISWA").get());
        mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        try {
            return userRepository.findByEmail(email).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username).get();
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public AdminDTO findAdminByEmail(String email) {
        try{
            Admin admin = adminRepository.findByEmail(email).get();
            return mapToAdminDTO(admin);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AdminDTO findAdminByUsername(String username) {
        try{
            Admin admin = adminRepository.findByUsername(username).get();
            return mapToAdminDTO(admin);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public AdminDTO findAdminByNip(String nip) {
        try{
            Admin admin = adminRepository.findByNip(nip).get();
            return mapToAdminDTO(admin);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MahasiswaDTO findMahasiswaByEmail(String email) {
        try {
            Mahasiswa mahasiswa = mahasiswaRepository.findByEmail(email).get();
            return mapToMahasiswaDTO(mahasiswa);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MahasiswaDTO findMahasiswaByUsername(String username) {
        try {
            Mahasiswa mahasiswa = mahasiswaRepository.findByUsername(username).get();
            return mapToMahasiswaDTO(mahasiswa);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public MahasiswaDTO findMahasiswaByNim(String nim) {
        try {
            Mahasiswa mahasiswa = mahasiswaRepository.findByNim(nim).get();
            return mapToMahasiswaDTO(mahasiswa);
        } catch (Exception e) {
            return null;
        }
    }

}
