package com.emmanuel.escalade.model;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "commentaire")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="commentaireid")
    private int commentaireId;
   // @NotNull
 //   @NotBlank
    @Size(min =1 , max = 250)
    private String texteCommentaire;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateRedaction;
    @ManyToOne
    @JoinColumn(name="siteid", nullable=false)
    private Site site;
    @ManyToOne
    @JoinColumn(name="utilisateurid", nullable=false)
    private Utilisateur utilisateur;

    public Commentaire() {
    }

    public int getCommentaireId() {
        return commentaireId;
    }

    public void setCommentaireId(int commentaireId) {
        this.commentaireId = commentaireId;
    }

    public String getTexteCommentaire() {
        return texteCommentaire;
    }

    public void setTexteCommentaire(String texteCommentaire) {
        this.texteCommentaire = texteCommentaire;
    }

    public LocalDateTime getDateRedaction() {
        return dateRedaction;
    }

    public void setDateRedaction(LocalDateTime dateRedaction) {
        this.dateRedaction = dateRedaction;
    }

    public Site getSite() {
        return site;
    }

    public void setSite(Site site) {
        this.site = site;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
