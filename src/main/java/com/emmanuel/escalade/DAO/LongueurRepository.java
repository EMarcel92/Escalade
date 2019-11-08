package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.Longueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LongueurRepository extends JpaRepository<Longueur, Integer>{
}
