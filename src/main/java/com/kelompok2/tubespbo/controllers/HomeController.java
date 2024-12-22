package com.kelompok2.tubespbo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.kelompok2.tubespbo.models.dtos.AdminDTO;
import com.kelompok2.tubespbo.models.dtos.MahasiswaDTO;
import com.kelompok2.tubespbo.security.SecurityUtil;
import com.kelompok2.tubespbo.services.UserService;


@Controller
public class HomeController {

    @Autowired
    public UserService userService;

    @GetMapping({"", "/"})
    public String redirectHome() {
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(Model model) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (currentAuthority.equals("ADMIN")) {
            AdminDTO adminDTO = userService.findAdminByUsername(SecurityUtil.getSessionUser());
            model.addAttribute("user", adminDTO);
            return "home/admin";
        } else if (currentAuthority.equals("MAHASISWA")) {
            MahasiswaDTO mahasiswaDTO = userService.findMahasiswaByUsername(SecurityUtil.getSessionUser());
            model.addAttribute("user", mahasiswaDTO);
            return "home/mahasiswa";
        }
        model.addAttribute("role", currentAuthority);
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    
}
