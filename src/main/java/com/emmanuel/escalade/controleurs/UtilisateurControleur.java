package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.Services.SecurityService;
import com.emmanuel.escalade.Services.UtilisateurService;
import com.emmanuel.escalade.Utilitaires.UtilisateurValidator;
import com.emmanuel.escalade.model.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class UtilisateurControleur {

    private final UtilisateurService utilisateurService;
    private final SecurityService securityService;
    private final UtilisateurValidator utilisateurValidator;

    private static final Logger log = LoggerFactory.getLogger(UtilisateurControleur.class);

    @Autowired
    public UtilisateurControleur(UtilisateurService utilisateurService, SecurityService securityService, UtilisateurValidator utilisateurValidator) {
        this.utilisateurService = utilisateurService;
        this.securityService = securityService;
        this.utilisateurValidator = utilisateurValidator;
    }

    @GetMapping("/login")
    public String AfficherFormulaireLogin(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "identifiant ou mot de passe invalide");

        if (logout != null)
            model.addAttribute("message", "Vous êtes déconnecté.");

        return "login";
    }

    @GetMapping("/logout")
    public String seDeconnecter(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/";
    }

    @GetMapping("/nouvelutilisateur")
    public String AfficherFormulaireAjoutUtilisateur(Utilisateur utilisateur, Model model) {
        return "/ajouterutilisateur";
    }

    @PostMapping("/nouvelutilisateur")
    public String AjouterUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult result, Model model) {
        utilisateurValidator.validate(utilisateur, result);
        if (result.hasErrors()){
            return "ajouterutilisateur";
        }
        utilisateurService.save(utilisateur);
        securityService.autoLogin(utilisateur.getPseudo(),utilisateur.getMotDePasseConfirme());
        return "redirect:/";
    }
}
