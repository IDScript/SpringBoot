package com.kanggara.budgetin.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.kanggara.budgetin.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
  Optional<UserEntity> findByToken(String token);
}
