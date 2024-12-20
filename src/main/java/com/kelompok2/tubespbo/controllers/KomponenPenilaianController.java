package com.kelompok2.tubespbo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.kelompok2.tubespbo.models.KomponenPenilaian;
import com.kelompok2.tubespbo.models.dtos.KomponenPenilaianDTO;
import com.kelompok2.tubespbo.services.KomponenPenilaianService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/komponen_penilaian")
public class KomponenPenilaianController {

    @Autowired
    private KomponenPenilaianService komponenPenilaianService;

    @GetMapping("{mkid}/create")
    public String createKomponenPenilaianForm(Model model, @PathVariable int mkid) {
        KomponenPenilaian komponenPenilaian = new KomponenPenilaian();
        model.addAttribute("mkid", mkid);
        model.addAttribute("kp", komponenPenilaian);
        return "komponen_penilaian/create";
    }

    @PostMapping("{mkid}/create")
    public String postMethodName(Model model, 
                                 @PathVariable int mkid, 
                                 @Valid @ModelAttribute("kp") KomponenPenilaianDTO komponenPenilaianDTO, 
                                 BindingResult result) 
    {
        if (result.hasErrors()) {
            model.addAttribute("kp", komponenPenilaianDTO);
            return "komponen_penilaian/create";
        }
        komponenPenilaianService.createKomponenPenilaian(mkid, komponenPenilaianDTO);
        return "redirect:/mata_kuliah/" + mkid;
    }

    @GetMapping("{mkid}/{id}/edit")
    public String editKomponenPenilaianForm(Model model, @PathVariable int mkid, @PathVariable int id) {
        KomponenPenilaianDTO komponenPenilaianDTO = komponenPenilaianService.findKomponenPenilaianById(id);
        model.addAttribute("mkid", mkid);
        model.addAttribute("kp", komponenPenilaianDTO);
        return "komponen_penilaian/edit";
    }

    @PostMapping("{mkid}/{id}/edit")
    public String editMataKuliahQuery(Model model, 
                                      @PathVariable int mkid,
                                      @PathVariable int id, 
                                      @Valid @ModelAttribute("kp") KomponenPenilaianDTO komponenPenilaianDTO,
                                      BindingResult result) 
    {
        if (result.hasErrors()) {
            model.addAttribute("mkid", mkid);
            model.addAttribute("kp", komponenPenilaianDTO);
            return "komponen_penilaian/edit";
        }
        KomponenPenilaianDTO komponenPenilaianDTO2 = komponenPenilaianService.findKomponenPenilaianById(id);
        komponenPenilaianDTO.setId(id);
        komponenPenilaianDTO.setMataKuliahTerambil(komponenPenilaianDTO2.getMataKuliahTerambil());
        komponenPenilaianService.updateKomponenPenilaian(komponenPenilaianDTO);
        return "redirect:/mata_kuliah/" + mkid;
    }

    @GetMapping("{mkid}/{id}/delete")
    public String getMethodName(@PathVariable int mkid, @PathVariable int id) {
        komponenPenilaianService.deleteKomponenPenilaianByID(id);
        return "redirect:/mata_kuliah/" + mkid;
    }
    
}
