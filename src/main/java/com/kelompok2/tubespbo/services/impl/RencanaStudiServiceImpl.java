package com.kelompok2.tubespbo.services.impl;

import static com.kelompok2.tubespbo.models.mappers.RencanaStudiMapper.mapToRencanaStudiDTO;
import static com.kelompok2.tubespbo.models.mappers.MataKuliahTerambilMapper.mapToMataKuliahTerambilFromMataKuliah;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelompok2.tubespbo.models.MataKuliah;
import com.kelompok2.tubespbo.models.MataKuliahTerambil;
import com.kelompok2.tubespbo.models.RencanaStudi;
import com.kelompok2.tubespbo.models.Transkrip;
import com.kelompok2.tubespbo.models.dtos.RencanaStudiDTO;
import com.kelompok2.tubespbo.repositories.MataKuliahRepository;
import com.kelompok2.tubespbo.repositories.MataKuliahTerambilRepository;
import com.kelompok2.tubespbo.repositories.RencanaStudiRepository;
import com.kelompok2.tubespbo.repositories.TranskripRepository;
import com.kelompok2.tubespbo.services.RencanaStudiService;

@Service
public class RencanaStudiServiceImpl implements RencanaStudiService {

    @Autowired
    private RencanaStudiRepository rencanaStudiRepository;
    @Autowired
    private MataKuliahRepository mataKuliahRepository;
    @Autowired
    private MataKuliahTerambilRepository mataKuliahTerambilRepository;
    @Autowired
    private TranskripRepository transkripRepository;

    @Override
    public void saveRencanaStudi(int rs_id, int mk_id) {
        try {
            RencanaStudi rencanaStudi = rencanaStudiRepository.findById(rs_id).get();
            MataKuliah mataKuliah = mataKuliahRepository.findById(mk_id).get();
            rencanaStudi.setTotalSKS(rencanaStudi.getTotalSKS()+mataKuliah.getSks());
            rencanaStudi.getListMK().add(mataKuliah);
            rencanaStudiRepository.save(rencanaStudi);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void removeRencanaStudi(int rs_id, int mk_id) {
        try {
            RencanaStudi rencanaStudi = rencanaStudiRepository.findById(rs_id).get();
            MataKuliah mataKuliah = mataKuliahRepository.findById(mk_id).get();
            rencanaStudi.setTotalSKS(rencanaStudi.getTotalSKS()-mataKuliah.getSks());
            rencanaStudi.getListMK().remove(mataKuliah);
            rencanaStudiRepository.save(rencanaStudi);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public RencanaStudiDTO findRencanaStudiById(int id) {
        try {
            RencanaStudi rencanaStudi = rencanaStudiRepository.findById(id).get();
            return mapToRencanaStudiDTO(rencanaStudi);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void submitRencanaStudi(int rs_id) {
        try {
            RencanaStudi rencanaStudi = rencanaStudiRepository.findById(rs_id).get();
            Transkrip transkrip = Transkrip.builder()
                .semester(rencanaStudi.getSemester())
                .totalSKS(rencanaStudi.getTotalSKS())
                .mahasiswa(rencanaStudi.getMahasiswa())
                .ips(0)
                .build();
            rencanaStudi.setSemester(rencanaStudi.getSemester()+1);
            rencanaStudi.setTotalSKS(0);
            rencanaStudi.getListMK().clear();
            transkripRepository.save(transkrip);
            for (MataKuliah mk : rencanaStudi.getListMK()) {
                MataKuliahTerambil mkt = mapToMataKuliahTerambilFromMataKuliah(mk);
                mkt.setTranskrip(transkrip);
                mataKuliahRepository.save(mkt);
            }
        } catch (Exception e) {

        }
        
    }

}
