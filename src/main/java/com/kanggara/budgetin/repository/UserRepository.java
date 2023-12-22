package com.kanggara.budgetin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kanggara.budgetin.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
