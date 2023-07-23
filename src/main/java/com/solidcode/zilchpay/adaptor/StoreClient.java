package com.solidcode.zilchpay.adaptor;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.solidcode.zilchpay.adaptor.response.ProductAmountResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("API-STORE")
public interface StoreClient {

  @PostMapping(value = "/v1/api/store/products/list", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
  ProductAmountResponse getAmount(List<String> productKeys);
}
