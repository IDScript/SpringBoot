package com.kanggara.budgetin.services;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.kanggara.budgetin.entities.User;
import com.kanggara.budgetin.models.RegisterUserRequest;
import com.kanggara.budgetin.repository.UserRepository;
import com.kanggara.budgetin.security.BCrypt;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final Validator validator;

    public UserService(Validator validator, UserRepository userRepository) {
        this.validator = validator;
        this.userRepository = userRepository;
    }

    @Transactional
    public void register(RegisterUserRequest registerUserRequest) {
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(registerUserRequest);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

        if (userRepository.existsById(registerUserRequest.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(BCrypt.hashpw(registerUserRequest.getPassword(), BCrypt.gensalt()));
        user.setName(registerUserRequest.getName());

        userRepository.save(user);
    }
}
