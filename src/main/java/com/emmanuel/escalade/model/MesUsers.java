package com.emmanuel.escalade.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity  //marque la class comme une entité (javabean)
@Table(name="mesusers")  //crée le lien avec la table physique
public class MesUsers {

    @Id   //champ clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //génère une nouvelle valeur à chaque entité commitée
    //@Column(name="topoid")  // indique le nom de la colonne correspondante dans la table
    private Integer id;
    @NotBlank(message = "Nom obligatoire")
    private String name;
    @NotBlank(message = "Email obligatoire")
    private String email;

    //standard constructors/setters/getters/toString

    public MesUsers() {
    }

    public MesUsers(@NotBlank(message = "Nom obligatoire") String name, @NotBlank(message = "Email obligatoire") String email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
