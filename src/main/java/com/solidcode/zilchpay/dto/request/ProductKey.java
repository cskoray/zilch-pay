package com.solidcode.zilchpay.dto.request;

import com.solidcode.zilchpay.validator.ValidUUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductKey {

  @ValidUUID
  private String productKey;
}
