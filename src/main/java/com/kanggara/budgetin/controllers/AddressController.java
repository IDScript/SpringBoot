package com.kanggara.budgetin.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.AddressResponse;
import com.kanggara.budgetin.services.AddressService;
import com.kanggara.budgetin.models.CreateAddressRequest;
import com.kanggara.budgetin.models.UpdateAddressRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class AddressController {

  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping(path = "/api/contacts/{contactId}/addresses", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<AddressResponse> create(UserEntity userEntity,
      @RequestBody CreateAddressRequest request,
      @PathVariable("contactId") String contactId) {

    request.setContactId(contactId);

    AddressResponse addressResponse = addressService.create(userEntity, request);

    return WebResponse.<AddressResponse>builder().data(addressResponse).build();

  }

  @GetMapping(path = "/api/contacts/{contactId}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<AddressResponse> getMethodName(UserEntity userEntity,
      @PathVariable("contactId") String contactId,
      @PathVariable("addressId") String addressId) {

    AddressResponse addressResponse = addressService.get(userEntity, contactId, addressId);

    return WebResponse.<AddressResponse>builder().data(addressResponse).build();
  }

  @PutMapping(path = "/api/contacts/{contactId}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<AddressResponse> update(UserEntity userEntity,
      @RequestBody UpdateAddressRequest request,
      @PathVariable("contactId") String contactId,
      @PathVariable("addressId") String addressId) {

    request.setContactId(contactId);
    request.setAddressId(addressId);

    AddressResponse addressResponse = addressService.update(userEntity, request);

    return WebResponse.<AddressResponse>builder().data(addressResponse).build();

  }

  @DeleteMapping(path = "/api/contacts/{contactId}/addresses/{addressId}", produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<String> delete(UserEntity userEntity,
      @PathVariable("contactId") String contactId,
      @PathVariable("addressId") String addressId) {

    addressService.delete(userEntity, contactId, addressId);
    return WebResponse.<String>builder().data("Deleted").build();
  }

}
