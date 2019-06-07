package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.model.Topo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TopoControleur {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private static List<Topo> topos = new ArrayList<Topo>();



    static {
        topos.add(new Topo("Site difficile", "Bla bla bla", 3 , 8 ));
        topos.add(new Topo("Site facile", "Bli  bli blibli blibli bli", 4 , 6 ));
    }

    @RequestMapping(value = {"/listetopos" }, method = RequestMethod.GET)
    public String listeTopos(Model model) {
        log.info("TopoControleur");
//        Topo topo = new Topo();
        model.addAttribute("topos", topos);

        return "listetopos";
    }

    @RequestMapping(value = {"/topo" }, method = RequestMethod.GET)
    public String listeSites(Model model) {

        return "topo";
    }


}
