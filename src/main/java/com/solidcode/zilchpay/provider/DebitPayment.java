package com.solidcode.zilchpay.provider;

import com.solidcode.zilchpay.provider.request.PaymentProviderRequest;
import com.solidcode.zilchpay.provider.response.PaymentProviderResponse;

public class DebitPayment implements
    PaymentProvider<DebitService<PaymentProviderRequest, PaymentProviderResponse>> {

  @Override
  @SuppressWarnings("rawtypes")
  public DebitService getProvider(PaymentProviderType type) {
    switch (type) {
      case HSBC:
        return new HSBCPaymentService<>();
      default:
        throw new UnsupportedOperationException("Notification type: " + type + " not supported");
    }
  }
}
