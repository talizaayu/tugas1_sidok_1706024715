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
public class PoliController {
    @Qualifier("poliServiceImpl")
    @Autowired
    private PoliService poliService;

    @RequestMapping("/poli")
    public String viewPoli(Model model) {
        List<PoliModel> listPoli = poliService.getPoliList();
        model.addAttribute("poliList", listPoli);
        return "daftar-poli"; 
    }

    @RequestMapping(value = "/poli/tambah", method = RequestMethod.GET)
    public String addPoliFormPage(Model model) {
        PoliModel newPoli = new PoliModel();
        PoliModel oldPoli = poliService.getTopByOrderByIdPoliDesc();
        model.addAttribute("poli", newPoli);
        model.addAttribute("idPoli", oldPoli.getIdPoli() + 1);
        return "form-add-poli";
    }

    @RequestMapping(value = "/poli/view/{idPoli}", method = RequestMethod.POST)
    public String addPoliSubmit(@PathVariable Long idPoli, @ModelAttribute PoliModel poli, Model model) {
        PoliModel newPoli = poliService.addPoli(poli);
        model.addAttribute("poli", newPoli);
        return "detail-add-poli";
    }

    @RequestMapping(value="/poli/view/{idPoli}", method=RequestMethod.GET)
    public String detailPoli(@PathVariable Long idPoli, Model model) {
        PoliModel poli = poliService.getPoliByIdPoli(idPoli);
        model.addAttribute("poli", poli);
        return "detail-poli";
    }
}



