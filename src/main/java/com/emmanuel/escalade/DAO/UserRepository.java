package com.emmanuel.escalade.DAO;

import com.emmanuel.escalade.model.MesUsers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<MesUsers, Integer> {

}