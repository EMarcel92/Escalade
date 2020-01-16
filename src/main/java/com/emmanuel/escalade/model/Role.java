package com.emmanuel.escalade.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * Profil auquel est rattaché un utilisateur, pouvant lui octroyer des droits.
 * Un utilisteur peut avoir plusieurs rôles.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="roleid")
    private Integer roleid;
    @Column(name="nom_role")
    @NotNull
    @Size(max=5)
    private String nomRole;
    @ManyToMany(mappedBy = "roles")
    private Collection<Utilisateur> utilisateurs;


    public Role() {
    }

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getNomRole() {
        return nomRole;
    }

    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }

    public Collection<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Collection<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }
}
