package com.solidcode.zilchpay.util;

import java.math.BigDecimal;

public enum PaymentType {
  ONE(new BigDecimal(0.5)),
  FOUR(new BigDecimal(0));

  BigDecimal coefficient;

  PaymentType(BigDecimal coefficient) {
    this.coefficient = coefficient;
  }

  public BigDecimal getCoefficient() {
    return coefficient;
  }
}
