package com.emmanuel.escalade.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "topo")
public class Topo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int topoid;
    private String nomTopo;
    private String descritpionTopo;
    private Date dateParution;
    private boolean disponible;
    @ManyToOne
    @JoinColumn
    private Region region;
//    @ManyToOne
//    @JoinColumn
//    private int utilisaeurId;

    public Topo() {
    }

    public Topo(String nomTopo, String descritpionTopo, Date dateParution, boolean disponible, int regionId, int utilisaeurId) {
        this.nomTopo = nomTopo;
        this.descritpionTopo = descritpionTopo;
        this.dateParution = dateParution;
        this.disponible = disponible;
    //    this.regionId = regionId;
  //      this.utilisaeurId = utilisaeurId;
    }

    public int getTopoid() {
        return topoid;
    }

    public void setTopoid(int topoid) {
        this.topoid = topoid;
    }

    public String getNomTopo() {
        return nomTopo;
    }

    public void setNomTopo(String nomTopo) {
        this.nomTopo = nomTopo;
    }

    public String getDescritpionTopo() {
        return descritpionTopo;
    }

    public void setDescritpionTopo(String descritpionTopo) {
        this.descritpionTopo = descritpionTopo;
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

//    public int getRegionId() {
//        return regionId;
//    }
//
//    public void setRegionId(int regionId) {
//        this.regionId = regionId;
//    }

//    public int getUtilisaeurId() {
//        return utilisaeurId;
//    }
//
//    public void setUtilisaeurId(int utilisaeurId) {
//        this.utilisaeurId = utilisaeurId;
//    }

    @Override
    public String toString() {
        return "Topo{" +
                "topoid=" + topoid +
                ", nomTopo='" + nomTopo + '\'' +
                ", descritpionTopo='" + descritpionTopo + '\'' +
                ", dateParution=" + dateParution +
                ", disponible=" + disponible +
  //              ", regionId=" + regionId +
  //              ", utilisaeurId=" + utilisaeurId +
                '}';
    }
}
