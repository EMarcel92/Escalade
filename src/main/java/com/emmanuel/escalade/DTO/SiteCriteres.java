package com.emmanuel.escalade.DTO;

public class SiteCriteres {

    private String nomSite;
    private String cotationMin;
    private String cotationMax;
    private Integer regionId;
    private Integer nbSecteurs;

    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
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

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getNbSecteurs() {
        return nbSecteurs;
    }

    public void setNbSecteurs(Integer nbSecteurs) {
        this.nbSecteurs = nbSecteurs;
    }

    @Override
    public String toString() {
        return "SiteCriteres{" +
                "nomSite='" + nomSite + '\'' +
                ", cotationMin='" + cotationMin + '\'' +
                ", cotationMax='" + cotationMax + '\'' +
                ", regionId=" + regionId +
                ", nbSecteurs=" + nbSecteurs +
                '}';
    }
}
