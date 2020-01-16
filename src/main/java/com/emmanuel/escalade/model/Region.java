package com.emmanuel.escalade.model;

import javax.persistence.*;
import java.util.List;

/**
 * Zone géographique dans laquelle on trouve les sites d'escalade
 * et à laquelle peuvent être rattachés des topos
 */
@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="regionid")
    private int regionId;
    private String nomRegion;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Topo> topos;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private List<Site> sites;

    public Region() {
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public List<Topo> getTopo() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    public void addTopo (Topo t) {
        t.setRegion(this);
        topos.add(t) ;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}


