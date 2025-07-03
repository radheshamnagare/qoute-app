package com.radhesham.repo;

import com.radhesham.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<UserEntity,Integer> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Optional<UserEntity> findByEmailAndPassword(String uname, String password);

}
