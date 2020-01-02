package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.Topo;
import com.emmanuel.escalade.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {
    List<Topo> findByUtilisateurPseudoOrderByNomTopo(String pseudo);
    List<Topo> findByUtilisateurPseudoNotOrderByNomTopo(String pseudo);
 //   List<Topo> findAllOrderByNomTopo();
//    List<Topo> findByemprunteurNot(Utilisateur emprunteur);

}
