package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.DAO.RegionRepository;
import com.emmanuel.escalade.DAO.TopoRepository;
import com.emmanuel.escalade.DAO.UtilisateurRepository;
import com.emmanuel.escalade.Services.TopoService;
import com.emmanuel.escalade.model.Topo;
import com.emmanuel.escalade.model.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

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

    @Autowired
    public TopoControleur(TopoRepository topoRepository, RegionRepository regionRepository, UtilisateurRepository utilisateurRepository) {
        this.topoRepository = topoRepository;
        this.regionRepository = regionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping("/listetopos")
    public String listeTopos(Principal principal, Model model) {
        log.info("Liste de tous les topos");
        if (principal != null)
            model.addAttribute("topos", topoRepository.findByUtilisateurPseudoNotOrderByNomTopo(principal.getName()));
        else
            model.addAttribute("topos", topoRepository.findAll());
        return "listetopos";
    }

    @GetMapping("/listetopos/utilisateur")
    public String listeToposDunUtilisateur(Principal principal, Model model) {
        log.info("Liste des topos d'un utilisateur");
        List<Topo> topos = topoRepository.findByUtilisateurPseudoOrderByNomTopo(principal.getName());
        model.addAttribute("topos", topos);
        return "listemestopos";
    }

    @GetMapping("/formulairetopo")
    public String afficherFormulaireAjoutTopo(Topo topo,Model model) {
        model.addAttribute("regions", regionRepository.findAll());
        return "ajoutertopo";
    }

    @PostMapping("/ajoutertopo")
    public String ajouterTopo (@Valid @ModelAttribute("topo") Topo topo, BindingResult result, Principal principal, Model model) {
        if (result.hasErrors()) {
            return "ajoutertopo";
        }
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(principal.getName());
        topo.setUtilisateur(utilisateur);
        topoRepository.save(topo);
        model.addAttribute("topos", topoRepository.findAll());
        return "redirect:/listetopos/utilisateur";
    }

    @GetMapping("/majtopo/{id}")
    public String mettreAJourTopo(@PathVariable("id") Integer id, Model model) {
        // PathVariable récupère l'id dans l'URI et le met dans l'integer id
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
        //todo cette ligne est-elle nécessaire ?
        model.addAttribute("topos", topoRepository.findAll());
        return "redirect:/listetopos/utilisateur";
    }

    @GetMapping("/topo")
    public String afficherTopo (Topo topo) {
        return "topo";
    }

    @GetMapping("/supprimertopo/{id}")
    public String supprimerTopo(@PathVariable("id") Integer id, Model model){
        topoRepository.deleteById(id);
        model.addAttribute("topos", topoRepository.findAll());
        return "redirect:/listetopos/utilisateur";
    }

    @GetMapping("/gererdemandeemprunt/{id}")
    public String gererDemandeEmpruntTopo(@PathVariable("id") Integer id, Principal principal, Model model) {
        log.info("Demande d'emprunt par un utilisateur connecté. id topo=" + id + ".");
        Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu : " + id));
        log.info("Demande d'emprunt. Diponibilité=" + topo.getDisponible() + ", utilisateur connecté=" + principal.getName() + ".");
        if (topo.getEmprunteur() != null){
            log.info("emprunteur=" + topo.getEmprunteur().getPseudo() );
        }
        if (topo.getDisponible().equals("D") && topo.getEmprunteur() == null) {
            log.info("Topage demande de réservation" );
            Utilisateur emprunteur = utilisateurRepository.findByPseudo(principal.getName());
            topo.setEmprunteur(emprunteur);
            topo.setDisponible("R");
        }
        else {
            if (topo.getDisponible().equals("R") && topo.getEmprunteur().getPseudo().equals(principal.getName())) {
                log.info("Détaoppage résa");
                Utilisateur emprunteur = utilisateurRepository.findByPseudo(principal.getName());
                topo.setDisponible("D");
                topo.setEmprunteur(null);
            }
            else{
                log.info("Aucune condition remplie");
            }
        }
        topoRepository.save(topo);
        return "redirect:/listetopos";
    }

    @GetMapping("/accepterpret/{id}")
    public String accepterPretTopo(@PathVariable("id") Integer id, Principal principal, Model model) {
        log.info("Accepter la demande d'emprunt. id topo=" + id + ".");
        Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour acceptation prêt: " + id));
        topo.setDisponible("I");
        topoRepository.save(topo);
        return "redirect:/listetopos/utilisateur";
    }

    @GetMapping("/refuserpret/{id}")
    public String refuserPretTopo(@PathVariable("id") Integer id, Principal principal, Model model) {
        log.info("Accepter la demande d'emprunt. id topo=" + id + ".");
        Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour refus de prêt: " + id));
        topo.setDisponible("D");
        topo.setEmprunteur(null);
        topoRepository.save(topo);
        return "redirect:/listetopos/utilisateur";
    }

}
