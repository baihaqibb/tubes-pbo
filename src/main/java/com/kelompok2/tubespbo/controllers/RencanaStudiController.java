package com.kelompok2.tubespbo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kelompok2.tubespbo.models.dtos.MataKuliahDTO;
import com.kelompok2.tubespbo.models.dtos.RencanaStudiDTO;
import com.kelompok2.tubespbo.security.SecurityUtil;
import com.kelompok2.tubespbo.services.MataKuliahService;
import com.kelompok2.tubespbo.services.RencanaStudiService;

@Controller
@RequestMapping("/rencana_studi")
public class RencanaStudiController {

    @Autowired
    private RencanaStudiService rencanaStudiService;
    @Autowired
    private MataKuliahService mataKuliahService;
    
    @GetMapping("{rs_id}/edit")
    public String editRencanaStudiForm(Model model, @PathVariable int rs_id) {
        RencanaStudiDTO rs = rencanaStudiService.findRencanaStudiById(rs_id);
        List<MataKuliahDTO> mkList = mataKuliahService.findAllMataKuliah();
        if (rs == null) {
            return "redirect:/";
        }
        model.addAttribute("rs", rs);
        
        model.addAttribute("mkList", mkList);
        return "rencana_studi/edit";
    }

    @GetMapping("{rs_id}/add/{mk_id}")
    public String addMataKuliahToList(Model model, 
        @PathVariable int rs_id, 
        @PathVariable int mk_id) 
    {
        rencanaStudiService.saveRencanaStudi(rs_id, mk_id);
        return "redirect:/rencana_studi/" + rs_id + "/edit";
    }
    
    @GetMapping("{rs_id}/remove/{mk_id}")
    public String removeMataKuliahFromList(Model model, 
        @PathVariable int rs_id, 
        @PathVariable int mk_id) 
    {
        rencanaStudiService.removeRencanaStudi(rs_id, mk_id);
        return "redirect:/rencana_studi/" + rs_id + "/edit";
    }
    
    @GetMapping("{rs_id}/submit")
    public String submitRencanaStudi(Model model, @PathVariable int rs_id, @RequestParam("mhs") int mhs_id) {
        String currentAuthority = SecurityUtil.getSessionAuthority();
        if (!currentAuthority.equals("ADMIN")) {
            return "redirect:/";
        }
        rencanaStudiService.submitRencanaStudi(rs_id);
        return "redirect:/mahasiswa/" + mhs_id;
    }
    
}
