package com.kelompok2.tubespbo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kelompok2.tubespbo.models.dtos.MataKuliahTerambilDTO;
import com.kelompok2.tubespbo.security.SecurityUtil;
import com.kelompok2.tubespbo.services.MataKuliahTerambilService;


@Controller
@RequestMapping("/mata_kuliah_terambil")
public class MataKuliahTerambilController {

    @Autowired
    private MataKuliahTerambilService mataKuliahTerambilService;
    
    @GetMapping({"", "/"})
    public String redirectToHome() {
        return "redirect:/";
    }
    
    @GetMapping("{mkt_id}")
    public String mataKuliahTerambilDetail(Model model, @PathVariable int mkt_id) {
        MataKuliahTerambilDTO mkt = mataKuliahTerambilService.findMataKuliahTerambilById(mkt_id);
        String currentAuthority = SecurityUtil.getSessionAuthority();
        model.addAttribute("role", currentAuthority);
        model.addAttribute("mkt", mkt);
        return "mata_kuliah_terambil/detail";
    }
    
    
}
