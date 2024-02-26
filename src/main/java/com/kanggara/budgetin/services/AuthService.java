package com.kanggara.budgetin.services;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.extern.slf4j.Slf4j;
import jakarta.transaction.Transactional;

import com.kanggara.budgetin.security.BCrypt;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.TokenResponse;
import com.kanggara.budgetin.models.LoginUserRequest;
import com.kanggara.budgetin.repository.UserRepository;

@Slf4j
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
    String password = request.getPassword();

    log.info("User : {}", username);
    log.info("Pass : {}", password);

    if (username != null && password != null) {
      validationService.validate(request);
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
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
    }
  }

  private ResponseStatusException unauthorized() {
    return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong");
  }

  /**
   * @return Long Epoch to next 30 days (60s * 60m * 24h * 30d)
   */
  private Long next30Day() {
    Long next30Day = (System.currentTimeMillis() / 1000) + (60 * 60 * 24 * 30);
    log.info("Epoch : {}", next30Day);
    return next30Day;
  }

  @Transactional
  public void logout(UserEntity userEntity) {
    userEntity.setToken(null);
    userEntity.setTokenExpiriedAt(null);

    userRepository.save(userEntity);
  }
}
