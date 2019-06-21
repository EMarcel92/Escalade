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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @GetMapping("/topo")
    public String afficherTopo (Topo topo) {

        return "topo";
    }

    @PostMapping("/ajoutertopo")
    public String ajouterTopo (@Valid Topo topo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "topo";
        }
        topoRepository.save(topo);
        model.addAttribute("users", topoRepository.findAll());
        return "listetopos";
    }

//    Création d'objet topos à la volée
//    static {
//        topos.add(new Topo("Site difficile", "Bla bla bla", 3 , 8 ));
//        topos.add(new Topo("Site facile", "Bli  bli blibli blibli bli", 4 , 6 ));
//    }

    /*
    @RequestMapping(value = {"/listetopos" }, method = RequestMethod.GET)
    public String listeTopos(Model model) {
        log.info("TopoControleur");
//        Topo topo = new Topo();
        model.addAttribute("topos", topos);

        return "listetopos";
    }

    @RequestMapping(value = {"/topo" }, method = RequestMethod.GET)
    public String listeSites(Model model) {

        return "topo";
    }
    */


}
