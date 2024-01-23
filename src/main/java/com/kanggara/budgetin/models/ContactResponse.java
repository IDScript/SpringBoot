package com.kanggara.budgetin.models;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactResponse {

  private String id;

  private String firstName;

  private String lastName;

  private String email;

  private String phone;

}
