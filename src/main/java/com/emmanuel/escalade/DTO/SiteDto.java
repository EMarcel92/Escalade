package com.emmanuel.escalade.DTO;

import java.io.Serializable;

public class SiteDto implements Serializable {
 //   private Integer siteid;
    private String nomSite;
    private String descriptionSite;
    private String photo;
    private Boolean tagOfficiel;
    private String nomRegion;

    public SiteDto() {
    }

    public SiteDto(String nomSite, String descriptionSite, String photo, Boolean tagOfficiel, String nomRegion, String cotationMin) {
        this.nomSite = nomSite;
        this.descriptionSite = descriptionSite;
        this.photo = photo;
        this.tagOfficiel = tagOfficiel;
        this.nomRegion = nomRegion;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Boolean getTagOfficiel() {
        return tagOfficiel;
    }

    public void setTagOfficiel(Boolean tagOfficiel) {
        this.tagOfficiel = tagOfficiel;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

}
