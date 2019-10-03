package com.emmanuel.escalade.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "site")
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="siteid")
    private Integer siteid;
    private String nomSite;
    private String descriptionSite;
    private String photo;
 //   @Column(name = "tag_officiel")
    private Boolean tagOfficiel;
    @ManyToOne
    @JoinColumn(name="regionid", nullable=false)
    private Region region;
    @OneToMany (mappedBy = "site", cascade = CascadeType.ALL)
    private List<Secteur> secteurs;

//    @Transient
//    private String cotationMin;
//    @Transient
//    private String cotationMax;

    public Site() {
    }

    public Site(Integer siteid, String nomSite, String descriptionSite, String photo, Boolean tagOfficiel, Region region) {
        this.siteid = siteid;
        this.nomSite = nomSite;
        this.descriptionSite = descriptionSite;
        this.photo = photo;
        this.tagOfficiel = tagOfficiel;
        this.region = region;
    }

    public Integer getSiteid() {
        return siteid;
    }

    public void setSiteid(Integer siteid) {
        this.siteid = siteid;
    }

    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

    public String getDescriptionSite() {
        return descriptionSite;
    }

    public void setDescriptionSite(String descriptionSite) {
        this.descriptionSite = descriptionSite;
    }

    public Boolean getTagOfficiel() {
        return tagOfficiel;
    }

    public void setTagOfficiel(Boolean tagOfficiel) {
        this.tagOfficiel = tagOfficiel;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getPhoto() {        return photo;    }

    public void setPhoto(String photo) {        this.photo = photo;    }

}
