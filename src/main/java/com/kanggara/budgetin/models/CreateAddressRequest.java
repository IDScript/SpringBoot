package com.kanggara.budgetin.models;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAddressRequest {

  @JsonIgnore
  @NotBlank
  private String contactId;

  @Size(max = 200)
  private String street;

  @Size(max = 100)
  private String city;

  @Size(max = 100)
  private String province;

  @NotBlank
  @Size(max = 100)
  private String country;

  @Size(max = 10)
  private String postalCode;

}
