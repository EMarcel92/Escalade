package com.emmanuel.escalade.controleurs;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginControleur {


    @RequestMapping(value = {"/login" }, method = RequestMethod.GET)
    public String listeSites(Model model) {

        return "login";
    }



}
