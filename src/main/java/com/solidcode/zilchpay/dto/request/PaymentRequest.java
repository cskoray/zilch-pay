package com.solidcode.zilchpay.dto.request;

import com.solidcode.zilchpay.validator.ValidPaymentType;
import com.solidcode.zilchpay.validator.ValidUUID;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
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
public class PaymentRequest {

  @ValidUUID
  private String paymentToken;

  @ValidPaymentType
  private String paymentType;

  @NotNull
  @Size(min = 3, max = 35)
  private String merchantName;

  private List<ProductKey> productKeys;
}
