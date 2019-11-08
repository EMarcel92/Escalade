package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByPseudo(String pseudo);
    
}
