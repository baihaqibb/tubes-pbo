package com.kelompok2.tubespbo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kelompok2.tubespbo.models.Admin;
import com.kelompok2.tubespbo.models.dtos.AdminDTO;
import com.kelompok2.tubespbo.services.AdminService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

@   GetMapping({ "", "/" })
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
        AdminDTO existingUser = adminService.findByEmail(adminDTO.getEmail());
        if (existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isBlank()) {
            result.rejectValue("email", "This username/email has already been used by an user");
        }
        if (result.hasErrors()) {
            model.addAttribute("adm", adminDTO);
            return "admin/register";
        }
        adminService.saveAdmin(adminDTO);
        return "redirect:/admin";
    }
    
}
