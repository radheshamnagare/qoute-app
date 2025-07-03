package com.radhesham.repo;

import com.radhesham.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryDao extends JpaRepository< CountryEntity,Integer> {

    CountryView getCountryEntityById(int id);
}
