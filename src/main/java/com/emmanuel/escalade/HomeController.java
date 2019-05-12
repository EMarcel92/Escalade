package com.emmanuel.escalade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Value("${messages.home:default-value}")
    private String message = "Contenu de la variable message tout court";

    @RequestMapping("/toto")    // toto = suffixe de l'URL
    // @ReturnBody renvoie directement la réponse (texte, variable,objet) et pas une page HTML
    public String welcome(Model model) {
        log.info("Ceci est un message pour la log");
        String message1 = "Contenu de la variable message1";
        model.addAttribute("message2", message1);
        return "home";    //page HTML home
    }
}
