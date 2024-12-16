package com.kelompok2.tubespbo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kelompok2.tubespbo.models.Mahasiswa;
import com.kelompok2.tubespbo.models.UserEntity;
import com.kelompok2.tubespbo.models.dtos.MahasiswaDTO;
import com.kelompok2.tubespbo.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    
    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String mahasiswaHome() {
        return "mahasiswa/index";
    }
    
    @GetMapping("register")
    public String registerMahasiswa(Model model) {
        Mahasiswa mahasiswa = new Mahasiswa();
        model.addAttribute("mhs", mahasiswa);
        return "mahasiswa/register";
    }

    @PostMapping("register")
    public String postMethodName(Model model, 
        @Valid @ModelAttribute("mhs") MahasiswaDTO mahasiswaDTO,
        BindingResult result) {
        UserEntity existingEmail = userService.findUserByEmail(mahasiswaDTO.getEmail());
        if (existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isBlank()) {
            result.rejectValue("email", "error.email", "The email is already in use with another user!");
        }
        UserEntity existingUsername = userService.findUserByUsername(mahasiswaDTO.getUsername());
        if (existingUsername != null && existingUsername.getEmail() != null && !existingUsername.getEmail().isBlank()) {
            result.rejectValue("username", "error.username", "The username is already in use with another user!");
        }
        MahasiswaDTO existingNim = userService.findMahasiswaByNim(mahasiswaDTO.getNim());
        if (existingNim != null && existingNim.getNim() != null && !existingNim.getNim().isBlank()) {
            result.rejectValue("nim", "error.nim", "The NIM is already in use with another user!");
        }
        if (result.hasErrors()) {
            model.addAttribute("mhs", mahasiswaDTO);
            return "mahasiswa/register";
        }
        userService.createMahasiswa(mahasiswaDTO);
        return "redirect:/mahasiswa?success";
    }

}
