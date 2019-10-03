package com.emmanuel.escalade.Services;

import com.emmanuel.escalade.DAO.RegionRepository;
import com.emmanuel.escalade.DAO.SiteRepository;
import com.emmanuel.escalade.DAO.UtilisateurRepository;
import com.emmanuel.escalade.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List listeSiteAvecCotation() {
        return siteRepository.listeSiteAvecCotation();
    }

    public Iterable<Site> findAll() {
        Iterable<Site> maListe = siteRepository.findAll();
        //for (Site site : maListe) {
        return maListe;
    }
}
