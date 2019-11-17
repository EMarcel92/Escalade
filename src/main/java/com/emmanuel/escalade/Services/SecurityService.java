package com.emmanuel.escalade.Services;

/** Fournit l'utilisateur loggué et réalise l'auto-login d'un utilisateur après son enregistrement */
public interface SecurityService {
    String findLoggedInPseudo();

    void autoLogin(String pseudo, String motDePasse);
}
