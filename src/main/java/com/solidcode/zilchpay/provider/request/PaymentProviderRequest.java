package com.solidcode.zilchpay.provider.request;

import com.solidcode.zilchpay.validator.ValidCardNumber;
import com.solidcode.zilchpay.validator.ValidCvv;
import com.solidcode.zilchpay.validator.ValidEmail;
import com.solidcode.zilchpay.validator.ValidExpiryDate;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
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
public class PaymentProviderRequest {

  private BigDecimal amount;

  @NotNull
  @Size(min = 3, max = 35)
  private String name;

  @ValidEmail
  @NotNull
  @NotEmpty
  private String email;

  @ValidCardNumber
  @NotNull
  @NotEmpty
  private String cardNumber;

  @NotNull
  @ValidExpiryDate
  private String expiryDate;

  @NotNull
  @ValidCvv
  private String cvv;
}
