package com.solidcode.zilchpay.provider;

public interface PaymentProvider <T> {
  T getProvider(PaymentProviderType type);
}

