package com.emmanuel.escalade.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Table(name = "topo")
public class Topo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //génère une nouvelle valeur à chaque entité commitée
    @Column(name="topoid")  // indique le nom de la colonne correspondante dans la table
    private Integer topoId;
    @NotBlank(message = "Nom de topo obligatoire")
    private String nomTopo;
    @NotBlank(message = "Description obligatoire")
    private String descriptionTopo;
    @NotNull(message = "Date du topo obligatoire")
    private Date dateParution;
    @NotNull(message = "Disponibilité obligatoire")
    private String disponible;
    @ManyToOne
    @JoinColumn(name="regionid", nullable=false)
    @NotNull(message = "Région obligatoire")
    private Region region;

    @ManyToOne
    @JoinColumn(name="utilisateurid", nullable=false)
    //la clé du parent est utilisateurid dans la table enfant topo (et pas dans l'entité enfant en Java)
    private Utilisateur utilisateur;   //L'objet Utilisateur est le parent de topo
    @ManyToOne
    @JoinColumn(name="emprunteurid", nullable=true)
    private Utilisateur emprunteur;

    public Topo() {
    }

    public Topo(String nomTopo, String descriptionTopo, Date dateParution, String disponible, Region region, Utilisateur utilisateur, Utilisateur emprunteur) {
        this.nomTopo = nomTopo;
        this.descriptionTopo = descriptionTopo;
        this.dateParution = dateParution;
        this.disponible = disponible;
        this.region = region;
        this.utilisateur = utilisateur;
        this.emprunteur = emprunteur;
    }

    public Integer getTopoId() {
        return topoId;
    }

    public void setTopoId(Integer topoId) {
        this.topoId = topoId;
    }

    public String getNomTopo() {
        return nomTopo;
    }

    public void setNomTopo(String nomTopo) {
        this.nomTopo = nomTopo;
    }

    public String getDescriptionTopo() {
        return descriptionTopo;
    }

    public void setDescriptionTopo(String descriptionTopo) {
        this.descriptionTopo = descriptionTopo;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getEmprunteur() {
        return emprunteur;
    }

    public void setEmprunteur(Utilisateur emprunteur) {
        this.emprunteur = emprunteur;
    }
}