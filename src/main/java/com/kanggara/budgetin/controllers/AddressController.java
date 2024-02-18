package com.kanggara.budgetin.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.AddressResponse;
import com.kanggara.budgetin.models.CreateAddressRequest;
import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.services.AddressService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class AddressController {

  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping(path = "/api/contacts/{contactId}/addresses")
  public WebResponse<AddressResponse> create(UserEntity userEntity,
      @RequestBody CreateAddressRequest request,
      @PathVariable("contactId") String contactId) {

    request.setContactId(contactId);

    AddressResponse addressResponse = addressService.create(userEntity, request);

    return WebResponse.<AddressResponse>builder().data(addressResponse).build();

  }

}
