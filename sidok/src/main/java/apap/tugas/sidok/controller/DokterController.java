package apap.tugas.sidok.controller;

import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.util.Random;
import java.util.Date;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.validation.BindingResult;
import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private PoliService poliService;

    @Autowired
    private JadwalJagaService jadwalJagaService;

    @RequestMapping("/")
    public String viewall(Model model) {
        List<DokterModel> listDokter = dokterService.getDokterList();
        model.addAttribute("dokterList", listDokter);
        return "beranda"; 
    }

    @RequestMapping(value = "/dokter/tambah", method = RequestMethod.GET)
    public String addDokterFormPage(Model model) {
        DokterModel newDokter = new DokterModel();
        List<SpesialisasiModel> spesialisasiModel = spesialisasiService.getSpesialisasiList();
        ArrayList<SpesialisasiModel> listSpesialisasi = new ArrayList<SpesialisasiModel>();
        listSpesialisasi.add(new SpesialisasiModel());
        newDokter.setListSpesialisasi(listSpesialisasi);
        model.addAttribute("dokter", newDokter);
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
        
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String tanggalLahir = dateFormat.format(dokter.getTanggalLahir());
        String tanggalLahir2 = dateFormat.format(dokter.getTanggalLahir());
        tanggalLahir = tanggalLahir.substring(0, tanggalLahir.length() - 4);
        tanggalLahir2 = tanggalLahir2.substring(tanggalLahir2.length() - 2, tanggalLahir2.length());
        tanggalLahir = tanggalLahir.replace("-", "");
        nipDokter += tanggalLahir;
        nipDokter += tanggalLahir2;


        int jenis_kelamin = 0;
        if (dokter.getJenisKelamin() == 1) {
            jenis_kelamin = 1;
        }
        if (dokter.getJenisKelamin() == 2) {
            jenis_kelamin = 2;
        }
        
        nipDokter += jenis_kelamin;
        
        Random r = new Random();
        char a = (char) (r.nextInt(26) + 'A');
        char b = (char) (r.nextInt(26) + 'A');
        nipDokter += a;
        nipDokter += b;
      
        dokter.setNipDokter(nipDokter);
        dokterService.addDokter(dokter);
        // dokter.setNipDokter("0");
        model.addAttribute("dokter", dokter);
        return "add-dokter";
    }

    @RequestMapping(value="/dokter/tambah", method = RequestMethod.POST, params={"addRow"}) 
    public String addRow(@ModelAttribute DokterModel dokter, BindingResult bindingResult, final HttpServletRequest req, Model model) {
        if (dokter.getListSpesialisasi() == null) {
            dokter.setListSpesialisasi(new ArrayList<SpesialisasiModel>());
        }
        dokter.getListSpesialisasi().add(new SpesialisasiModel());
        model.addAttribute("dokter", dokter);
        List<SpesialisasiModel> spesialisasiModel = spesialisasiService.getSpesialisasiList();
        model.addAttribute("listSpesialisasi", spesialisasiModel);
        return "form-add-dokter";
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
        // DokterModel oldDokterData = dokterService.updateDokter(dokter);

        // if((dokter.getNamaDokter().equals(oldDokterData.getNamaDokter())) 
        // && (dokter.getJenisKelamin() == oldDokterData.getJenisKelamin()
        // && (dokter.getTanggalLahir().equals(oldDokterData.getTanggalLahir()))
        // && (dokter.getTempatLahir().equals(oldDokterData.getTempatLahir())))) {
        //     return "tidak-update-dokter";
        // }

        // else {
            String nipDokter = "";
            String[] nowYear = LocalDate.now().toString().split("-");
            int selectedYear = Integer.parseInt(nowYear[0]) + 5;
            System.out.println(selectedYear);
            nipDokter += selectedYear;
            
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String tanggalLahir = dateFormat.format(dokter.getTanggalLahir());
            String tanggalLahir2 = dateFormat.format(dokter.getTanggalLahir());
            tanggalLahir = tanggalLahir.substring(0, tanggalLahir.length() - 4);
            tanggalLahir2 = tanggalLahir2.substring(tanggalLahir2.length() - 2, tanggalLahir2.length());
            tanggalLahir = tanggalLahir.replace("-", "");
            nipDokter += tanggalLahir;
            nipDokter += tanggalLahir2;

    
            int jenis_kelamin = 0;
            if (dokter.getJenisKelamin() == 1) {
                jenis_kelamin = 1;
            }
            if (dokter.getJenisKelamin() == 2) {
                jenis_kelamin = 2;
            }
            nipDokter += jenis_kelamin;
            
            Random r = new Random();
            char a = (char) (r.nextInt(26) + 'A');
            char b = (char) (r.nextInt(26) + 'A');
            nipDokter += a;
            nipDokter += b;
    
            dokter.setNipDokter(nipDokter);
            DokterModel newDokterData = dokterService.updateDokter(dokter);
            model.addAttribute("dokter", newDokterData);
            return "detail-dokter";
        // }
        
    }

    @RequestMapping(value="/dokter/delete/{idDokter}", method = RequestMethod.POST)
	private String delete(@PathVariable Long idDokter, Model model) {
		DokterModel dokter = dokterService.findDokterByIdDokter(idDokter);
		String namaDokter = dokter.getNamaDokter();
		dokterService.deleteDokter(idDokter);
		model.addAttribute("namaDokter", namaDokter);
		return "delete-dokter";
    }

    @RequestMapping("/find")
    public String find() {
        return "cari";
    }
    
    @RequestMapping(value="/cari", method = RequestMethod.GET)
    public String cariDokterByPoliAndSpesialisasi(Model model) {
        List<PoliModel> listPoli = poliService.getPoliList();
        model.addAttribute("poliList", listPoli);
        List<SpesialisasiModel> spesialisasiModel = spesialisasiService.getSpesialisasiList();
        model.addAttribute("listSpesialisasi", spesialisasiModel);
        return "cari-dokter";
    }

    @RequestMapping(value="/cari", method=RequestMethod.GET, params={"idSpesialisasi", "idPoli"})
    public String cariDokterPoliAndSpesialisasi(@RequestParam(value="idSpesialisasi") Long idSpesialisasi, @RequestParam(value="idPoli") Long idPoli, Model model) {
        List<PoliModel> poliModel = poliService.getPoliList();
        model.addAttribute("poliList", poliModel);
        
        List<SpesialisasiModel> spesialisasiModel = spesialisasiService.getSpesialisasiList();
        model.addAttribute("listSpesialisasi", spesialisasiModel);

        PoliModel poliModels = poliService.getPoliByIdPoli(idPoli);
        SpesialisasiModel spesialisasiModels = spesialisasiService.getSpesialisasiByIdSpesialisasi(idSpesialisasi).get();

        List<JadwalJagaModel> jadwalJagaByPoli = jadwalJagaService.getJadwalJagaByPoli(poliModels);
        List<DokterModel> targetDokterModels = new ArrayList<DokterModel>();

        for (JadwalJagaModel jadwalJaga : jadwalJagaByPoli) {
            int spesialisasiLn = jadwalJaga.getDokter().getListSpesialisasi().size();
            for(int i = 0; i < spesialisasiLn; i++) {
                if(spesialisasiModels == jadwalJaga.getDokter().getListSpesialisasi().get(i)){
                    targetDokterModels.add(jadwalJaga.getDokter());
                }
            }
        }
        model.addAttribute("targetDokterModels", targetDokterModels);
        return "cari-dokter";

    }

    @RequestMapping(value="/cari-dokter-poli", method=RequestMethod.GET, params={"idPoli"})
    public String cariDokterBanyakPoli(@RequestParam(value="idPoli") Long idPoli, Model model) {
        List<PoliModel> poliModel = poliService.getPoliList();
        model.addAttribute("poliList", poliModel);
        PoliModel poliModels = poliService.getPoliByIdPoli(idPoli);
        List<JadwalJagaModel> jadwalJagaModel = jadwalJagaService.getJadwalJagaByPoli(poliModels);
        Map<Long, Integer> map = new HashMap<Long, Integer>();

        for(int i = 0; i<jadwalJagaModel.size(); i++) {
            Long id = jadwalJagaModel.get(i).getDokter().getIdDokter();
            if(map.containsKey(id)) {
                map.put(id, map.get(id) + 1);
            }
            map.put(id, 1);
        }
        Long max = null;
        for(Map.Entry<Long, Integer> entry : map.entrySet()) {
            if(max == null || entry.getValue() > map.get(max)) {
                max = entry.getKey();
            }
        }
        DokterModel dokter = dokterService.getDokterByIdDokter(max);
        model.addAttribute("dokter", dokter);
        return "cari-dokter-banyak-poli";
    }

    @RequestMapping(value="/cari-dokter-poli", method = RequestMethod.GET) 
    public String cariDokterBanyakPoli(Model model) {
        List<PoliModel> poliModels = poliService.getPoliList();
        model.addAttribute("poliList", poliModels);
        return "cari-dokter-banyak-poli";
    }

    @RequestMapping(value="/bonus")
    public String bonus(Model model) {
		List<SpesialisasiModel> listSpesialisasi = spesialisasiService.getSpesialisasiList();
		model.addAttribute("listSpesialisasi", listSpesialisasi);
		return "bonus";
	}
}



