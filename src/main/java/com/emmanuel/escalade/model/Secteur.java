package com.emmanuel.escalade.model;

import javax.persistence.*;

@Entity
@Table(name = "secteur")
public class Secteur {
// secteurid integer PK
// nom_secteur varchar
// siteid FK  integer

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom_secteur;

    public Secteur() {
    }

    public Secteur(String nom_secteur) {
        this.nom_secteur = nom_secteur;
    }

   /* public Secteur(String nom_secteur, Double price) {
        this.nom_secteur = nom_secteur;
    } */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomsecteur() {
        return nom_secteur;
    }

    public void setNomSecteur(String nom_secteur) {
        this.nom_secteur = nom_secteur;
    }

    @Override
    public String toString() {
        return "Secteur{" +
                "id=" + id +
                ", nom secteur='" + nom_secteur + '\'' +
                '}';
    }
}
