package com.solidcode.zilchpay.provider;

import com.solidcode.zilchpay.provider.request.PaymentProviderRequest;
import com.solidcode.zilchpay.provider.response.PaymentProviderResponse;
import org.springframework.stereotype.Component;

@Component
public class HSBCPaymentService<T extends PaymentProviderRequest, R extends PaymentProviderResponse> implements
    DebitService<T, R> {

  @Override
  public PaymentProviderResponse pay(PaymentProviderRequest request) {

    return PaymentProviderResponse.builder().status("success").build();
  }
}
