package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.DAO.LongueurRepository;
import com.emmanuel.escalade.DAO.SecteurRepository;
import com.emmanuel.escalade.DAO.UtilisateurRepository;
import com.emmanuel.escalade.DAO.VoieRepository;
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

@Controller
public class SiteControleur {

    private final SiteService siteService;
    private final RegionService regionService;
    private final SecteurRepository secteurRepository;
    private final VoieRepository voieRepository;
    private final LongueurRepository longueurRepository;
    private final UtilisateurRepository utilisateurRepository;

    private static final Logger log = LoggerFactory.getLogger(SiteControleur.class);

    @Autowired
    public SiteControleur(SiteService siteService, RegionService regionService, SecteurRepository secteurRepository, VoieRepository voieRepository, LongueurRepository longueurRepository, UtilisateurRepository utilisateurRepository) {
        this.siteService = siteService;
        this.regionService = regionService;
        this.secteurRepository = secteurRepository;
        this.voieRepository = voieRepository;
        this.longueurRepository = longueurRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

  //  @RequestMapping(value = {"/listesites" }, method = RequestMethod.GET)
    @GetMapping("/listesites")
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

    @GetMapping({"/site/{id}"})
    public String unSite(@PathVariable("id") Integer id, Model model) {
        Site site = siteService.findById(id);
        //todo trier les commentaires par date décroissante
        model.addAttribute("site", site);
        return "site";
    }

    @PostMapping("/ajoutercommentaire")
    public String ajouterCommentaire(@RequestParam(value = "texteCommentaire") String texteCommentaire,
                                     @RequestParam(value="siteid") Integer siteid,
                                     Principal principal){
        Utilisateur utilisateur = utilisateurRepository.findByPseudo(principal.getName());
        siteService.sauverCommentaire(texteCommentaire, siteid, utilisateur);
        return "redirect:/site/" + siteid;
    }

    @GetMapping("/ajoutersecteur/{siteid}")
    public String afficherFomulaireAjouterSecteur(@PathVariable("siteid") int siteid, Secteur secteur, Model model){
      //  secteur.setSite(siteService.findById(siteid));
     //   model.addAttribute("siteId", siteid);
        return "ajoutersecteur";
    }

    @PostMapping("/ajoutersecteur/{siteid}")
    public String ajouterSecteur(@PathVariable("siteid") int siteid, @Valid Secteur secteur, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "ajoutersecteur";
        }
        secteur.setSite(siteService.findById(siteid));
        secteurRepository.save(secteur);
        return "redirect:/site/" + secteur.getSite().getSiteid();
    }

    @GetMapping("/modifiersecteur/{secteurid}")
    public String afficherFormulaireModifierSecteur(@PathVariable("secteurid") int secteurid, Model model) {
        Secteur secteur = secteurRepository.findById(secteurid)
                .orElseThrow(() -> new IllegalArgumentException("Id secteur inconnu :" + secteurid));
        model.addAttribute("secteur", secteur);
        return "majsecteur";
    }

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

    @GetMapping("/supprimersecteur/{secteurid}")
    public String supprimersecteur(@PathVariable("secteurid") int secteurid, Model model) {
        Secteur secteur = secteurRepository.findById(secteurid)
                .orElseThrow(() -> new IllegalArgumentException("Secteur à supprimer n°" + secteurid + "inconnu"));
        secteurRepository.delete(secteur);
        return "redirect:/site/" + secteur.getSite().getSiteid();
    }

    @GetMapping("/ajoutervoie/{secteurid}")
    public String afficherFomulaireAjouterVoie(@PathVariable("secteurid") int secteurid, Voie voie, Model model){
        return "ajoutervoie";
    }

    @PostMapping("/ajoutervoie/{secteurid}")
    public String ajouterVoie(@PathVariable("secteurid") int secteurid, @Valid Voie voie, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "ajoutervoie";
        }
        voie.setSecteur(siteService.findSecteurById(secteurid));
        voieRepository.save(voie);
        return "redirect:/site/" + voie.getSecteur().getSite().getSiteid();
    }

    @GetMapping("/modifiervoie/{voieid}")
    public String afficherFormulaireModifierVoie(@PathVariable("voieid") int voieid, Model model) {
        Voie voie = voieRepository.findById(voieid)
                .orElseThrow(() -> new IllegalArgumentException("Id voie inconnu :" + voieid));
        model.addAttribute("voie", voie);
        return "majvoie";
    }

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

    @GetMapping("/supprimervoie/{voieid}")
    public String supprimervoie(@PathVariable("voieid") int voieid, Model model) {
        Voie voie = voieRepository.findById(voieid)
                .orElseThrow(() -> new IllegalArgumentException("Voie à supprimer n°" + voieid + "inconnue"));
        voieRepository.delete(voie);
        return "redirect:/site/" + voie.getSecteur().getSite().getSiteid();
    }




    @GetMapping("/ajouterlongueur/{voieid}")
    public String afficherFomulaireAjouterLongueur(@PathVariable("voieid") int voieid, Longueur longueur, Model model){
        return "ajouterlongueur";
    }

    @PostMapping("/ajouterlongueur/{voieid}")
    public String ajouterLongueur(@PathVariable("voieid") int voieid, @Valid Longueur longueur, BindingResult result, Model model){
        if (result.hasErrors()) {
            return "ajouterlongueur";
        }
        longueur.setVoie(siteService.findVoieById(voieid));
        longueurRepository.save(longueur);
        return "redirect:/site/" + longueur.getVoie().getSecteur().getSite().getSiteid();
    }

    @GetMapping("/modifierlongueur/{longueurid}")
    public String afficherFormulaireModifierLongueur(@PathVariable("longueurid") int longueurid, Model model) {
        Longueur longueur = longueurRepository.findById(longueurid)
                .orElseThrow(() -> new IllegalArgumentException("Id longueur inconnu :" + longueurid));
        model.addAttribute("longueur", longueur);
        return "majlongueur";
    }

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

    @GetMapping("/supprimerlongueur/{longueurid}")
    public String supprimerlongueur(@PathVariable("longueurid") int longueurid, Model model) {
        Longueur longueur = longueurRepository.findById(longueurid)
                .orElseThrow(() -> new IllegalArgumentException("Longueur à supprimer n°" + longueurid + "inconnue"));
        longueurRepository.delete(longueur);
        return "redirect:/site/" + longueur.getVoie().getSecteur().getSite().getSiteid();
    }
}
