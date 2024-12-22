package com.kelompok2.tubespbo.services.impl;

import static com.kelompok2.tubespbo.models.mappers.UserMapper.mapToAdmin;
import static com.kelompok2.tubespbo.models.mappers.UserMapper.mapToAdminDTO;
import static com.kelompok2.tubespbo.models.mappers.UserMapper.mapToMahasiswa;
import static com.kelompok2.tubespbo.models.mappers.UserMapper.mapToMahasiswaDTO;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.Admin;
import com.kelompok2.tubespbo.models.Mahasiswa;
import com.kelompok2.tubespbo.models.RencanaStudi;
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
        RencanaStudi rencanaStudi = RencanaStudi.builder()
                .semester(1)
                .totalSKS(0)
                .mahasiswa(mahasiswa)
                .build();
        mahasiswa.setRencanaStudi(rencanaStudi);
        mahasiswa.setPassword(passwordEncoder.encode(mahasiswaDTO.getPassword()));
        mahasiswa.setRole(roleRepository.findByRole("MAHASISWA").get());
        mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public void updateMahasiswa(MahasiswaDTO mahasiswaDTO) {
        Mahasiswa mahasiswa = mapToMahasiswa(mahasiswaDTO);
        mahasiswa.setRole(roleRepository.findByRole("MAHASISWA").get());
        mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public void deleteMahasiswaById(int id) {
        mahasiswaRepository.deleteById(id);
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
    public List<MahasiswaDTO> findAllMahasiswa() {
        List<Mahasiswa> mahasiswas = mahasiswaRepository.findAll(Sort.by(Sort.Direction.ASC, "nim"));
        return mahasiswas.stream().map(mhs -> mapToMahasiswaDTO(mhs)).collect(Collectors.toList());
    }

    @Override
    public MahasiswaDTO findMahasiswaById(int id) {
        try {
            Mahasiswa mahasiswa = mahasiswaRepository.findById(id).get();
            return mapToMahasiswaDTO(mahasiswa);
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
