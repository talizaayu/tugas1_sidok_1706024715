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
}



