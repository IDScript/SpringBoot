package com.kanggara.budgetin.repository;

import org.springframework.stereotype.Repository;
import com.kanggara.budgetin.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, String> {
}
