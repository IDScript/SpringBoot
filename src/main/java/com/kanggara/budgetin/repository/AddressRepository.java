package com.kanggara.budgetin.repository;

import com.kanggara.budgetin.entities.AddressEntity;
import com.kanggara.budgetin.entities.ContactEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, String> {

  Optional<AddressEntity> findFirstByContactAndId(ContactEntity contactEntity, String id);

}
