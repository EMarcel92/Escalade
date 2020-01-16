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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Gère les requêtes correspondant à la gestion des topos : CRUD et emprunts
 */
@Controller
public class TopoControleur {

    private static final Logger log = LoggerFactory.getLogger(TopoControleur.class);
    private final TopoRepository topoRepository;
    private final RegionRepository regionRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public TopoControleur(TopoRepository topoRepository, RegionRepository regionRepository, UtilisateurRepository utilisateurRepository) {
        this.topoRepository = topoRepository;
        this.regionRepository = regionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Affiche la liste des topos sauf ceux de l'utilisateur connecté
     * @param principal les références de l'utilisateur connecté
     * @param model
     * @return Affiche la liste des topos
     */
    @GetMapping("/listetopos")
    public String listeTopos(Principal principal, Model model) {
        log.info("Liste de tous les topos");
        if (principal != null)
            model.addAttribute("topos", topoRepository.findByUtilisateurPseudoNotOrderByNomTopo(principal.getName()));
        else
            model.addAttribute("topos", topoRepository.findAll());
        return "listetopos";
    }

    /**
     * Affcihe les topos appartenant à l'utilisateur connecté
     * @param principal
     * @param model
     * @return Affiche la liste des topos de l'utilisateur avec les fonctions CRUD
     */
    @GetMapping("/listetopos/utilisateur")
    public String listeToposDunUtilisateur(Principal principal, Model model) {
        log.info("Liste des topos d'un utilisateur");
        List<Topo> topos = topoRepository.findByUtilisateurPseudoOrderByNomTopo(principal.getName());
        model.addAttribute("topos", topos);
        return "listemestopos";
    }

    /**
     * Affiche le formulaire de saisie d'un nouveau topo
     * @param topo
     * @param model
     * @return renvoie le formulaire
     */
    @GetMapping("/formulairetopo")
    public String afficherFormulaireAjoutTopo(Topo topo, Model model) {
        model.addAttribute("regions", regionRepository.findAll());
        return "ajoutertopo";
    }

    /**
     * Crée un topo en base
     * @param topo l'instance du topo à créer
     * @param result
     * @param principal les informations de l'utilisateur connecté
     * @param model
     * @return si erreur, réaffiche le formulaire de saisie, sinon appelle la méthode d'affichage de la liste des topos utilisateurs
     */
    @PostMapping("/ajoutertopo")
    public String ajouterTopo(@Valid @ModelAttribute("topo") Topo topo, BindingResult result, Principal principal, Model model) {
        if (result.hasErrors()) {
            return "ajoutertopo";
        }
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(principal.getName());
        topo.setUtilisateur(utilisateur);
        topoRepository.save(topo);
        model.addAttribute("topos", topoRepository.findAll());
        return "redirect:/listetopos/utilisateur";
    }

    /**
     * Affiche le formulaire de modification d'un topo
     * @param id l'id du topo à mofidier
     * @param model
     * @return affiche le formulaire de modification d'un topo préalimenté
     */
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

    /**
     * Enregistre les modifications d'un topo
     * @param id l'id du topo
     * @param topo l'instance du topo modifié
     * @param result
     * @param model
     * @return si erreur, réaffiche le formulaire de modification, sinon appelle la méthode d'affichage de la liste des topos utilisateur
     */
    @PostMapping("/sauvertopo/{id}")
    public String sauverTopo(@PathVariable("id") Integer id, @Valid Topo topo, BindingResult result, Model model) {
        if (result.hasErrors()) {
            topo.setTopoId(id);
            return "majtopo";
        }
        topo.setTopoId(id);
        topoRepository.save(topo);
        return "redirect:/listetopos/utilisateur";
    }

    /**
     * Affiche le détail d'un topo
     * @param topo
     * @return affiche la page d'un détail topo
     */
    @GetMapping("/topo")
    public String afficherTopo(Topo topo) {
        return "topo";
    }

    /**
     * Supprime un topo
     * @param id
     * @param model
     * @return appelle la méthode d'affichage de la listes des topos utilisateur
     */
    @GetMapping("/supprimertopo/{id}")
    public String supprimerTopo(@PathVariable("id") Integer id, Model model) {
        topoRepository.deleteById(id);
        model.addAttribute("topos", topoRepository.findAll());
        return "redirect:/listetopos/utilisateur";
    }

    /**
     * Active ou désactive une demande de réservation d'un topo par un utilisateur connecté
     * @param id id du topo
     * @param principal infos de l'utilisateurs connecté
     * @param model
     * @return appelle la méthode d'affichage de la liste des topos mise à jour
     */
    @GetMapping("/gererdemandeemprunt/{id}")
    public String gererDemandeEmpruntTopo(@PathVariable("id") Integer id, Principal principal, Model model) {
        log.info("Demande d'emprunt par un utilisateur connecté. id topo=" + id + ".");
        Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu : " + id));
        log.info("Demande d'emprunt. Diponibilité=" + topo.getDisponible() + ", utilisateur connecté=" + principal.getName() + ".");
        if (topo.getDisponible().equals("D") && topo.getEmprunteur() == null) {
            log.info("Topage demande de réservation");
            Utilisateur emprunteur = utilisateurRepository.findByPseudo(principal.getName());
            topo.setEmprunteur(emprunteur);
            topo.setDisponible("R");
        } else {
            if (topo.getDisponible().equals("R") && topo.getEmprunteur().getPseudo().equals(principal.getName())) {
                log.info("Détoppage résa");
                Utilisateur emprunteur = utilisateurRepository.findByPseudo(principal.getName());
                topo.setDisponible("D");
                topo.setEmprunteur(null);
            } else {
                log.info("Aucune condition remplie");
            }
        }
        topoRepository.save(topo);
        return "redirect:/listetopos";
    }

    /**
     * Le propriétaire d'un topo accepte la demande d'emprunt d'un topo et rend le topo indisponible
     * @param id id du topo
     * @param principal information de l'utilisateur propriétaire du topo
     * @param model
     * @return appelle la méthode d'affichage de la liste des topos d'un utilisateur mise à jour
     */
    @GetMapping("/accepterpret/{id}")
    public String accepterPretTopo(@PathVariable("id") Integer id, Principal principal, Model model) {
        log.info("Accepter la demande d'emprunt. id topo=" + id + ".");
        Topo topo = topoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant topo inconnu pour acceptation prêt: " + id));
        topo.setDisponible("I");
        topoRepository.save(topo);
        return "redirect:/listetopos/utilisateur";
    }

    /**
     * Le propriétaire du topo refuse le prêt
     * @param id id du topo
     * @param principal infos du propriétaire du topo
     * @param model
     * @return appelle la méthode d'affichage de la liste des topos de l'utilisateur connecté
     */
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
