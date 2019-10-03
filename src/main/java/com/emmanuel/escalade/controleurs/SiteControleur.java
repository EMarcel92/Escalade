package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.Services.SiteService;
import com.emmanuel.escalade.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SiteControleur {

//    private final SiteRepository siteRepository;
//    private final RegionRepository regionRepository;
//    private final UtilisateurRepository utilisateurRepository;
    private final SiteService siteService;

    private static final Logger log = LoggerFactory.getLogger(SiteControleur.class);

    @Autowired
    public SiteControleur(SiteService siteService) {
        this.siteService = siteService;
//    public SiteControleur(SiteRepository siteRepository, RegionRepository regionRepository, UtilisateurRepository utilisateurRepository) {
//        this.siteRepository = siteRepository;
//        this.regionRepository = regionRepository;
//        this.utilisateurRepository = utilisateurRepository;
           }

    @RequestMapping(value = {"/listesites" }, method = RequestMethod.GET)
    public String listeSites(Model model) {
        log.info("SiteControleur");
//        Iterable<Site> sites = siteService.findAll();
//        model.addAttribute("sites", sites);
//        model.addAttribute("cotationMin","progress-bar bg-danger");
        List<Site> sites = siteService.listeSiteAvecCotation();
        model.addAttribute("sites", sites);
        model.addAttribute("cotationMin","progress-bar bg-danger");

        return "listesites";
    }
    @RequestMapping(value = {"/site" }, method = RequestMethod.GET)
    public String unSite(Model model) {
        return "site";
    }

}
