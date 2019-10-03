package com.emmanuel.escalade.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "voie")
public class Voie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voieid")
    private Integer voieid;
    private String nomVoie;
    @ManyToOne
    @JoinColumn(name="secteurid", nullable=false)
    private Secteur secteur;
//    @OneToMany (mappedBy = "voie", cascade = CascadeType.ALL)
//    private List<Longueur> longueurs;

    public Voie() {
    }

    public Voie(String nomVoie, Secteur secteur, List<Longueur> longueurs) {
        this.nomVoie = nomVoie;
        this.secteur = secteur;
//        this.longueurs = longueurs;
    }

    public Integer getVoieid() {
        return voieid;
    }

    public void setVoieid(Integer voieid) {
        this.voieid = voieid;
    }

    public String getNom_voie() {
        return nomVoie;
    }

    public void setNom_voie(String nom_voie) {
        this.nomVoie = nom_voie;
    }

    public Secteur getSecteur() {
        return secteur;
    }

    public void setSecteur(Secteur secteur) {
        this.secteur = secteur;
    }

//    public List<Longueur> getLongueurs() {
//        return longueurs;
//    }
//
//    public void setLongueurs(List<Longueur> longueurs) {
//        this.longueurs = longueurs;
//    }
}

