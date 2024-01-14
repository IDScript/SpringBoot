package com.kanggara.budgetin.services;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kanggara.budgetin.security.BCrypt;

import jakarta.transaction.Transactional;

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
		validationService.validate(request);

		UserEntity userEntity = userRepository.findById(request.getUsername())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong"));

		if (BCrypt.checkpw(request.getPassword(), userEntity.getPassword())) {
			// sukses
			userEntity.setToken(UUID.randomUUID().toString());
			userEntity.setTokenExpiriedAt(next30Day());
			userRepository.save(userEntity);

			return TokenResponse.builder()
					.token(userEntity.getToken())
					.expiredAt(userEntity.getTokenExpiriedAt())
					.build();

		} else {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong");

		}

	}

	private Long next30Day() {
		return System.currentTimeMillis() + 30 * 1000 * 60 * 24;

	}

}
