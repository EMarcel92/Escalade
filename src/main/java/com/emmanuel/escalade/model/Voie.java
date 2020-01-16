package com.emmanuel.escalade.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Chemin à emprunter par le grimpeur.
 * Subdivision d'un secteur d'escalade, pouvant elle-même être divisée en longueurs.
 */
@Entity
@Table(name = "voie")
public class Voie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voieid")
    private Integer voieid;
    @NotBlank(message = "Nom de voie obligatoire")
    private String nomVoie;
    private String cotationVoie;
    @NotNull(message = "Nombre de points obligatoire")
    private Integer nbPointsVoie;
    @ManyToOne
    @JoinColumn(name="secteurid", nullable=false)
    private Secteur secteur;
    @OneToMany (mappedBy = "voie", cascade = CascadeType.ALL)
    private List<Longueur> longueurs;

    public Voie() {
    }

    public String getNomVoie() {
        return nomVoie;
    }

    public void setNomVoie(String nomVoie) {
        this.nomVoie = nomVoie;
    }

    public String getCotationVoie() {
        return cotationVoie;
    }

    public void setCotationVoie(String cotationVoie) {
        this.cotationVoie = cotationVoie;
    }

    public Integer getNbPointsVoie() {
        return nbPointsVoie;
    }

    public void setNbPointsVoie(Integer nbPointsVoie) {
        this.nbPointsVoie = nbPointsVoie;
    }

    public Integer getVoieid() {
        return voieid;
    }

    public void setVoieid(Integer voieid) {
        this.voieid = voieid;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

    public List<Longueur> getLongueurs() {
        return longueurs;
    }

    public void setLongueurs(List<Longueur> longueurs) {
        this.longueurs = longueurs;
    }

}

