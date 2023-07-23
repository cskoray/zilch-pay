package com.solidcode.zilchpay.controller;

import static org.springframework.http.HttpStatus.OK;

import com.solidcode.zilchpay.dto.request.PaymentRequest;
import com.solidcode.zilchpay.dto.response.PaymentResponse;
import com.solidcode.zilchpay.service.PaymentService;
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
@RequestMapping("/v1/api/zilch/payment")
@Validated
public class PaymentController {

  private PaymentService paymentService;

  @Autowired
  public PaymentController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PostMapping("/pay")
  @ResponseStatus(OK)
  public PaymentResponse pay(@Valid @RequestBody PaymentRequest request) {

    log.info("PaymentController: register {}", request.toString());
    return paymentService.pay(request);
  }
}
