package com.kanggara.budgetin.services;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;

import com.kanggara.budgetin.security.BCrypt;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.TokenResponse;
import com.kanggara.budgetin.models.LoginUserRequest;
import com.kanggara.budgetin.repository.UserRepository;

@Service
public class AuthService {
  private UserRepository userRepository;
  private ValidationService validationService;

  public AuthService(UserRepository userRepository, ValidationService validationService) {
    this.userRepository = userRepository;
    this.validationService = validationService;
  }

  @Transactional
  public TokenResponse login(LoginUserRequest request) {
    String username = request.getUsername();
    validationService.validate(request);

    if (username != null) {

      UserEntity userEntity = userRepository.findById(username).orElseThrow(this::unauthorized);
      if (BCrypt.checkpw(request.getPassword(), userEntity.getPassword())) {
        userEntity.setToken(UUID.randomUUID().toString());
        userEntity.setTokenExpiriedAt(next30Day());
        userRepository.save(userEntity);

        return TokenResponse
            .builder()
            .token(userEntity.getToken())
            .expiredAt(userEntity.getTokenExpiriedAt())
            .build();
      } else {
        throw unauthorized();
      }
    } else {
      throw unauthorized();
    }
  }

  private ResponseStatusException unauthorized() {
    return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong");
  }

  /**
   * @return Long milis to next 30 days (1000ms * 60s * 24h * 30d)
   */
  private Long next30Day() {
    return System.currentTimeMillis() + (1000 * 60 * 24 * 30);
  }

}
