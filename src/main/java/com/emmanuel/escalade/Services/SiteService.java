package com.emmanuel.escalade.Services;

import com.emmanuel.escalade.DAO.RegionRepository;
import com.emmanuel.escalade.DAO.SiteRepository;
import com.emmanuel.escalade.DAO.UtilisateurRepository;
import com.emmanuel.escalade.DTO.SiteCriteres;
import com.emmanuel.escalade.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    private static final Logger log = LoggerFactory.getLogger(SiteService.class);

    private final SiteRepository siteRepository;
    private final RegionRepository regionRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository, RegionRepository regionRepository, UtilisateurRepository utilisateurRepository) {
        this.siteRepository = siteRepository;
        this.regionRepository = regionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Site> listeSiteAvecCotation() {
        return siteRepository.findAll();
    }

    public Site findById (int id){
        Optional<Site> result = siteRepository.findById(id);
        return (result.isPresent()?result.get():null);
    }

    public Iterable<Site> findAll() {
        Iterable<Site> maListe = siteRepository.findAll();
        return maListe;
    }

    public List<Site> rechercherSites(SiteCriteres siteCriteres) {
        List<Site> maListe = siteRepository.findSitesByNomAndRegionAndCotationAndNbSecteurs(siteCriteres);
        return  maListe;
    }
}
