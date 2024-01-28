package com.kanggara.budgetin.repository;

import org.springframework.stereotype.Repository;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.entities.ContactEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@Repository
public interface ContactRepository
    extends JpaRepository<ContactEntity, String>, JpaSpecificationExecutor<ContactEntity> {

  Optional<ContactEntity> findFirstByUserAndId(UserEntity userEntity, String id);
}
