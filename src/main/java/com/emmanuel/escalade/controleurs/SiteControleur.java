package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.DAO.*;
import com.emmanuel.escalade.DTO.SiteCriteres;
import com.emmanuel.escalade.Services.RegionService;
import com.emmanuel.escalade.Services.SiteService;
import com.emmanuel.escalade.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * Traite les requêtes correspondant aux sites d'escalade, à leurs composants (secteurs, voies, longueurs) et
 * les commentaires sur les sites
 */
@Controller
public class SiteControleur {

    private final SiteService siteService;
    private final RegionService regionService;
    private final SecteurRepository secteurRepository;
    private final VoieRepository voieRepository;
    private final LongueurRepository longueurRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final CommentaireRepository commentaireRepository;

    private static final Logger log = LoggerFactory.getLogger(SiteControleur.class);

    @Autowired
    public SiteControleur(SiteService siteService, RegionService regionService, SecteurRepository secteurRepository, VoieRepository voieRepository, LongueurRepository longueurRepository, UtilisateurRepository utilisateurRepository, CommentaireRepository commentaireRepository) {
        this.siteService = siteService;
        this.regionService = regionService;
        this.secteurRepository = secteurRepository;
        this.voieRepository = voieRepository;
        this.longueurRepository = longueurRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.commentaireRepository = commentaireRepository;
    }

    /**
     * Affiche la liste de tous les sites d'escalade
     * @param model
     * @return la page HTML listesites
     */
    @GetMapping("/listesites")  //ou  @RequestMapping(value = {"/listesites" }, method = RequestMethod.GET)
    public String listeSites(Model model) {
        log.info("SiteControleur");
        SiteCriteres siteCriteres = new SiteCriteres();
        List<Site> sites = siteService.listeSiteAvecCotation();
        List<Region> regions = regionService.findAll();
        model.addAttribute("siteCriteres", siteCriteres);
        model.addAttribute("regions",regions);
        model.addAttribute("sites", sites);
        return "listesites";
    }

    /**
     * Gère les critères de recherche de sites et affiche la liste correspondant
     * @param model
     * @param siteCriteres représentation des critères possibles
     * @return page HTML liste des sites filtrée
     */
    @PostMapping("/recherchersites")
    public String listeSitesRecherches (Model model, @ModelAttribute("siteCriteres") SiteCriteres siteCriteres){
        log.info("SiteControleur - listeSitesRecherches");
        List<Site> sites = siteService.rechercherSites(siteCriteres);
        List<Region> regions = regionService.findAll();
        model.addAttribute("regions",regions);
        model.addAttribute("siteCriteres", siteCriteres);
        model.addAttribute("sites",sites);
        return "listesites";
    }

    /**
     * Affiche un site
     * @param id du site à afficher
     * @param model
     * @return page HTML d'un site avec secteurs, voies, longueur
     */
    @GetMapping({"/site/{id}"})
    public String afficherUnSite(@PathVariable("id") Integer id, Model model) {
        Site site = siteService.findById(id);
        model.addAttribute("site", site);
        return "site";
    }

    /**
     * Ajoute un commentaire sur un site
     * @param texteCommentaire texte du commentaire
     * @param siteid identifiant du site
     * @param principal infos sur l'utilisteur
     * @return
     */
    @PostMapping("/ajoutercommentaire")
    public String ajouterCommentaire(@RequestParam(value = "texteCommentaire") String texteCommentaire,
                                     @RequestParam(value="siteid") Integer siteid,
                                     Principal principal){
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(principal.getName());
        siteService.sauverCommentaire(texteCommentaire, siteid, utilisateur);
        return "redirect:/site/" + siteid;
    }

    /**
     * Gère la mise à jour d'un commentaire par un administrateur
     * @param commentaireid identifiant du commentaire
     * @param commentaire texte du commentaire
     * @param result
     * @param model
     * @return renvoie vers la méthode d'affichage du site du commentaire
     */
    @PostMapping("/modifiercommentaire/{commentaireid}")
    public String ModifierCommentaire(@PathVariable("commentaireid") int commentaireid, @Valid Commentaire commentaire,
                                  BindingResult result, Model model) {
        Optional<Commentaire> commentaireBDD = commentaireRepository.findById(commentaireid);
        if (commentaireBDD.isPresent()){
            commentaire.setCommentaireId(commentaireBDD.get().getCommentaireId());
            commentaire.setDateRedaction(commentaireBDD.get().getDateRedaction());
            commentaire.setSite(commentaireBDD.get().getSite());
            commentaire.setUtilisateur(commentaireBDD.get().getUtilisateur());
            commentaireRepository.save(commentaire);
        }
        return "redirect:/site/" + commentaire.getSite().getSiteid();
    }

    /**
     * supprime un commentairesur un site
     * @param commentaireid identifiant du commentaire
     * @return renvoie vers la méthode d'affichage du site du commentaire
     */
    @GetMapping("/supprimercommentaire/{commentaireid}")
    public String supprimerCommentaire(@PathVariable("commentaireid") int commentaireid){
        Commentaire commentaire = commentaireRepository.findById(commentaireid)
                .orElseThrow(() -> new IllegalArgumentException("Commentaire à supprimer n°" + commentaireid + "inconnu"));
        commentaireRepository.delete(commentaire);
        return "redirect:/site/" + commentaire.getSite().getSiteid();
    }

    /**
     * Affiche un formulaire de saisie d'un secteur
     * @param siteid identifiant
     * @param secteur une instance de l'objet Secteur
     * @param model
     * @return la page de saisie du secteur
     */
    @GetMapping("/ajoutersecteur/{siteid}")
    public String afficherFomulaireAjouterSecteur(@PathVariable("siteid") int siteid, Secteur secteur, Model model){
        return "ajoutersecteur";
    }

    /**
     * Ajoute un secteur sur un site
     * @param siteid identifiant du site
     * @param secteur instance d'un secteur
     * @param result
     * @param model
     * @return Si erreur,réaffiche la page se saisie sinon appelle la méthode pour afficher le site du secteur
     */
    @PostMapping("/ajoutersecteur/{siteid}")
    public String ajouterSecteur(@PathVariable("siteid") int siteid, @Valid Secteur secteur, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "ajoutersecteur";
        }
        secteur.setSite(siteService.findById(siteid));
        secteurRepository.save(secteur);
        return "redirect:/site/" + secteur.getSite().getSiteid();
    }

    /**
     * Affiche le formulaire de modification d'un secteur
     * @param secteurid identifiant du secteur
     * @param model
     * @return affiche la page de modifiaction d'un secteur préalimentée
     */
    @GetMapping("/modifiersecteur/{secteurid}")
    public String afficherFormulaireModifierSecteur(@PathVariable("secteurid") int secteurid, Model model) {
        Secteur secteur = secteurRepository.findById(secteurid)
                .orElseThrow(() -> new IllegalArgumentException("Id secteur inconnu :" + secteurid));
        model.addAttribute("secteur", secteur);
        return "majsecteur";
    }

    /**
     * Modifie un secteur d'un site
     * @param secteurid identifiant du secteur
     * @param secteur instance du secteur modifié
     * @param result
     * @param model
     * @return Affiche le site du secteur sauf si erreur, réaffiche la page de mise à jour du secteur
     */
    @PostMapping("/modifiersecteur/{secteurid}")
    public String ModifierSecteur(@PathVariable("secteurid") int secteurid, @Valid Secteur secteur,
                                  BindingResult result, Model model) {
//        log.info("SiteControleur - siteid = ");
        if (result.hasErrors()) {
            secteur.setSecteurid(secteurid);
            return "majsecteur";
        }
        secteurRepository.save(secteur);
        return "redirect:/site/" + secteur.getSite().getSiteid();
    }

    /**
     * Supprime définitivement un secteur et les voies et longueurs liées
     * @param secteurid identifiant du secteur
     * @param model
     * @return renvoie vers la méthode d'affichage du site du commentaire
     */
    @GetMapping("/supprimersecteur/{secteurid}")
    public String supprimersecteur(@PathVariable("secteurid") int secteurid, Model model) {
        Secteur secteur = secteurRepository.findById(secteurid)
                .orElseThrow(() -> new IllegalArgumentException("Secteur à supprimer n°" + secteurid + "inconnu"));
        secteurRepository.delete(secteur);
        return "redirect:/site/" + secteur.getSite().getSiteid();
    }

    /**
     * Affiche le formulaire de saisie d'une nouvelle voie
     * @param secteurid identifiant du secteur
     * @param voie instance d'une voie
     * @param model
     * @return affiche la page de formulaire de saisie d'une voie
     */
    @GetMapping("/ajoutervoie/{secteurid}")
    public String afficherFomulaireAjouterVoie(@PathVariable("secteurid") int secteurid, Voie voie, Model model){
        return "ajoutervoie";
    }

    /**
     * Ajoute une voie à un secteur
     * @param secteurid id du secteur
     * @param voie instance d'une voie
     * @param result
     * @param model
     * @return Si erreur, réaffiche le formulaire, sinon renvoie vers la méthode d'affichage du site de la voie
     */
    @PostMapping("/ajoutervoie/{secteurid}")
    public String ajouterVoie(@PathVariable("secteurid") int secteurid, @Valid Voie voie, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "ajoutervoie";
        }
        voie.setSecteur(siteService.findSecteurById(secteurid));
        voieRepository.save(voie);
        return "redirect:/site/" + voie.getSecteur().getSite().getSiteid();
    }

    /**
     * Affiche le formulaire de modification d'une voie
     * @param voieid identifiant d'une voie
     * @param model
     * @return affiche la page de modifiaction d'une voie préalimentée
     */
    @GetMapping("/modifiervoie/{voieid}")
    public String afficherFormulaireModifierVoie(@PathVariable("voieid") int voieid, Model model) {
        Voie voie = voieRepository.findById(voieid)
                .orElseThrow(() -> new IllegalArgumentException("Id voie inconnu :" + voieid));
        model.addAttribute("voie", voie);
        return "majvoie";
    }

    /**
     * Modifie une voie d'un secteur
     * @param voieid identifiant de la voie
     * @param voie instance du secteur modifié
     * @param result
     * @param model
     * @return Affiche le site de la voie sauf si erreur, réaffiche la page de mise à jour de la voie
     */
    @PostMapping("/modifiervoie/{voieid}")
    public String ModifierVoie(@PathVariable("voieid") int voieid, @Valid Voie voie,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            voie.setVoieid(voieid);
            return "majvoie";
        }
        voieRepository.save(voie);
        return "redirect:/site/" + siteService.findVoieById(voieid).getSecteur().getSite().getSiteid();
    }

    /**
     * Supprime une voie et les longueurs liées
     * @param voieid id de la voie
     * @param model
     * @return renvoie vers la méthode d'affichage du site de la voie
     * @exception IllegalArgumentException si la voie n'existe pas
     */
    @GetMapping("/supprimervoie/{voieid}")
    public String supprimervoie(@PathVariable("voieid") int voieid, Model model) {
        Voie voie = voieRepository.findById(voieid)
                .orElseThrow(() -> new IllegalArgumentException("Voie à supprimer n°" + voieid + "inconnue"));
        voieRepository.delete(voie);
        return "redirect:/site/" + voie.getSecteur().getSite().getSiteid();
    }

    /**
     * Affiche le formulaire des création d'une longueur à une voie
     * @param voieid id de la voie
     * @param longueur instance de la longueur à créer
     * @param model
     * @return Affiche le formulaire de siasie d'une longueur
     */
    @GetMapping("/ajouterlongueur/{voieid}")
    public String afficherFomulaireAjouterLongueur(@PathVariable("voieid") int voieid, Longueur longueur, Model model){
        return "ajouterlongueur";
    }

    /**
     * Ajout d'une longueur sur une voie
     * @param voieid id de la voie
     * @param longueur instance de la longueur à créer
     * @param result
     * @param model
     * @return Si erreur, réaffiche le formulaire de création, sinon renvoie vers la méthode d'affichage du site de la longueur
     */
    @PostMapping("/ajouterlongueur/{voieid}")
    public String ajouterLongueur(@PathVariable("voieid") int voieid, @Valid Longueur longueur, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "ajouterlongueur";
        }
        longueur.setVoie(siteService.findVoieById(voieid));
        longueurRepository.save(longueur);
        return "redirect:/site/" + longueur.getVoie().getSecteur().getSite().getSiteid();
    }

    /**
     * Affiche le formulaire de modification d'une longueur
     * @param longueurid id de la longueur
     * @param model
     * @return Affcihe le formulaire de modification de la longueur préalimenté
     * @exception IllegalArgumentException si la longueur n'existe pas
     */
    @GetMapping("/modifierlongueur/{longueurid}")
    public String afficherFormulaireModifierLongueur(@PathVariable("longueurid") int longueurid, Model model) {
        Longueur longueur = longueurRepository.findById(longueurid)
                .orElseThrow(() -> new IllegalArgumentException("Id longueur inconnu :" + longueurid));
        model.addAttribute("longueur", longueur);
        return "majlongueur";
    }

    /**
     * Modifie une longueur
     * @param longueurid id de la longueur
     * @param longueur instance de la longueur
     * @param result
     * @param model
     * @return Si erreur, réaffiche le formulaire de modification, sinon renvoie vers la méthode d'affichage du site de la longueu
     */
    @PostMapping("/modifierlongueur/{longueurid}")
    public String ModifierLongueur(@PathVariable("longueurid") int longueurid, @Valid Longueur longueur,
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            longueur.setLongueurid(longueurid);
            return "majlongueur";
        }
        longueurRepository.save(longueur);
        return "redirect:/site/" + siteService.findLongueurById(longueurid).getVoie().getSecteur().getSite().getSiteid();
    }

    /**
     * Supprime une longueur
     * @param longueurid id de la longueur
     * @param model
     * @return Renvoie vers la méthode d'affichage du site de la longueur
     * @exception IllegalArgumentException si la longueur n'existe pas
     */
    @GetMapping("/supprimerlongueur/{longueurid}")
    public String supprimerlongueur(@PathVariable("longueurid") int longueurid, Model model) {
        Longueur longueur = longueurRepository.findById(longueurid)
                .orElseThrow(() -> new IllegalArgumentException("Longueur à supprimer n°" + longueurid + "inconnue"));
        longueurRepository.delete(longueur);
        return "redirect:/site/" + longueur.getVoie().getSecteur().getSite().getSiteid();
    }

    /**
     * Tague ou détague un site
     * @param siteid
     * @return Réaffichge le site avec le tag de l'association ajouter ou supprimer en fonction du contexte
     */
    @GetMapping("/taguersite/{siteid}")
    public String taguerSite(@PathVariable("siteid") int siteid) {
        siteService.changerTagSite(siteid);
        return "redirect:/site/" + siteid;
    }
}