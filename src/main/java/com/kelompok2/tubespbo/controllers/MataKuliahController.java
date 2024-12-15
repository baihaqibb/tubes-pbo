package com.kelompok2.tubespbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kelompok2.tubespbo.dtos.MataKuliahDTO;
import com.kelompok2.tubespbo.services.MataKuliahService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/mata_kuliah")
public class MataKuliahController {

    @Autowired
    private MataKuliahService mataKuliahService;

    @GetMapping({ "", "/" })
    public String mataKuliahList(Model model) {
        List<MataKuliahDTO> mataKuliahList = mataKuliahService.findAllMataKuliah();
        model.addAttribute("mataKuliahList", mataKuliahList);
        return "mata_kuliah/index";
    }

    @GetMapping("/search")
    public String getMethodName(Model model, @RequestParam(value = "q") String query) {
        List<MataKuliahDTO> mataKuliahList = mataKuliahService.searchMataKuliahs(query);
        model.addAttribute("mataKuliahList", mataKuliahList);
        return "mata_kuliah/index";
    }
    

    @GetMapping("/{id}")
    public String mataKuliahDetail(Model model, @PathVariable int id) {
        try {
            MataKuliahDTO mataKuliahDTO = mataKuliahService.findMataKuliahById(id);
            model.addAttribute("mk", mataKuliahDTO);
            return "mata_kuliah/detail";
        } catch (Exception e) {
            return "mata_kuliah/index";
        }
    }

    @GetMapping("/create")
    public String createMataKuliahForm(Model model) {
        MataKuliahDTO mataKuliah = new MataKuliahDTO();
        model.addAttribute("mk", mataKuliah);
        return "mata_kuliah/create";
    }

    @PostMapping("/create")
    public String createMataKuliahQuery(Model model, 
                                        @Valid @ModelAttribute("mk") MataKuliahDTO mataKuliahDTO, 
                                        BindingResult result) 
    {
        try {
            mataKuliahService.findMataKuliahByKode(mataKuliahDTO.getKode());
            result.addError(new FieldError("mataKuliahDTO", 
                                               "kode",
                                               mataKuliahDTO.getKode(), 
                                               false, 
                                               null, 
                                               null, 
                                               "Kode is already in use!"));
            
        } catch (Exception e) {}
        if (result.hasErrors()) {
            return "mata_kuliah/create";
        }
        mataKuliahService.saveMataKuliah(mataKuliahDTO);
        return "redirect:/mata_kuliah";
    }

    @GetMapping("/{id}/edit")
    public String editMataKuliahForm(Model model, @PathVariable int id) {
        try {
            MataKuliahDTO mataKuliah = mataKuliahService.findMataKuliahById(id);
            model.addAttribute("mk", mataKuliah);
            return "mata_kuliah/edit";
        } catch (Exception e) {
            return "redirect:/mata_kuliah";
        }
    }

    @PostMapping("/{id}/edit")
    public String editMataKuliahQuery(Model model, 
                                      @PathVariable int id, 
                                      @Valid @ModelAttribute("mk") MataKuliahDTO mataKuliahDTO,
                                      BindingResult result) 
    {
        try {
            MataKuliahDTO foundMataKuliah = mataKuliahService.findMataKuliahByKode(mataKuliahDTO.getKode());
            if (foundMataKuliah.getId() != mataKuliahDTO.getId()) {
                result.addError(new FieldError("mataKuliahDTO", 
                                               "kode",
                                               mataKuliahDTO.getKode(), 
                                               false, 
                                               null, 
                                               null, 
                                               "Kode is already in use!"));
            }
        } catch (Exception e) {}
        if (result.hasErrors()) {
            model.addAttribute("mk", mataKuliahDTO);
            return "mata_kuliah/edit";
        }
        mataKuliahDTO.setId(id);
        mataKuliahService.updateMataKuliah(mataKuliahDTO);
        return "redirect:/mata_kuliah";
    }

    @GetMapping("/{id}/delete")
    public String deleteMataKuliah(@PathVariable int id) {
        mataKuliahService.deleteMataKuliahById(id);
        return "redirect:/mata_kuliah";
    }
}
