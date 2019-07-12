package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.DAO.TopoRepository;
import com.emmanuel.escalade.model.Topo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TopoControleur {

    private final TopoRepository topoRepository;

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private static List<Topo> topos = new ArrayList<Topo>();

    @Autowired
    public TopoControleur(TopoRepository topoRepository) {
        this.topoRepository = topoRepository;
    }

    @GetMapping("/listetopos")
    public String listeTopos(Model model) {
        log.info("TopoControleur");
        model.addAttribute("topos", topoRepository.findAll());
        return "listetopos";
    }

    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("topo", topoRepository.findById(id));
        //        model.addAttribute("topo", topo);
        return "test";
    }


    @GetMapping("/majtopo/{id}")
    public String MAJTopo(@PathVariable("id") Integer id, Model model) {
        Topo topo= topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu : " + id));
        model.addAttribute("topo", topo);
        return "majtopo";
    }

    @PostMapping("/sauvertopo/{id}")
    public String sauverTopo(@PathVariable("id") Integer id, @Valid Topo topo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            topo.setTopoid(id);
            return "majtopo";
        }
        topoRepository.save(topo);
        model.addAttribute("topos", topoRepository.findAll());
        return "listetopos";
    }

    @GetMapping("/topo")
    public String afficherTopo (Topo topo) {
        return "topo";
    }

    @PostMapping("/ajoutertopo")
    public String ajouterTopo (@Valid @ModelAttribute("topo") Topo topo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "topo";
        }
        topoRepository.save(topo);
        model.addAttribute("topos", topoRepository.findAll());
        return "listetopos";
    }

}
