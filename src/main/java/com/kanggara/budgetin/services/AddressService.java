package com.kanggara.budgetin.services;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.entities.AddressEntity;
import com.kanggara.budgetin.entities.ContactEntity;
import com.kanggara.budgetin.models.AddressResponse;
import com.kanggara.budgetin.models.CreateAddressRequest;
import com.kanggara.budgetin.models.UpdateAddressRequest;
import com.kanggara.budgetin.repository.AddressRepository;
import com.kanggara.budgetin.repository.ContactRepository;

@Service
public class AddressService {

  private ContactRepository contactRepository;

  private AddressRepository addressRepository;

  private ValidationService validationService;

  public AddressService(ContactRepository contactRepository, AddressRepository addressRepository,
      ValidationService validationService) {
    this.contactRepository = contactRepository;
    this.addressRepository = addressRepository;
    this.validationService = validationService;
  }

  @Transactional
  public AddressResponse create(UserEntity userEntity, CreateAddressRequest request) {
    validationService.validate(request);

    ContactEntity contactEntity = contactRepository.findFirstByUserAndId(userEntity, request.getContactId())
        .orElseThrow(() -> notFound("Contact Not Found"));

    AddressEntity addressEntity = new AddressEntity();
    addressEntity.setId(UUID.randomUUID().toString());
    addressEntity.setContact(contactEntity);
    addressEntity.setStreet(request.getStreet());
    addressEntity.setCity(request.getCity());
    addressEntity.setProvince(request.getProvince());
    addressEntity.setCountry(request.getCountry());
    addressEntity.setPostalCode(request.getPostalCode());
    addressRepository.save(addressEntity);

    return toAddressResponse(addressEntity);
  }

  @Transactional(readOnly = true)
  public AddressResponse get(UserEntity userEntity, String contactId, String addressId) {
    ContactEntity contactEntity = contactRepository.findFirstByUserAndId(userEntity, contactId)
        .orElseThrow(() -> notFound("Contact Not Found"));

    AddressEntity addressEntity = addressRepository.findFirstByContactAndId(contactEntity, addressId)
        .orElseThrow(() -> notFound("Address Not Found"));

    return toAddressResponse(addressEntity);
  }

  @Transactional
  public AddressResponse update(UserEntity userEntity, UpdateAddressRequest request) {
    validationService.validate(request);

    ContactEntity contactEntity = contactRepository.findFirstByUserAndId(userEntity, request.getContactId())
        .orElseThrow(() -> notFound("Contact Not Found"));

    AddressEntity addressEntity = addressRepository.findFirstByContactAndId(contactEntity, request.getAddressId())
        .orElseThrow(() -> notFound("Address Not Found"));

    addressEntity.setCity(request.getCity());
    addressEntity.setStreet(request.getStreet());
    addressEntity.setCountry(request.getCountry());
    addressEntity.setProvince(request.getProvince());
    addressEntity.setPostalCode(request.getPostalCode());
    addressRepository.save(addressEntity);

    return toAddressResponse(addressEntity);
  }

  @Transactional
  public void delete(UserEntity userEntity, String contactId, String addressId) {
    ContactEntity contactEntity = contactRepository.findFirstByUserAndId(userEntity, contactId)
        .orElseThrow(() -> notFound("Contact Not Found"));

    AddressEntity addressEntity = addressRepository.findFirstByContactAndId(contactEntity, addressId)
        .orElseThrow(() -> notFound("Address Not Found"));

    addressRepository.delete(addressEntity);
  }

  @Transactional(readOnly = true)
  public List<AddressResponse> list(UserEntity userEntity, String contactId) {
    ContactEntity contactEntity = contactRepository.findFirstByUserAndId(userEntity, contactId)
        .orElseThrow(() -> notFound("Contact Not Found"));

    List<AddressEntity> addresses = addressRepository.findAllByContact(contactEntity);

    return addresses.stream().map(this::toAddressResponse).toList();

  }

  private ResponseStatusException notFound(String msg) {
    return new ResponseStatusException(HttpStatus.NOT_FOUND, msg);
  }

  private AddressResponse toAddressResponse(AddressEntity addressEntity) {

    return AddressResponse.builder()
        .id(addressEntity.getId())
        .street(addressEntity.getStreet())
        .city(addressEntity.getCity())
        .province(addressEntity.getProvince())
        .country(addressEntity.getCountry())
        .postalCode(addressEntity.getPostalCode())
        .build();
  }

}
