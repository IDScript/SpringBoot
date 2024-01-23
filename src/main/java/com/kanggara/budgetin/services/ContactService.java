package com.kanggara.budgetin.services;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.entities.ContactEntity;
import com.kanggara.budgetin.models.ContactResponse;
import com.kanggara.budgetin.models.CreateContactRequest;
import com.kanggara.budgetin.repository.ContactRepository;

@Slf4j
@Service
public class ContactService {

  private final ValidationService validationService;

  private final ContactRepository contactRepository;

  ContactService(ValidationService validationService, ContactRepository contactRepository) {
    this.validationService = validationService;
    this.contactRepository = contactRepository;
  }

  @Transactional
  public ContactResponse create(UserEntity userEntity, CreateContactRequest contactRequest) {
    validationService.validate(contactRequest);

    ContactEntity contact = new ContactEntity();
    contact.setId(UUID.randomUUID().toString());
    log.info("First Name: {}", contactRequest.getFirstName());
    contact.setFirstName(contactRequest.getFirstName());
    contact.setLastName(contactRequest.getLastName());
    contact.setEmail(contactRequest.getEmail());
    contact.setPhone(contactRequest.getPhone());
    contactRepository.save(contact);

    return ContactResponse.builder()
        .id(contact.getId())
        .firstName(contact.getFirstName())
        .lastName(contact.getLastName())
        .email(contact.getEmail())
        .phone(contact.getPhone())
        .build();

  }

}
