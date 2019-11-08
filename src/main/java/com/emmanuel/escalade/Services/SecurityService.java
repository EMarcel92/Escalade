package com.emmanuel.escalade.Services;

public interface SecurityService {
    String findLoggedInPseudo();

    void autoLogin(String pseudo, String motDePasse);
}
