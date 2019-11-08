package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.Voie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoieRepository extends JpaRepository<Voie, Integer> {
}
