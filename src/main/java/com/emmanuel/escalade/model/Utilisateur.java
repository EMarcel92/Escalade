package com.emmanuel.escalade.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name="utilisateur")
public class Utilisateur {

    @Id   //champ clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="utilisateurid")
    private Integer id;
    @NotBlank(message = "Pseudo obligatoire")
    @Size(min = 3, max = 50, message = "Client Name should be greater then 3 Characters Or Less Then 50 Characters")
    @Column(unique = true)
    private String pseudo;
    private String motDePasse;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String profil;
    @OneToMany(mappedBy="utilisateur", cascade = CascadeType.ALL)   //utilisateur est l'attribut objet instancié dans Topo
    private List<Topo> topos;

    //standard constructors/setters/getters/toString


    public Utilisateur() {    }

    public Integer getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public String getProfil() {   return profil; }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public List<Topo> getTopos() {
        return topos;
    }

    public void setTopos(List<Topo> topos) {
        this.topos = topos;
    }

    public void addTopo (Topo t) {
        t.setUtilisateur(this);
        topos.add(t) ;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", pseudo='" + pseudo + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                ", nomUtilisateur='" + nomUtilisateur + '\'' +
                ", prenomUtilisateur='" + prenomUtilisateur + '\'' +
                ", profil='" + profil + '\'' +
                ", topos=" + topos +
                '}';
    }
}
