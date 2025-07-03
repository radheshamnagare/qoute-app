package com.radhesham.repo;

import com.radhesham.entity.StateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateDao extends JpaRepository< StateEntity,Integer> {

    StateView getStateEntityById(int id);
}
