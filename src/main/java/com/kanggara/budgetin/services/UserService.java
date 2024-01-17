package com.kanggara.budgetin.services;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kanggara.budgetin.security.BCrypt;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.models.UserResponse;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.models.RegisterUserRequest;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final ValidationService validationService;

  UserService(ValidationService validationService, UserRepository userRepository) {
    this.validationService = validationService;
    this.userRepository = userRepository;
  }

  @Transactional
  public void register(RegisterUserRequest registerUserRequest) {
    validationService.validate(registerUserRequest);
    String username = registerUserRequest.getUsername();

    if (username != null && (userRepository.existsById(username))) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
    }

    UserEntity user = new UserEntity();
    user.setUsername(registerUserRequest.getUsername());
    user.setPassword(BCrypt.hashpw(registerUserRequest.getPassword(), BCrypt.gensalt()));
    user.setName(registerUserRequest.getName());

    userRepository.save(user);
  }

  public UserResponse get(UserEntity userEntity){
    return UserResponse.builder().username(userEntity.getUsername()).name(userEntity.getName()).build();
  }
}
