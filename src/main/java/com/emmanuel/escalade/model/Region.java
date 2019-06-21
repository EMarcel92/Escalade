package com.emmanuel.escalade.model;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regionId;
    private String nomRegion;
    @OneToMany(mappedBy = "region", cascade = CascadeType.ALL)
    private Set<Topo> topos;

    public Region() {
    }

//    public Region(String nomRegion, Topo... topos) {
//        this.nomRegion = nomRegion;
//        this.topos = Stream.of(topos).collect(Collectors.toSet());
//        this.topos.forEach(x -> x.setRegion(this));
//    }

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

    @Override
    public String toString() {
        return "Region{" +
                "regionId=" + regionId +
                ", nomRegion='" + nomRegion + '\'' +
                '}';
    }
}


