package com.emmanuel.escalade.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "topo")
public class Topo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="topoid")
    private Integer topoId;
    private String nomTopo;
    private String descriptionTopo;
    private Date dateParution;
    private boolean disponible;
    @ManyToOne
    @JoinColumn(name="regionid", nullable=false)
    private Region region;
    @ManyToOne
    @JoinColumn(name="utilisateurid", nullable=false)  //la clé du parent est utilisateurid dans la table enfant (et pas dans l'entité enfant)
    private Utilisateur utilisateur;   //L'objet Utilisateur est le parent de topo

    public Topo() {
    }

    public Topo(String nomTopo, String descriptionTopo, Date dateParution, boolean disponible, Region region, Utilisateur utilisateur) {
        this.nomTopo = nomTopo;
        this.descriptionTopo = descriptionTopo;
        this.dateParution = dateParution;
        this.disponible = disponible;
        this.region = region;
        this.utilisateur = utilisateur;
    }

    public Integer getTopoId() {  return topoId;    }

    public void setTopoId(Integer topoId) { this.topoId = topoId; }

    public String getNomTopo() {
        return nomTopo;
    }

    public void setNomTopo(String nomTopo) {
        this.nomTopo = nomTopo;
    }

    public String getDescritpionTopo() {
        return descriptionTopo;
    }

    public void setDescritpionTopo(String descritpionTopo) {
        this.descriptionTopo = descritpionTopo;
    }

    public Date getDateParution() {
        return dateParution;
    }

    public void setDateParution(Date dateParution) {
        this.dateParution = dateParution;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
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

    public void setUtilisateur(Utilisateur utilisateur) {this.utilisateur = utilisateur; }
}
