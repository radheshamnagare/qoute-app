package com.radhesham.repo;

import com.radhesham.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao extends JpaRepository< CityEntity,Integer> {
    CityView  getCityEntityById(int id);
}
