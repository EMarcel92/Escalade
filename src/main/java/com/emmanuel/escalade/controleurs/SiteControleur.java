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
public class SiteControleur {

    @RequestMapping(value = {"/site" }, method = RequestMethod.GET)
    public String unSite(Model model) {

        return "site";
    }

    @RequestMapping(value = {"/listesites" }, method = RequestMethod.GET)
    public String listeSites(Model model) {

        return "listesites";
    }


}
