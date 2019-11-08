package com.emmanuel.escalade.Utilitaires;

import com.emmanuel.escalade.Services.UtilisateurService;
import com.emmanuel.escalade.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UtilisateurValidator implements Validator {
    @Autowired
    private UtilisateurService utilisateurService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Utilisateur.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors){
        Utilisateur utilisateur = (Utilisateur)o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"pseudo","NotEmpty");
        if (utilisateurService.findByPseudo(utilisateur.getPseudo()) != null) {
            errors.rejectValue("pseudo", "Duplicate.utilisateurForm.pseudo");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motDePasse", "NotEmpty");
        if (utilisateur.getMotDePasse().length() < 3 || utilisateur.getMotDePasse().length() > 10) {
            errors.rejectValue("motDePasse", "Size.Utilisateur.password");
        }

        if (!utilisateur.getMotDePasseConfirme().equals(utilisateur.getMotDePasse())) {
            errors.rejectValue("motDePasseConfirme", "Diff.UtilisateurForm.passwordConfirm");
        }
    }
}
