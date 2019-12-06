package com.emmanuel.escalade.Services;

import com.emmanuel.escalade.DAO.*;
import com.emmanuel.escalade.DTO.SiteCriteres;
import com.emmanuel.escalade.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SiteService {

    private static final Logger log = LoggerFactory.getLogger(SiteService.class);

    private final SiteRepository siteRepository;
    private final RegionRepository regionRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final CommentaireRepository commentaireRepository;
    private final SecteurRepository secteurRepository;
    private final VoieRepository voieRepository;
    private final LongueurRepository longueurRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository, RegionRepository regionRepository,
                       UtilisateurRepository utilisateurRepository, CommentaireRepository commentaireRepository,
                       SecteurRepository secteurRepository, VoieRepository voieRepository,
                       LongueurRepository longueurRepository) {
        this.siteRepository = siteRepository;
        this.regionRepository = regionRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.commentaireRepository = commentaireRepository;
        this.secteurRepository = secteurRepository;
        this.voieRepository = voieRepository;
        this.longueurRepository = longueurRepository;
    }

    public List<Site> listeSiteAvecCotation() {
        return siteRepository.findAll();
    }

    public Site findById (int id){
        Optional<Site> resultat = siteRepository.findById(id);
        if (resultat.isPresent()){
            List<Commentaire> commentaires = resultat.get().getCommentaires();
        }
        return (resultat.isPresent()?resultat.get():null);
    }

    public Secteur findSecteurById (int id){
        Optional<Secteur> resultat = secteurRepository.findById(id);
        return (resultat.isPresent()?resultat.get():null);
    }

    public Voie findVoieById (int id){
        Optional<Voie> resultat = voieRepository.findById(id);
        return (resultat.isPresent()?resultat.get():null);
    }

    public Longueur findLongueurById (int id){
        Optional<Longueur> resultat = longueurRepository.findById(id);
        return (resultat.isPresent()?resultat.get():null);
    }

    public Iterable<Site> findAll() {
        Iterable<Site> maListe = siteRepository.findAll();
        return maListe;
    }

    public List<Site> rechercherSites(SiteCriteres siteCriteres) {
        List<Site> maListe = siteRepository.findSitesByNomAndRegionAndCotationAndNbSecteurs(siteCriteres);
        return  maListe;
    }


    public void sauverCommentaire(String texteCommentaire, Integer siteid, Utilisateur utilisateur) {
        Commentaire commentaire = new Commentaire();
        commentaire.setTexteCommentaire(texteCommentaire);
        commentaire.setSite(siteRepository.findById(siteid).get());
        commentaire.setUtilisateur(utilisateur);
        commentaire.setDateRedaction(LocalDateTime.now());
        commentaireRepository.save(commentaire);
    }
}
