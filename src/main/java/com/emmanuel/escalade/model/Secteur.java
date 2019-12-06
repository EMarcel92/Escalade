package com.emmanuel.escalade.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "secteur")
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "secteurid")
    private Integer secteurid;
    @NotBlank(message = "Nom de secteur obligatoire")
    @Size(min = 3, max = 10)
    private String nomSecteur;
    @ManyToOne
    @JoinColumn(name="siteid", nullable=false)
    private Site site;
    @OneToMany (mappedBy = "secteur", cascade = CascadeType.ALL)
    private List<Voie> voies;

    public Secteur() {
    }

    public Secteur(String nomSecteur, Site site, List<Voie> voies) {
        this.nomSecteur = nomSecteur;
        this.site = site;
        this.voies = voies;
    }

    public Integer getSecteurid() {
        return secteurid;
    }

    public void setSecteurid(Integer secteurid) {
        this.secteurid = secteurid;
    }

    public String getNomSecteur() {
        return nomSecteur;
    }

    public void setNomSecteur(String nomSecteur) {
        this.nomSecteur = nomSecteur;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public List<Voie> getVoies() {
        return voies;
    }

    public void setVoies(List<Voie> voies) {
        this.voies = voies;
    }
}
