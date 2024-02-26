package com.kanggara.budgetin.models;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserRequest {

  @NotBlank
  @Size(max = 100)
  private String username;

  @NotBlank
  @Size(max = 100)
  private String password;
}
