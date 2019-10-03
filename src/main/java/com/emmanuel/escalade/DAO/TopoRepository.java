package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.Topo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoRepository extends JpaRepository<Topo, Integer> {  //objet Topo avec une clé Integer
}
