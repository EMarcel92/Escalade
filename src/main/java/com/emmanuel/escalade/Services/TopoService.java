package com.emmanuel.escalade.Services;

import com.emmanuel.escalade.DAO.*;
import com.emmanuel.escalade.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopoService {
    private static final Logger log = LoggerFactory.getLogger(TopoService.class);

    private final TopoRepository topoRepository;
    private final RegionRepository regionRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public TopoService(TopoRepository topoRepository, RegionRepository regionRepository,
                       UtilisateurRepository utilisateurRepository) {
        this.topoRepository = topoRepository;
        this.regionRepository = regionRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public void noterEmprunteurTopo (String pseudo){
        Utilisateur emprunteur = utilisateurRepository.findByPseudo(pseudo);
        if (emprunteur != null){

          //  siteRepository.save(site);
        }
    }

}


