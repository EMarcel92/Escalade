package com.emmanuel.escalade.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * La longueur est une subdivision d'une voie d'escalade, plus petit élément escaladable.
 * Elle possède les même attributs qu'une voie.
 */
@Entity
@Table(name = "longueur")
public class Longueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "longueurid")
    private Integer longueurid;
    @NotBlank(message = "Nom obligatoire")
    private String nomLongueur;
    private String cotationLongueur;
    @Max(9)
    @NotNull(message = "Nombre de points obligatoire")
    private Integer nbPointsLongueur;
    @ManyToOne
    @JoinColumn(name="voieid", nullable=false)
    private Voie voie;

    public Longueur() {
    }

    public Integer getLongueurid() {
        return longueurid;
    }

    public void setLongueurid(Integer longueurid) {
        this.longueurid = longueurid;
    }

    public String getNomLongueur() {
        return nomLongueur;
    }

    public void setNomLongueur(String nomLongueur) {
        this.nomLongueur = nomLongueur;
    }

    public String getCotationLongueur() {
        return cotationLongueur;
    }

    public void setCotationLongueur(String cotationLongueur) {
        this.cotationLongueur = cotationLongueur;
    }

    public Integer getNbPointsLongueur() {
        return nbPointsLongueur;
    }

    public void setNbPointsLongueur(Integer nbPointsLongueur) {
        this.nbPointsLongueur = nbPointsLongueur;
    }

    public Voie getVoie() {
        return voie;
    }

    public void setVoie(Voie voie) {
        this.voie = voie;
    }
}
