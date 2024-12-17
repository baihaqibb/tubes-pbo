package com.kelompok2.tubespbo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kelompok2.tubespbo.models.Admin;
import com.kelompok2.tubespbo.models.UserEntity;
import com.kelompok2.tubespbo.models.dtos.AdminDTO;
import com.kelompok2.tubespbo.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping({ "", "/" })
    public String adminHome(Model model) {
        return "admin/index";
    }

    @GetMapping("register")
    public String registerAdmin(Model model) {
        Admin admin = new Admin();
        model.addAttribute("adm", admin);
        return "admin/register";
    }

    @PostMapping("register")
    public String postMethodName(Model model, 
        @Valid @ModelAttribute("adm") AdminDTO adminDTO,
        BindingResult result) {
        UserEntity existingEmail = userService.findUserByEmail(adminDTO.getEmail());
        if (existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isBlank()) {
            result.rejectValue("email", "error.email", "The email is already in use with another user!");
        }
        UserEntity existingUsername = userService.findUserByUsername(adminDTO.getUsername());
        if (existingUsername != null && existingUsername.getEmail() != null && !existingUsername.getEmail().isBlank()) {
            result.rejectValue("username", "error.username", "The username is already in use with another user!");
        }
        AdminDTO existingNip = userService.findAdminByNip(adminDTO.getNip());
        if (existingNip != null && existingNip.getNip() != null && !existingNip.getNip().isBlank()) {
            result.rejectValue("nim", "error.nim", "The NIM is already in use with another user!");
        }
        if (result.hasErrors()) {
            model.addAttribute("adm", adminDTO);
            return "admin/register";
        }
        userService.createAdmin(adminDTO);
        return "redirect:/admin?success";
    }
    
}
