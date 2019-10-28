package apap.tugas.sidok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import apap.tugas.sidok.model.*;
import apap.tugas.sidok.service.*;

import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class JadwalJagaController {
    @Qualifier("jadwalJagaServiceImpl")
    @Autowired
    private JadwalJagaService jadwalJagaService;

    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Qualifier("spesialisasiServiceImpl")
    @Autowired
    private SpesialisasiService spesialisasiService;

    @Qualifier("poliServiceImpl")
    @Autowired
    private PoliService poliService;

    @RequestMapping(value = "/jadwal/tambah/{nipDokter}", method = RequestMethod.GET)
    public String addJadwalJagaFormPage(@PathVariable String nipDokter, Model model) {
        DokterModel dokter = dokterService.findDokterByNipDokter(nipDokter);
        JadwalJagaModel newJadwalJaga = new JadwalJagaModel();
        newJadwalJaga.setDokter(dokter);
        List<JadwalJagaModel> listJadwalJaga = jadwalJagaService.getJadwalJagaByDokter(dokter);
        List<PoliModel> listPoli = poliService.getPoliList();
        model.addAttribute("jadwalJaga", newJadwalJaga);
        model.addAttribute("dokter", dokter);
        model.addAttribute("listJadwalJaga", listJadwalJaga);
        model.addAttribute("listPoli", listPoli);
        return "form-add-jadwal-jaga";
    }

    @RequestMapping(value="/jadwal/tambah/{nipDokter}", method = RequestMethod.POST)
    public String addJadwalJagaSubmit(@PathVariable String nipDokter, @ModelAttribute JadwalJagaModel jadwalJaga, Model model){
        DokterModel dokter = dokterService.findDokterByNipDokter(nipDokter);
        jadwalJaga.setDokter(dokter);
        JadwalJagaModel jadwalJagaSubmit = jadwalJagaService.addJadwalJaga(jadwalJaga);
        model.addAttribute("jadwalJaga", jadwalJagaSubmit);
        model.addAttribute("dokter", dokter);
        return "add-jadwal";
    }

    @RequestMapping(value="poli/dokter/{idPoli}", method=RequestMethod.GET)
    public String viewDokterPoli(@PathVariable Long idPoli, Model model) {
        PoliModel poliModel = poliService.getPoliByIdPoli(idPoli);
        List<JadwalJagaModel> jadwalJagaModel = jadwalJagaService.getJadwalJagaByPoli(poliModel);
        model.addAttribute("listJadwalJagaByPoli", jadwalJagaModel);
        return "view-jadwal-jaga";
    }

}