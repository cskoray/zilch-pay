package com.solidcode.zilchpay.util;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ZilchCard {

  private String cardNumber;
  private String cvv;
  private String expiryDate;
  private BigDecimal creditLimit;
  private String paymentToken;
}
