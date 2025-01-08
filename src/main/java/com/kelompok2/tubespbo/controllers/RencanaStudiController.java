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
    public String editRencanaStudiForm(Model model, @PathVariable String rs_id) {
        try{
            int rs_idInt = Integer.parseInt(rs_id);
            RencanaStudiDTO rs = rencanaStudiService.findRencanaStudiById(rs_idInt);
            List<MataKuliahDTO> mkList = mataKuliahService.findAllMataKuliah();
            if (rs == null) {
                return "redirect:/";
            }
            model.addAttribute("rs", rs);
            
            model.addAttribute("mkList", mkList);
            return "rencana_studi/edit";
        } catch (NumberFormatException e) {
            return "redirect:/home?typeError";
        }
    }

    @GetMapping("{rs_id}/add/{mk_id}")
    public String addMataKuliahToList(Model model, 
        @PathVariable String rs_id, 
        @PathVariable String mk_id) 
    {
        try{
            int rs_idInt = Integer.parseInt(rs_id);
            int mk_idInt = Integer.parseInt(mk_id);
            rencanaStudiService.saveRencanaStudi(rs_idInt, mk_idInt);
            return "redirect:/rencana_studi/" + rs_idInt + "/edit";
        } catch (NumberFormatException e) {
            return "redirect:/home?typeError";
        }
    }
    
    @GetMapping("{rs_id}/remove/{mk_id}")
    public String removeMataKuliahFromList(Model model, 
        @PathVariable String rs_id, 
        @PathVariable String mk_id) 
    {
        try{
            int rs_idInt = Integer.parseInt(rs_id);
            int mk_idInt = Integer.parseInt(mk_id);
            rencanaStudiService.removeRencanaStudi(rs_idInt, mk_idInt);
            return "redirect:/rencana_studi/" + rs_id + "/edit";
        } catch (NumberFormatException e) {
            return "redirect:/home?typeError";
        }
    }
    
    @GetMapping("{rs_id}/submit")
    public String submitRencanaStudi(Model model, @PathVariable String rs_id, @RequestParam("mhs") String mhs_id) {
        try{
            int rs_idInt = Integer.parseInt(rs_id);
            int mhs_idInt = Integer.parseInt(mhs_id);
            String currentAuthority = SecurityUtil.getSessionAuthority();
            if (!currentAuthority.equals("ADMIN")) {
                return "redirect:/home?authError";
            }
            rencanaStudiService.submitRencanaStudi(rs_idInt);
            return "redirect:/mahasiswa/" + mhs_idInt;
        } catch (NumberFormatException e) {
            return "redirect:/home?typeError";
        }
    }
}
