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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Gère les requêtes correspondant à la gestion de l'utilisateur (création, login)
 */
@Controller
public class UtilisateurControleur {

    private static final Logger log = LoggerFactory.getLogger(UtilisateurControleur.class);
    private final UtilisateurService utilisateurService;
    private final SecurityService securityService;
    private final UtilisateurValidator utilisateurValidator;

    @Autowired
    public UtilisateurControleur(UtilisateurService utilisateurService, SecurityService securityService, UtilisateurValidator utilisateurValidator) {
        this.utilisateurService = utilisateurService;
        this.securityService = securityService;
        this.utilisateurValidator = utilisateurValidator;
    }

    /**
     * Affiche le formulaire de login
     * @param model
     * @param error
     * @param logout
     * @return Affiche le formulaire de login
     */
    @GetMapping("/login")
    public String AfficherFormulaireLogin(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "identifiant ou mot de passe invalide");
        if (logout != null)
            model.addAttribute("message", "Vous êtes déconnecté.");
        return "login";
    }

    /**
     * gère la déconnexion (suppression du cookie de session)
     * @param request
     * @param response
     * @return appelle la méthode d'affcihage de la page d'accueil
     */
    @GetMapping("/logout")
    public String seDeconnecter(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null)
            new SecurityContextLogoutHandler().logout(request, response, auth);
        return "redirect:/";
    }

    /**
     * Affiche la formulaire de création d'un nouvel utilisateur
     * @param utilisateur une instance d'un utilisateur
     * @param model
     * @return Affiche le formulaire de création utilisateur
     */
    @GetMapping("/nouvelutilisateur")
    public String AfficherFormulaireAjoutUtilisateur(Utilisateur utilisateur, Model model) {
        return "/ajouterutilisateur";
    }

    /**
     * Crée un utilisateur en base de données
     * @param utilisateur une instance d'utilisateur
     * @param result
     * @param model
     * @return si la connexion a échoué (erreur), réaffichage du formulaire de création utilisateur
     *          sinon appelle la méthode d'affichage de la page d'accueil avec utilisateur connecté
     */
    @PostMapping("/nouvelutilisateur")
    public String AjouterUtilisateur(@ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult result, Model model) {
        utilisateurValidator.validate(utilisateur, result);
        if (result.hasErrors()) {
            return "ajouterutilisateur";
        }
        utilisateurService.save(utilisateur);
        securityService.autoLogin(utilisateur.getPseudo(), utilisateur.getMotDePasseConfirme());
        return "redirect:/";
    }
}
