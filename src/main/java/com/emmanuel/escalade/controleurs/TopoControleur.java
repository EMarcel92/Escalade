package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.DAO.RegionRepository;
import com.emmanuel.escalade.DAO.TopoRepository;
import com.emmanuel.escalade.DAO.UtilisateurRepository;
import com.emmanuel.escalade.model.Topo;
import com.emmanuel.escalade.model.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.Console;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TopoControleur {

    private final TopoRepository topoRepository;
    private final RegionRepository regionRepository;
    private final UtilisateurRepository utilisateurRepository;

    private static final Logger log = LoggerFactory.getLogger(TopoControleur.class);

   // private static List<Topo> topos = new ArrayList<Topo>();

    @Autowired
    public TopoControleur(TopoRepository topoRepository, RegionRepository regionRepository, UtilisateurRepository utilisateurRepository) {
        this.topoRepository = topoRepository;
        this.regionRepository = regionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/listetopos")
    public String listeTopos(Model model) {
        log.info("TopoControleur");
        model.addAttribute("topos", topoRepository.findAll());
        return "listetopos";
    }

    @GetMapping("/listetopos/utilisateur")
    public String listeToposDunUtilisateur(Principal principal, Model model) {
        log.info("Topos d'un utilisateur");
        List<Topo> topos = topoRepository.findByUtilisateurPseudo(principal.getName());
        model.addAttribute("topos", topos);
        return "listetopos";
    }

    @PostMapping("/ajoutertopo")
    public String ajouterTopo (@Valid @ModelAttribute("topo") Topo topo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ajoutertopo";
        }
        //todo vérifer que l'utilisateur est connecté, sinon rediriger vers login
        //todo récupérer l'user connecté et supprimer la ligne suivante
        Utilisateur utilisateur = utilisateurRepository.findById(2).get();
        topo.setUtilisateur(utilisateur);
       // utilisateurRepository.save(utilisateur);
        topoRepository.save(topo);
        model.addAttribute("topos", topoRepository.findAll());
        return "redirect:/listetopos";
    }

    @GetMapping("/majtopo/{id}")
    public String mettreAJourTopo(@PathVariable("id") Integer id, Model model) {
        // PathVariable récupère l'id dans l'URI et le met dana l'integer id
        //Pour récupérer une variable passée en paramètre dans l'URL, utiliser @RequestParam
        //ex : @RequestParam(value = "date", required = false) Date dateOrNull)
        Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu : " + id));
        model.addAttribute("regions", regionRepository.findAll());
        model.addAttribute("topo", topo);
        return "majtopo";
    }

    @PostMapping("/sauvertopo/{id}")
    public String sauverTopo(@PathVariable("id") Integer id, @Valid Topo topo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            topo.setTopoId(id);
            return "majtopo";
        }
        topo.setTopoId(id);
        topoRepository.save(topo);
        model.addAttribute("topos", topoRepository.findAll());
        return "redirect:/listetopos";
    }

    @GetMapping("/topo")
    public String afficherTopo (Topo topo) {
        return "topo";
    }

    @GetMapping("/formulairetopo")
    public String afficherFormulaireAjoutTopo(Topo topo,Model model) {
        model.addAttribute("regions", regionRepository.findAll());
        return "ajoutertopo";
    }

    @GetMapping("/supprimertopo/{id}")
    public String supprimerTopo(@PathVariable("id") Integer id, Model model){
        topoRepository.deleteById(id);
        model.addAttribute("topos", topoRepository.findAll());
        return "redirect:/listetopos";
    }

    //todo test à detruire
    @GetMapping("/test/{id}")
    public String test(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("topo", topoRepository.findById(id));
        //        model.addAttribute("topo", topo);
        return "test";
    }
}
