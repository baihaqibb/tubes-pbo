package com.kelompok2.tubespbo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.kelompok2.tubespbo.models.KomponenPenilaian;
import com.kelompok2.tubespbo.repositories.KomponenPenilaianRepository;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/komponen_penilaian")
public class KomponenPenilaianController {

    @Autowired
    private KomponenPenilaianRepository komponenPenilaianRepo;

    @GetMapping("/create")
    public String createKomponenPenilaian(Model model, @RequestParam int mkid) {
        KomponenPenilaian komponenPenilaian = new KomponenPenilaian();
        model.addAttribute("komponenPenilaianDTO", komponenPenilaian);
        model.addAttribute("mkid", mkid);
        return "komponen_penilaian/create";
    }
    
}
