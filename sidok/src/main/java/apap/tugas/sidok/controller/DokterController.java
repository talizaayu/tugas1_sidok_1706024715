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
public class DokterController {
    @Qualifier("dokterServiceImpl")
    @Autowired
    private DokterService dokterService;

    @Autowired
    private SpesialisasiService spesialisasiService;
    
    // @RequestMapping("/")
    // public String home() {
    //     return "home";
    // }

    @RequestMapping("/")
    public String viewall(Model model) {

        List<DokterModel> listDokter = dokterService.getDokterList();

        model.addAttribute("dokterList", listDokter);

        return "beranda"; 
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    public String addDokterFormPage(Model model) {
        DokterModel newDokter = new DokterModel();
        model.addAttribute("dokter", newDokter);
        List<SpesialisasiModel> spesialisasiModel = spesialisasiService.getSpesialisasiList();
        model.addAttribute("listSpesialisasi", spesialisasiModel);
        return "form-add-dokter";
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.POST)
    public String addDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
        dokter.setNipDokter("0");
        dokterService.addDokter(dokter);
        model.addAttribute("namaDokter", dokter.getNamaDokter());
        return "add-dokter";
    }

    @RequestMapping(value="/dokter/{nikDokter}", method=RequestMethod.GET)
    public String detailDokter(@PathVariable String nikDokter, Model model) {
        DokterModel nik = dokterService.findDokterByNikDokter(nikDokter);
        model.addAttribute("dokter", nik);
        return "detail-dokter";
    }

    // @RequestMapping(value = "/dokter/add", method = RequestMethod.POST, params={"submit"})
    // private String tambahDokterSubmit(@ModelAttribute DokterModel dokter, Model model) {
    //     String nipDokter = dokterService.generateNip(dokter.getTanggalLahir(), dokter.getJenisKelamin());
    //     dokter.setNipDokter(nipDokter);
    //     dokterService.addDokter(dokter);

    //     model.addAttribute("nipDokter", nipDokter);
	// 	model.addAttribute("title", "Tambah Dokter");
       
    //     return "add-dokter";
    // } 

    @RequestMapping(value="dokter/update/{idDokter}", method = RequestMethod.GET)
    public String updateDokterFormPage(@PathVariable Long idDokter, Model model) {
        DokterModel existingDokter = dokterService.findDokterByIdDokter(idDokter);
        model.addAttribute("dokter", existingDokter);
        return "form-update-dokter";
    }

    @RequestMapping(value="dokter/update/{idDokter}", method = RequestMethod.POST)
    public String updateDokterFormSubmit(@PathVariable Long idDokter, @ModelAttribute DokterModel dokter, Model model) {
        DokterModel newDokterData = dokterService.updateDokter(dokter);
        model.addAttribute("dokter", newDokterData);
        return "update-dokter";
    }

    @RequestMapping(value="/dokter/delete/{idDokter}", method = RequestMethod.POST)
	private String delete(@PathVariable Long idDokter, Model model) {
		DokterModel dokter = dokterService.findDokterByIdDokter(idDokter);
		String namaDokter = dokter.getNamaDokter();
		dokterService.deleteDokter(idDokter);
		model.addAttribute("namaDokter", namaDokter);
		return "delete-dokter";
	}
}



