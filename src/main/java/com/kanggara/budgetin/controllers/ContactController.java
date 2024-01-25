package com.kanggara.budgetin.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kanggara.budgetin.models.WebResponse;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.ContactResponse;
import com.kanggara.budgetin.services.ContactService;
import com.kanggara.budgetin.models.CreateContactRequest;

@RestController
public class ContactController {

  private final ContactService contactService;

  ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @PostMapping(path = "/api/contact", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<ContactResponse> create(
      UserEntity userEntity,
      @RequestBody CreateContactRequest createContactRequest) {
    ContactResponse contactResponse = contactService.create(userEntity, createContactRequest);

    return WebResponse.<ContactResponse>builder().data(contactResponse).build();
  }

  @GetMapping(path = "/api/contact/{contactId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public WebResponse<ContactResponse> get(UserEntity userEntity, @PathVariable("contactId") String contactId) {

    ContactResponse contactResponse = contactService.get(userEntity, contactId);

    return WebResponse.<ContactResponse>builder().data(contactResponse).build();
  }

}
