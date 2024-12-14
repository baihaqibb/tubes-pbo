package com.kelompok2.tubespbo.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.kelompok2.tubespbo.dtos.MataKuliahDTO;
import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.repositories.MataKuliahRepository;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mata_kuliah")
public class MataKuliahController {

    @Autowired
    private MataKuliahRepository mataKuliahRepo;

    @GetMapping({ "", "/" })
    public String getMataKuliah(Model model) {
        var mataKuliahList = mataKuliahRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("mataKuliahList", mataKuliahList);

        return "mata_kuliah/index";
    }

    @GetMapping("/detail")
    public String getMethodName(Model model, @RequestParam int id) {
        MataKuliah mk = mataKuliahRepo.findById(id).orElse(null);
        if (mk == null) {
            return "mata_kuliah/index";
        }
        model.addAttribute("mk", mk);

        return "mata_kuliah/detail";
    }

    @GetMapping("/create")
    public String createMataKuliah(Model model) {
        MataKuliahDTO mataKuliahDTO = new MataKuliahDTO();
        model.addAttribute("mataKuliahDTO", mataKuliahDTO);

        return "mata_kuliah/create";
    }

    @PostMapping("/create")
    public String createMataKuliah(
            @Valid @ModelAttribute MataKuliahDTO mataKuliahDTO,
            BindingResult result) {

        if (mataKuliahDTO.getKode().isBlank()) {
            result.addError(new FieldError("mataKuliahDTO", "kode",
                    mataKuliahDTO.getKode(), false, null, null, "Kode can't be blank!"));
        }

        if (mataKuliahDTO.getNama().isBlank()) {
            result.addError(new FieldError("mataKuliahDTO", "nama",
                    mataKuliahDTO.getNama(), false, null, null, "Nama can't be blank!"));
        }

        if (mataKuliahRepo.findByKode(mataKuliahDTO.getKode()) != null) {
            result.addError(new FieldError("mataKuliahDTO", "kode",
                    mataKuliahDTO.getKode(), false, null, null, "Kode is already in use!"));
        }

        if (result.hasErrors()) {
            return "mata_kuliah/create";
        }

        MataKuliah mk = new MataKuliah();

        mk.setKode(mataKuliahDTO.getKode());
        mk.setNama(mataKuliahDTO.getNama());
        mk.setSks(mataKuliahDTO.getSks());

        mataKuliahRepo.save(mk);

        return "redirect:/mata_kuliah";

    }

    @GetMapping("/edit")
    public String editMataKuliah(Model model, @RequestParam int id) {
        MataKuliah mk = mataKuliahRepo.findById(id).orElse(null);
        if (mk == null) {
            return "redirect:/mata_kuliah";
        }
        MataKuliahDTO mataKuliahDTO = new MataKuliahDTO();
        mataKuliahDTO.setKode(mk.getKode());
        mataKuliahDTO.setNama(mk.getNama());
        mataKuliahDTO.setSks(mk.getSks());

        model.addAttribute("mk", mk);
        model.addAttribute("mataKuliahDTO", mataKuliahDTO);

        return "mata_kuliah/edit";
    }

    @PostMapping("/edit")
    public String editMataKuliah(Model model,
            @RequestParam int id,
            @Valid @ModelAttribute MataKuliahDTO mataKuliahDTO,
            BindingResult result) {

        MataKuliah mk = mataKuliahRepo.findById(id).orElse(null);
        if (mk == null) {
            return "redirect:/mata_kuliah";
        }

        model.addAttribute("mk", mk);

        if (result.hasErrors()) {
            return "mata_kuliah/edit";
        }

        mk.setKode(mataKuliahDTO.getKode());
        mk.setNama(mataKuliahDTO.getNama());
        mk.setSks(mataKuliahDTO.getSks());

        try {
            mataKuliahRepo.save(mk);
        } catch (Exception e) {
            result.addError(new FieldError("mataKuliahDTO", "kode",
                    mataKuliahDTO.getKode(), false, null, null, "Kode is already in use!"));
        }

        return "redirect:/mata_kuliah";

    }

    @GetMapping("/delete")
    public String deleteMataKuliah(@RequestParam int id) {
        MataKuliah mk = mataKuliahRepo.findById(id).orElse(null);
        if (mk != null) {
            mataKuliahRepo.delete(mk);
        }

        return "redirect:/mata_kuliah";
    }
}
