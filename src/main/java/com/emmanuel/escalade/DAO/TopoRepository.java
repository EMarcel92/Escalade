package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.Topo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopoRepository extends CrudRepository<Topo, Integer> {  //objet Topo avec une clé Integer
}
