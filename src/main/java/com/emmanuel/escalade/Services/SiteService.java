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

/**
 * Gestion des sites d'escalade (y compris les secteurs, voies, longuers) et les commentaires inhérents
 */
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

    /**
     * Renvoie la liste des sites d'escalade
     * @return liste des sites
     */
    public List<Site> listeSiteAvecCotation() {
        return siteRepository.findAll();
    }

    /**
     * Renvoie les infos détaillées d'un site avec les commentaires associés
     * @param id identifiant du site
     * @return infos du site + commentaires
     */
    public Site findById (int id){
        Optional<Site> resultat = siteRepository.findById(id);
        if (resultat.isPresent()){
            List<Commentaire> commentaires = resultat.get().getCommentaires();
        }
        return (resultat.isPresent()?resultat.get():null);
    }

    /**
     * Renvoie les infos d'un secteur
     * @param id identifiant du secteur
     * @return secteur
     */
    public Secteur findSecteurById (int id){
        Optional<Secteur> resultat = secteurRepository.findById(id);
        return (resultat.isPresent()?resultat.get():null);
    }

    /**
     * Renvoie les infos d'une voie
     * @param id identifiant de la voie
     * @return voie
     */
    public Voie findVoieById (int id){
        Optional<Voie> resultat = voieRepository.findById(id);
        return (resultat.isPresent()?resultat.get():null);
    }

    /**
     * Renvoie les infos d'une longueur
     * @param id identifiant d'une longueur
     * @return longueur
     */
    public Longueur findLongueurById (int id){
        Optional<Longueur> resultat = longueurRepository.findById(id);
        return (resultat.isPresent()?resultat.get():null);
    }

    /**
     * Renvoie la liste des sites avec la descriptions des secteurs, voies , longueurs
     * @return Liste des sites d'escalade
     */
    public Iterable<Site> findAll() {
        Iterable<Site> maListe = siteRepository.findAll();
        return maListe;
    }

    /**
     * Recherche la liste des sites correspondant à un ensemble de critères
     * @param siteCriteres entité regroupant l'ensemble des critères gérés
     * @return liste de sites filtrée
     */
    public List<Site> rechercherSites(SiteCriteres siteCriteres) {
        List<Site> maListe = siteRepository.findSitesByNomAndRegionAndCotationAndNbSecteurs(siteCriteres);
        return  maListe;
    }

    /**
     * Sauvegarde en base un commentaire d'utilisateur sur un site
     * @param texteCommentaire texte du commentaire
     * @param siteid identifiant du site
     * @param utilisateur Entité utilisteur auteur du commentaire
     */
    public void sauverCommentaire(String texteCommentaire, Integer siteid, Utilisateur utilisateur) {
        Commentaire commentaire = new Commentaire();
        commentaire.setTexteCommentaire(texteCommentaire);
        commentaire.setSite(siteRepository.findById(siteid).get());
        commentaire.setUtilisateur(utilisateur);
        commentaire.setDateRedaction(LocalDateTime.now());
        commentaireRepository.save(commentaire);
    }

    /**
     * Active ou désactive le tag "officel" sur un site d'escalade
     * Si le site est tagué, il le détague et inversement
     * @param siteid identifiant du site
     */
    public void changerTagSite (int siteid){
        Site site = findById(siteid);
        if (site != null){
            site.setTagOfficiel(!site.getTagOfficiel());
            siteRepository.save(site);
        }
    }
}
