package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.DTO.SiteCriteres;
import com.emmanuel.escalade.DTO.SiteDto;
import com.emmanuel.escalade.Services.RegionService;
import com.emmanuel.escalade.Services.SiteService;
import com.emmanuel.escalade.model.Region;
import com.emmanuel.escalade.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SiteControleur {

    private final SiteService siteService;
    private final RegionService regionService;

    private static final Logger log = LoggerFactory.getLogger(SiteControleur.class);

    @Autowired
    public SiteControleur(SiteService siteService, RegionService regionService) {
        this.siteService = siteService;
        this.regionService = regionService;
    }

    @RequestMapping(value = {"/listesites" }, method = RequestMethod.GET)
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

    @PostMapping(value = "/recherchersites")
    public String listeSitesRecherches (Model model, @ModelAttribute("siteCriteres") SiteCriteres siteCriteres){
        log.info("SiteControleur - listeSitesRecherches");
        List<Site> sites = siteService.rechercherSites(siteCriteres);
        List<Region> regions = regionService.findAll();
        model.addAttribute("regions",regions);
        model.addAttribute("siteCriteres", siteCriteres);
        model.addAttribute("sites",sites);
        return "listesites";
    }

    @RequestMapping(value = {"/site/{id}"} , method = RequestMethod.GET)
    public String unSite(@PathVariable("id") Integer id, Model model) {
        Site site = siteService.findById(id);
        model.addAttribute("site", site);
        return "site";
    }

    @PostMapping(value = "/ajoutercommentaire")
    public String ajouterComentaire (@RequestParam(value = "texteCommentaire") String texteCommentaire,
                                     @RequestParam(value="siteid") Integer siteid){
        siteService.sauverCommentaire(texteCommentaire, siteid);
        return "redirect:/site/" + siteid;
    }
}
