package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.model.Topo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestion de la page d'accueil
 */
@Controller
public class IndexControleur {

    private static final Logger log = LoggerFactory.getLogger(IndexControleur.class);

    /**
     * Affiche la page d'accueil
     * @param model
     * @return la page d'accueil HTML index
     */
    @GetMapping("/")
    // @ReturnBody renvoie directement la réponse (texte, variable, objet) et pas une page HTML
    public String accueil (Model model) {
       // log.info("Ceci est un message pour la log");
       // model.addAttribute("message2", "rien");
        return "index";    //page HTML index.html
    }

}



