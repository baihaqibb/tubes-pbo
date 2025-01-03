package com.kelompok2.tubespbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.kelompok2.tubespbo.models.KomponenPenilaian;
import com.kelompok2.tubespbo.models.dtos.KomponenPenilaianDTO;
import com.kelompok2.tubespbo.models.dtos.MahasiswaDTO;
import com.kelompok2.tubespbo.models.dtos.MataKuliahTerambilDTO;
import com.kelompok2.tubespbo.models.dtos.TranskripDTO;
import com.kelompok2.tubespbo.security.SecurityUtil;
import com.kelompok2.tubespbo.services.KomponenPenilaianService;
import com.kelompok2.tubespbo.services.MataKuliahTerambilService;
import com.kelompok2.tubespbo.services.TranskripService;
import com.kelompok2.tubespbo.services.UserService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/transkrip")
public class TranskripController {

    @Autowired
    private TranskripService transkripService;
    @Autowired
    private UserService userService;
    @Autowired
    private MataKuliahTerambilService mataKuliahTerambilService;
    @Autowired
    private KomponenPenilaianService komponenPenilaianService;
    
    @GetMapping({"", "/"})
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("{mhs_id}")
    public String transkripList(Model model, @PathVariable int mhs_id) {
        MahasiswaDTO mhs = userService.findMahasiswaById(mhs_id);
        List<TranskripDTO> tsList = transkripService.findAllTranskripByMahasiswaId(mhs_id);
        model.addAttribute("mhs", mhs);
        model.addAttribute("tsList", tsList);
        return "transkrip/index";
    }

    @GetMapping("{mhs_id}/{ts_id}")
    public String transkripDetail(Model model, @PathVariable int mhs_id, @PathVariable int ts_id) {
        MahasiswaDTO mhs = userService.findMahasiswaById(mhs_id);
        TranskripDTO ts = transkripService.findTranskripById(ts_id);
        model.addAttribute("mhs", mhs);
        model.addAttribute("ts", ts);
        return "transkrip/detail";
    }

    @GetMapping("{mhs_id}/{ts_id}/{mkt_id}")
    public String mataKuliahTerambilDetail(Model model, @PathVariable int mhs_id, @PathVariable int ts_id, @PathVariable int mkt_id) {
        MataKuliahTerambilDTO mkt = mataKuliahTerambilService.findMataKuliahTerambilById(mkt_id);
        String currentAuthority = SecurityUtil.getSessionAuthority();
        model.addAttribute("role", currentAuthority);
        model.addAttribute("mhs_id", mhs_id);
        model.addAttribute("ts_id", ts_id);
        model.addAttribute("mkt", mkt);
        return "mata_kuliah_terambil/detail";
    }
    
    

    @GetMapping("{mhs_id}/{ts_id}/{mkt_id}/create")
    public String createKomponenPenilaianForm(Model model, @PathVariable int mhs_id, @PathVariable int ts_id, @PathVariable int mkt_id) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        KomponenPenilaian komponenPenilaian = new KomponenPenilaian();
        model.addAttribute("mhs_id", mhs_id);
        model.addAttribute("ts_id", ts_id);
        model.addAttribute("mkid", mkt_id);
        model.addAttribute("kp", komponenPenilaian);
        return "komponen_penilaian/create";
    }

    @PostMapping("{mhs_id}/{ts_id}/{mkt_id}/create")
    public String createKomponenPenilaianQuery(Model model, 
                                 @PathVariable int mhs_id, @PathVariable int ts_id, @PathVariable int mkt_id,
                                 @Valid @ModelAttribute("kp") KomponenPenilaianDTO komponenPenilaianDTO, 
                                 BindingResult result) 
    {
        if (result.hasErrors()) {
            model.addAttribute("kp", komponenPenilaianDTO);
            return "komponen_penilaian/create";
        }
        komponenPenilaianService.createKomponenPenilaian(mkt_id, komponenPenilaianDTO);
        return "redirect:/transkrip/" + mhs_id + "/" + ts_id + "/" + mkt_id;
    }

    @GetMapping("{mhs_id}/{ts_id}/{mkt_id}/{id}/edit")
    public String editKomponenPenilaianForm(Model model, @PathVariable int mhs_id, @PathVariable int ts_id, @PathVariable int mkt_id, @PathVariable int id) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        KomponenPenilaianDTO komponenPenilaianDTO = komponenPenilaianService.findKomponenPenilaianById(id);
        model.addAttribute("mhs_id", mhs_id);
        model.addAttribute("ts_id", ts_id);
        model.addAttribute("mkid", mkt_id);
        model.addAttribute("kp", komponenPenilaianDTO);
        return "komponen_penilaian/edit";
    }

    @PostMapping("{mhs_id}/{ts_id}/{mkt_id}/{id}/edit")
    public String editKomponenPenilaianQuery(Model model, 
                                      @PathVariable int mhs_id, @PathVariable int ts_id, @PathVariable int mkt_id,
                                      @PathVariable int id, 
                                      @Valid @ModelAttribute("kp") KomponenPenilaianDTO komponenPenilaianDTO,
                                      BindingResult result) 
    {
        if (result.hasErrors()) {
            model.addAttribute("mkid", mkt_id);
            model.addAttribute("kp", komponenPenilaianDTO);
            return "komponen_penilaian/edit";
        }
        KomponenPenilaianDTO komponenPenilaianDTO2 = komponenPenilaianService.findKomponenPenilaianById(id);
        komponenPenilaianDTO.setId(id);
        komponenPenilaianDTO.setMataKuliahTerambil(komponenPenilaianDTO2.getMataKuliahTerambil());
        komponenPenilaianService.updateKomponenPenilaian(komponenPenilaianDTO);
        return "redirect:/transkrip/" + mhs_id + "/" + ts_id + "/" + mkt_id;
    }

    @GetMapping("{mhs_id}/{ts_id}/{mkt_id}/{id}/delete")
    public String deleteKomponenPenilaian(@PathVariable int mhs_id, @PathVariable int ts_id, @PathVariable int mkt_id, @PathVariable int id) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        komponenPenilaianService.deleteKomponenPenilaianByID(mkt_id, id);
        return "redirect:/transkrip/" + mhs_id + "/" + ts_id + "/" + mkt_id;
    }
    
}
