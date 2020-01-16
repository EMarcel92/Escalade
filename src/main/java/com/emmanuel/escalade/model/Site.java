package com.emmanuel.escalade.model;

import javax.persistence.*;
import java.util.List;

/**
 * Site d'escalade, regroupant des secteurs
 */
@Entity
@Table(name = "SITE")
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
    private String cotationMin;
    private String cotationMax;
    @ManyToOne
    @JoinColumn(name="regionid", nullable=false)
    private Region region;
    @OneToMany (mappedBy = "site", cascade = CascadeType.ALL)
    private List<Secteur> secteurs;
    @OneToMany (mappedBy = "site", cascade = CascadeType.ALL)
    @OrderBy("dateRedaction desc")
    private List<Commentaire> commentaires;

    public Site() {
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

    public String getCotationMin() {
        return cotationMin;
    }

    public void setCotationMin(String cotationMin) {
        this.cotationMin = cotationMin;
    }

    public String getCotationMax() {
        return cotationMax;
    }

    public void setCotationMax(String cotationMax) {
        this.cotationMax = cotationMax;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getPhoto() {        return photo;    }

    public void setPhoto(String photo) {        this.photo = photo;    }

    public List<Secteur> getSecteurs() {
        return secteurs;
    }

    public void setSecteurs(List<Secteur> secteurs) {
        this.secteurs = secteurs;
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    @Override
    public String toString() {
        return "Site{" +
                "siteid=" + siteid +
                ", nomSite='" + nomSite + '\'' +
                ", descriptionSite='" + descriptionSite + '\'' +
                ", photo='" + photo + '\'' +
                ", tagOfficiel=" + tagOfficiel +
                ", region=" + region +
                ", secteurs=" + secteurs +
                '}';
    }
}
