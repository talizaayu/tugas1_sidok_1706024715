package apap.tugas.sidok.controller;

import java.util.List;
import java.time.LocalDate;
import java.util.Random;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
        String nipDokter = "";
        String[] nowYear = LocalDate.now().toString().split("-");
        int selectedYear = Integer.parseInt(nowYear[0]) + 5;
        System.out.println(selectedYear);
        nipDokter += selectedYear;
        
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
        String tanggalLahir = dateFormat.format(dokter.getTanggalLahir());
        tanggalLahir = tanggalLahir.substring(0, tanggalLahir.length() - 2);
        tanggalLahir = tanggalLahir.replace("-", "");
        nipDokter += tanggalLahir;

        int gender = 0;
        if (dokter.getJenisKelamin() == 1) {
            gender = 1;
        }
        if (dokter.getJenisKelamin() == 2) {
            gender = 2;
        }
        
        nipDokter += gender;
        
        Random r = new Random();
        char a = (char) (r.nextInt(26) + 'a');
        char b = (char) (r.nextInt(26) + 'a');
        nipDokter += a;
        nipDokter += b;
      
        dokter.setNipDokter(nipDokter);
        dokterService.addDokter(dokter);
        // dokter.setNipDokter("0");
        dokterService.addDokter(dokter);
        model.addAttribute("dokter", dokter);
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



