package com.emmanuel.escalade.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="utilisateur")
public class Utilisateur {

    @Id   //champ clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="utilisateurid")
    private Integer utilisateurId;
    @NotBlank(message = "Pseudo obligatoire")
    @NotNull
    @Size(min = 3, max = 10, message = "Le pseudo doit avoir 3 à 10 caractères")
    @Column(unique = true)
    private String pseudo;
    @NotNull
    @Column(nullable = false)
    private String motDePasse;
    @Column(nullable = false)
    private String nomUtilisateur;
    @Column(nullable = false)
    private String prenomUtilisateur;
    @Column(nullable = false)
    private String email;

    @Transient
    private String motDePasseConfirme;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "utilisateur_role", joinColumns = @JoinColumn(name = "utilisateurid"), inverseJoinColumns = @JoinColumn(name = "roleid"))
    private Collection<Role> roles;

    @OneToMany(mappedBy="utilisateur", cascade = CascadeType.ALL)   //utilisateur est l'attribut objet instancié dans Topo
    private List<Topo> topos;
    @OneToMany(mappedBy="utilisateur", cascade = CascadeType.ALL)
    private List<Commentaire> commentaires;

    public Utilisateur() {    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getMotDePasseConfirme() {
        return motDePasseConfirme;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public String getPrenomUtilisateur() {
        return prenomUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setMotDePasseConfirme(String motDePasseConfirme) {
        this.motDePasseConfirme = motDePasseConfirme;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public void setPrenomUtilisateur(String prenomUtilisateur) {
        this.prenomUtilisateur = prenomUtilisateur;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
