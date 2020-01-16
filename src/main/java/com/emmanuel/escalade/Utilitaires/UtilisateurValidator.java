package com.emmanuel.escalade.Utilitaires;

import com.emmanuel.escalade.Services.UtilisateurService;
import com.emmanuel.escalade.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Validation des saisies lors de la déclaration/modification d'un utilisateur
 */
@Component
public class UtilisateurValidator implements Validator {

    private final UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurValidator(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Utilisateur.class.equals(aClass);
    }

    /**
     * Contrôle les données saisies pour gérer un utilisateur (taille des noms, mot de passe)
     * @param o un objet utilisateur
     * @param errors
     */
    @Override
    public void validate(Object o, Errors errors){
        Utilisateur utilisateur = (Utilisateur)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pseudo","NotEmpty");
        if (utilisateur.getPseudo().length() < 3 || utilisateur.getPseudo().length() > 10) {
            errors.rejectValue("pseudo", "Size.utilisateurForm.pseudo");
        }
        if (utilisateurService.findByPseudo(utilisateur.getPseudo()) != null) {
            errors.rejectValue("pseudo", "Duplicate.utilisateurForm.pseudo");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motDePasse", "NotEmpty");
        if (utilisateur.getMotDePasse().length() < 3 || utilisateur.getMotDePasse().length() > 10) {
            errors.rejectValue("motDePasse", "Size.utilisateurForm.password");
        }

        if (!utilisateur.getMotDePasseConfirme().equals(utilisateur.getMotDePasse())) {
            errors.rejectValue("motDePasseConfirme", "Diff.utilisateurForm.passwordConfirm");
        }

        if (utilisateur.getNomUtilisateur().length() < 2 || utilisateur.getNomUtilisateur().length() > 50) {
            errors.rejectValue("nomUtilisateur", "Size.utilisateurForm.nomUtilisateur");
        }

        if (utilisateur.getPrenomUtilisateur().length() < 2 || utilisateur.getPrenomUtilisateur().length() > 50) {
            errors.rejectValue("prenomUtilisateur", "Size.utilisateurForm.nomUtilisateur");
        }
    }
}
