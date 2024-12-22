package com.kelompok2.tubespbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kelompok2.tubespbo.models.dtos.MataKuliahDTO;
import com.kelompok2.tubespbo.models.dtos.TranskripDTO;
import com.kelompok2.tubespbo.services.TranskripService;


@Controller
@RequestMapping("/transkrip")
public class TranskripController {

    @Autowired
    private TranskripService transkripService;
    
    @GetMapping({"", "/"})
    public String getMethodName(Model model) {
        List<TranskripDTO> tsList = transkripService.findAllTranskrip();
        model.addAttribute("tsList", tsList);
        return "transkrip/index";
    }
}
