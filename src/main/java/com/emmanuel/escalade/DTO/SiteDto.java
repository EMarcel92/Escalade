package com.emmanuel.escalade.DTO;

import com.emmanuel.escalade.model.Region;
import com.emmanuel.escalade.model.Secteur;
import javax.persistence.*;
import java.util.List;

public class SiteDto {
 //   private Integer siteid;
    private String nomSite;
    private String descriptionSite;
    private String photo;
    private Boolean tagOfficiel;
    private String NomRegion;
    private String cotationMin;
//    private String cotationMax;

    public SiteDto() {
    }

    public SiteDto(String nomSite, String descriptionSite, String photo, Boolean tagOfficiel, String nomRegion, String cotationMin) {
        this.nomSite = nomSite;
        this.descriptionSite = descriptionSite;
        this.photo = photo;
        this.tagOfficiel = tagOfficiel;
        NomRegion = nomRegion;
        this.cotationMin = cotationMin;
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
        return NomRegion;
    }

    public void setNomRegion(String nomRegion) {
        NomRegion = nomRegion;
    }

    public String getCotationMin() {
        return cotationMin;
    }

    public void setCotationMin(String cotationMin) {
        this.cotationMin = cotationMin;
    }
}
