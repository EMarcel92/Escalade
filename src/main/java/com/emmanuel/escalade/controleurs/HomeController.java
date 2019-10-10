package com.emmanuel.escalade.controleurs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

//    todo : Logs avec Apache ?
//    import org.apache.log4j.Logger;
//    private static Logger log = Logger.getLogger(Main.class);

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Value("${messages.home:default-value}")
    private String message = "Contenu de la variable message tout court";

    @RequestMapping("/home")    // home = suffixe de l'URL
    // @ReturnBody renvoie directement la réponse (texte, variable,objet) et pas une page HTML
    public String welcome(Model model) {
        log.info("Ceci est un message pour la log");
        String message1 = "Message formatté en Java par le controleur";
        model.addAttribute("message2", message1);
        return "home";    //page HTML home
    }
}
