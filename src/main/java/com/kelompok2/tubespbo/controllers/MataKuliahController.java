package com.kelompok2.tubespbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.models.dtos.MataKuliahDTO;
import com.kelompok2.tubespbo.security.SecurityUtil;
import com.kelompok2.tubespbo.services.MataKuliahService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/mata_kuliah")
public class MataKuliahController {

    @Autowired
    private MataKuliahService mataKuliahService;

    @GetMapping({ "", "/" })
    public String mataKuliahList(Model model) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        List<MataKuliahDTO> mataKuliahList = mataKuliahService.findAllMataKuliah();
        model.addAttribute("mataKuliahList", mataKuliahList);
        return "mata_kuliah/index";
    }

    @GetMapping("/search")
    public String getMethodName(Model model, @RequestParam(value = "q") String query) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        List<MataKuliahDTO> mataKuliahList = mataKuliahService.searchMataKuliahs(query);
        model.addAttribute("mataKuliahList", mataKuliahList);
        return "mata_kuliah/index";
    }

    @GetMapping("/{id}")
    public String mataKuliahDetail(Model model, @PathVariable int id) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        MataKuliahDTO mataKuliahDTO = mataKuliahService.findMataKuliahById2(id);
        if (mataKuliahDTO == null) {
            return "redirect:/mata_kuliah";
        }
        model.addAttribute("mk", mataKuliahDTO);
        return "mata_kuliah/detail";
    }

    @GetMapping("/create")
    public String createMataKuliahForm(Model model) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        MataKuliah mataKuliah = new MataKuliah();
        model.addAttribute("mk", mataKuliah);
        return "mata_kuliah/create";
    }

    @PostMapping("/create")
    public String createMataKuliahQuery(Model model, 
                                        @Valid @ModelAttribute("mk") MataKuliahDTO mataKuliahDTO, 
                                        BindingResult result) 
    {
        if (mataKuliahService.findMataKuliahByKode(mataKuliahDTO.getKode()) != null) {
            result.addError(new FieldError("mataKuliahDTO", 
                                           "kode",
                                           mataKuliahDTO.getKode(), 
                                           false, 
                                           null, 
                                           null, 
                                           "Kode is already in use!"));
        }
        if (result.hasErrors()) {
            model.addAttribute("mk", mataKuliahDTO);
            return "mata_kuliah/create";
        }
        mataKuliahService.createMataKuliah(mataKuliahDTO);
        return "redirect:/mata_kuliah";
    }

    @GetMapping("/{id}/edit")
    public String editMataKuliahForm(Model model, @PathVariable int id) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        MataKuliahDTO mataKuliah = mataKuliahService.findMataKuliahById(id);
        if (mataKuliah == null) {
            return "redirect:/mata_kuliah";
        }
        model.addAttribute("mk", mataKuliah);
        return "mata_kuliah/edit";
    }

    @PostMapping("/{id}/edit")
    public String editMataKuliahQuery(Model model, 
                                      @PathVariable int id, 
                                      @Valid @ModelAttribute("mk") MataKuliahDTO mataKuliahDTO,
                                      BindingResult result) 
    {
        MataKuliahDTO foundMataKuliah = mataKuliahService.findMataKuliahByKode(mataKuliahDTO.getKode());
        if (foundMataKuliah != null && foundMataKuliah.getId() != mataKuliahDTO.getId()) {
            result.addError(new FieldError("mataKuliahDTO", 
                                           "kode",
                                           mataKuliahDTO.getKode(), 
                                           false, 
                                           null, 
                                           null, 
                                           "Kode is already in use!"));
        }
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
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        mataKuliahService.deleteMataKuliahById(id);
        return "redirect:/mata_kuliah";
    }
}
