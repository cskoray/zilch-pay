package com.solidcode.zilchpay.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PaymentTypeValidator implements ConstraintValidator<ValidPaymentType, String> {

  @Override
  public void initialize(ValidPaymentType constraintAnnotation) {
  }

  @Override
  public boolean isValid(String type, ConstraintValidatorContext context) {
    return (validatePaymentType(type));
  }

  private boolean validatePaymentType(String type) {
    return type.equalsIgnoreCase("ONE") || type.equalsIgnoreCase("FOUR");
  }
}