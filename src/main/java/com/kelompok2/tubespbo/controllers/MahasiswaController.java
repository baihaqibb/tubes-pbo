package com.kelompok2.tubespbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kelompok2.tubespbo.models.UserEntity;
import com.kelompok2.tubespbo.models.dtos.MahasiswaDTO;
import com.kelompok2.tubespbo.security.SecurityUtil;
import com.kelompok2.tubespbo.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/mahasiswa")
public class MahasiswaController {
    
    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String mahasiswaList(Model model) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/home?authError";
        }
        List<MahasiswaDTO> mahasiswaList = userService.findAllMahasiswa();
        model.addAttribute("mahasiswaList", mahasiswaList);
        return "mahasiswa/index";
    }

    @GetMapping("/{id}")
    public String matahasiswaDetail(Model model, @PathVariable String id) {
        try{
            int idInt = Integer.parseInt(id);
            String currentAuthority = SecurityUtil.getSessionAuthority();
            if (!currentAuthority.equals("ADMIN")) {
                return "redirect:/home?authError";
            }
            MahasiswaDTO mahasiswaDTO = userService.findMahasiswaById(idInt);
            if (mahasiswaDTO == null) {
                return "redirect:/mahasiswa";
            }
            model.addAttribute("mhs", mahasiswaDTO);
            return "mahasiswa/detail";
        } catch (NumberFormatException e) {
            return "redirect:/home?typeError";
        }
    }

    @GetMapping("/{id}/edit")
    public String editMahasiswaForm(Model model, @PathVariable String id) {
        try{
            int idInt = Integer.parseInt(id);
            String currentAuthority = SecurityUtil.getSessionAuthority();
            if (!currentAuthority.equals("ADMIN")) {
                return "redirect:/home?authError";
            }
            MahasiswaDTO mahasiswaDTO = userService.findMahasiswaById(idInt);
            if (mahasiswaDTO == null) {
                return "redirect:/mahasiswa";
            }
            model.addAttribute("mhs", mahasiswaDTO);
            return "mahasiswa/edit";
        } catch (NumberFormatException e) {
            return "redirect:/home?typeError";
        }
    }

    @PostMapping("/{id}/edit")
    public String editMahasiswaQuery(Model model, 
                                      @PathVariable int id, 
                                      @Valid @ModelAttribute("mhs") MahasiswaDTO mahasiswaDTO,
                                      BindingResult result) 
    {
        UserEntity existingEmail = userService.findUserByEmail(mahasiswaDTO.getEmail());
        if (existingEmail != null && existingEmail.getEmail() != null && !existingEmail.getEmail().isBlank() && existingEmail.getId() != mahasiswaDTO.getId()) {
            result.rejectValue("email", "error.email", "The email is already in use with another user!");
        }
        MahasiswaDTO existingNim = userService.findMahasiswaByNim(mahasiswaDTO.getNim());
        if (existingNim != null && existingNim.getNim() != null && !existingNim.getNim().isBlank() && existingNim.getId() != mahasiswaDTO.getId()) {
            result.rejectValue("nim", "error.nim", "The NIM is already in use with another user!");
        }
        if (result.hasErrors()) {
            model.addAttribute("mhs", mahasiswaDTO);
            return "mahasiswa/edit";
        }
        mahasiswaDTO.setId(id);
        userService.updateMahasiswa(mahasiswaDTO);
        return "redirect:/mahasiswa";
    }

    @GetMapping("/{id}/delete")
    public String deleteMahasiswa(@PathVariable String id) {
        try{
            int idInt = Integer.parseInt(id);
            String currentAuthority = SecurityUtil.getSessionAuthority();
            if (!currentAuthority.equals("ADMIN")) {
                return "redirect:/home?authError";
            }
            userService.deleteMahasiswaById(idInt);
            return "redirect:/mahasiswa";
        } catch (NumberFormatException e) {
            return "redirect:/home?typeError";
        }
    }
    
}
