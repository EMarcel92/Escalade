package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {
    List<Topo> findByUtilisateurPseudo(String pseudo);
}
