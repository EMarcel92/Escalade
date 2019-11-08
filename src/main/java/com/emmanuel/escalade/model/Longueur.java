package com.emmanuel.escalade.model;

import javax.persistence.*;

@Entity
@Table(name = "longueur")
public class Longueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "longueurid")
    private Integer longueurid;
    private String nomLongueur;
    private String cotationLongueur;
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
