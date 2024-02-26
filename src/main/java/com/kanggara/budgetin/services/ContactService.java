package com.kanggara.budgetin.services;

import java.util.List;
import java.util.UUID;
import java.util.Objects;
import java.util.ArrayList;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.entities.ContactEntity;
import com.kanggara.budgetin.models.ContactResponse;
import com.kanggara.budgetin.models.CreateContactRequest;
import com.kanggara.budgetin.models.SearchContactRequest;
import com.kanggara.budgetin.models.UpdateContactRequest;
import com.kanggara.budgetin.repository.ContactRepository;

import jakarta.persistence.criteria.Predicate;

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

    return toContactResponse(contact);
  }

  private ContactResponse toContactResponse(ContactEntity contact) {
    return ContactResponse.builder()
        .id(contact.getId())
        .firstName(contact.getFirstName())
        .lastName(contact.getLastName())
        .email(contact.getEmail())
        .phone(contact.getPhone())
        .build();
  }

  @Transactional(readOnly = true)
  public ContactResponse get(UserEntity userEntity, String id) {

    ContactEntity contact = contactRepository.findFirstByUserAndId(userEntity, id)
        .orElseThrow(this::contactNotFound);

    return toContactResponse(contact);

  }

  @Transactional
  public ContactResponse update(UserEntity userEntity, UpdateContactRequest request) {
    validationService.validate(request);

    ContactEntity contact = contactRepository.findFirstByUserAndId(userEntity, request.getId())
        .orElseThrow(this::contactNotFound);

    contact.setFirstName(request.getFirstName());
    contact.setLastName(request.getLastName());
    contact.setPhone(request.getPhone());
    contact.setEmail(request.getEmail());
    contactRepository.save(contact);

    return toContactResponse(contact);
  }

  @Transactional
  public void delete(UserEntity userEntity, String contactId) {
    ContactEntity contact = contactRepository.findFirstByUserAndId(userEntity, contactId)
        .orElseThrow(this::contactNotFound);

    contactRepository.delete(contact);
  }

  private ResponseStatusException contactNotFound() {
    return new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact Not Found");
  }

  @Transactional(readOnly = true)
  public Page<ContactResponse> search(UserEntity userEntity, SearchContactRequest request) {

    Specification<ContactEntity> specification = (root, query, builder) -> {
      List<Predicate> predicates = new ArrayList<>();

      predicates.add(builder.equal(root.get("user"), userEntity));

      if (Objects.nonNull(request.getName())) {
        predicates.add(builder.or(
            builder.like(root.get("firstName"), "%" + request.getName() + "%"),
            builder.like(root.get("lastName"), "%" + request.getName() + "%")));
      }

      if (Objects.nonNull(request.getEmail())) {
        predicates.add(builder.like(root.get("email"), "%" + request.getEmail() + "%"));
      }

      if (Objects.nonNull(request.getPhone())) {
        predicates.add(builder.like(root.get("phone"), "%" + request.getPhone() + "%"));
      }

      return query.where(predicates.toArray(new Predicate[] {})).getRestriction();
    };

    Pageable pageable = PageRequest.of(request.getPage(), request.getSize());

    Page<ContactEntity> contacts = contactRepository.findAll(specification, pageable);

    List<ContactResponse> contactResponses = contacts.getContent().stream()
        .map(this::toContactResponse).toList();

    return new PageImpl<>(contactResponses, pageable, contacts.getTotalElements());
  }
}
