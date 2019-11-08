package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.Services.SecurityService;
import com.emmanuel.escalade.Services.UtilisateurService;
import com.emmanuel.escalade.Services.UtilisateurServiceImpl;
import com.emmanuel.escalade.Utilitaires.UtilisateurValidator;
import com.emmanuel.escalade.model.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UtilisateurControleur {
    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UtilisateurValidator utilisateurValidator;

  //  private final UtilisateurServiceImpl utilisateurService;

    private static final Logger log = LoggerFactory.getLogger(UtilisateurControleur.class);

//    @Autowired
//    public UtilisateurControleur(UtilisateurServiceImpl utilisateurService) {
//        this.utilisateurService = utilisateurService;
//    }

    @GetMapping("/login")
    public String AfficherFormulaireLogin(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "identifiant ou mot de passe invalide");

        if (logout != null)
            model.addAttribute("message", "Vous êtes connecté.");

        return "login";
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
        return "redirect:/index";
    }
}
