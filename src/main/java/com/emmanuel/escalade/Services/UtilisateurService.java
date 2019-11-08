package com.emmanuel.escalade.Services;

import com.emmanuel.escalade.model.Utilisateur;

public interface UtilisateurService {
    void save(Utilisateur utilisateur);

    Utilisateur findByPseudo(String pseudo);
}
