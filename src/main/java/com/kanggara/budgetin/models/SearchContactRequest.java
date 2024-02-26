package com.kanggara.budgetin.models;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchContactRequest {

  private String name;

  private String email;

  private String phone;

  @NotNull
  private int page;

  @NotNull
  private int size;

}
