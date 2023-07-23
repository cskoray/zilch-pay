package com.solidcode.zilchpay.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.solidcode.zilchpay.dto.request.ZilchUserRequest;
import com.solidcode.zilchpay.dto.response.ZilchUserResponse;
import com.solidcode.zilchpay.service.ZilchUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/api/zilch/users")
@Validated
public class UserController {

  private ZilchUserService zilchUserService;

  @Autowired
  public UserController(ZilchUserService zilchUserService) {
    this.zilchUserService = zilchUserService;
  }

  @PostMapping("/register")
  @ResponseStatus(CREATED)
  public ZilchUserResponse register(@Valid @RequestBody ZilchUserRequest request) {

    log.info("UserController: register {}", request.toString());
    return zilchUserService.register(request);
  }
}
