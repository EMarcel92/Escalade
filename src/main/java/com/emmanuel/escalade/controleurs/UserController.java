package com.emmanuel.escalade.controleurs;

import com.emmanuel.escalade.DAO.UserRepository;
import com.emmanuel.escalade.model.MesUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/list")
    public String maListe(Model model)  {
        model.addAttribute("users", userRepository.findAll());
        return "list_users";
    }

    @GetMapping("/signup")
    public String showSignUpForm(MesUsers mesUsers) {
        return "add-user";
    }

    @PostMapping("/adduser")
    public String addUser(@Valid @ModelAttribute("mesUsers") MesUsers mesUsers, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userRepository.save(mesUsers);
        model.addAttribute("users", userRepository.findAll());
        return "list_users";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        MesUsers mesUsers = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Identifiant de mesUsers invalide : " + id));
        model.addAttribute("mesusers", mesUsers);
        return "update-mesusers";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid MesUsers mesUsers, BindingResult result, Model model) {
        if (result.hasErrors()) {
            mesUsers.setId(id);
            return "update-mesusers";
        }
        userRepository.save(mesUsers);
        model.addAttribute("users", userRepository.findAll());
        return "list_users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        MesUsers mesUsers = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid mesUsers Id:" + id));
        userRepository.delete(mesUsers);
        model.addAttribute("users", userRepository.findAll());
        return "list_users";
    }
}