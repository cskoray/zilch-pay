package com.solidcode.zilchpay.provider;

import com.solidcode.zilchpay.provider.request.PaymentProviderRequest;
import com.solidcode.zilchpay.provider.response.PaymentProviderResponse;
import org.springframework.stereotype.Component;

@Component
public interface DebitService<T extends PaymentProviderRequest, R extends PaymentProviderResponse> {

  R pay(T request);
}
