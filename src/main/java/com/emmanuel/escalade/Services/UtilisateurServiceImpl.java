package com.emmanuel.escalade.Services;

import com.emmanuel.escalade.DAO.RoleRepository;
import com.emmanuel.escalade.DAO.UtilisateurRepository;
import com.emmanuel.escalade.model.Role;
import com.emmanuel.escalade.model.Utilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * Gestion d'un utilisateur par implémentation de Spring Security
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService{

    private UtilisateurRepository utilisateurRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    private static final Logger log = LoggerFactory.getLogger(UtilisateurServiceImpl.class);

    /**
     * Crée un utilisteur en base avec un rôle "user" (utilisateur standard)
     * @param utilisateur Entité Utilisateur
     */
    @Override
    public void save(Utilisateur utilisateur) {
        utilisateur.setMotDePasse(bCryptPasswordEncoder.encode(utilisateur.getMotDePasse()));
        Optional<Role> monRole = roleRepository.findById(2);
        utilisateur.setRoles(Arrays.asList(monRole.get()));
        utilisateurRepository.save(utilisateur);
    }

    /**
     * Recherche d'un utilisateur par son pseudo
     * @param pseudo pseudo de l'uitilisateur
     * @return un utilisteur
     */
    @Override
    public Utilisateur findByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo);
    }
}
