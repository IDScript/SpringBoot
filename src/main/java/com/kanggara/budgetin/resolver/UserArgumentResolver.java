package com.kanggara.budgetin.resolver;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;
import com.kanggara.budgetin.entities.UserEntity;
import com.kanggara.budgetin.repository.UserRepository;

@Slf4j
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

  private UserRepository userRepository;

  public UserArgumentResolver(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean supportsParameter(@NonNull MethodParameter parameter) {
    return UserEntity.class.equals(parameter.getParameterType());
  }

  @Override
  @Nullable
  public Object resolveArgument(
      @NonNull MethodParameter parameter,
      @Nullable ModelAndViewContainer mavContainer,
      @NonNull NativeWebRequest webRequest,
      @Nullable WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest servletRequest = (HttpServletRequest) webRequest.getNativeRequest();
    String token = servletRequest.getHeader("X-API-TOKEN");
    log.info("TOKEN {}", token);
    if (token == null) {
      throw unauthorized();
    }

    UserEntity userEntity = userRepository.findByToken(token).orElseThrow(this::unauthorized);
    log.info("USER {}", userEntity);
    if (userEntity.getTokenExpiriedAt() < (System.currentTimeMillis() / 1000)) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token Expiried");
    }

    return userEntity;
  }

  private ResponseStatusException unauthorized() {
    return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unautorized");

  }
}
