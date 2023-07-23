package com.solidcode.zilchpay.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.solidcode.zilchpay.dto.request.ZilchBrandRequest;
import com.solidcode.zilchpay.dto.response.ZilchBrandResponse;
import com.solidcode.zilchpay.service.ZilchBrandService;
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
@RequestMapping("/v1/api/zilch/brands")
@Validated
public class BrandController {

  private ZilchBrandService zilchBrandService;

  @Autowired
  public BrandController(ZilchBrandService zilchBrandService) {
    this.zilchBrandService = zilchBrandService;
  }

  @PostMapping("/register")
  @ResponseStatus(CREATED)
  public ZilchBrandResponse register(@Valid @RequestBody ZilchBrandRequest request) {

    log.info("BrandController: register {}", request.toString());
    return zilchBrandService.register(request);
  }
}
