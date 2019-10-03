package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.MesUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MesUsers, Integer> {

}