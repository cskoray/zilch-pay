package com.solidcode.zilchpay.service;

import static com.solidcode.zilchpay.exception.ErrorType.USER_NOT_FOUND;
import static com.solidcode.zilchpay.util.PaymentType.FOUR;
import static java.time.LocalDateTime.now;

import com.solidcode.zilchpay.adaptor.StoreClient;
import com.solidcode.zilchpay.dto.request.PaymentRequest;
import com.solidcode.zilchpay.dto.response.PaymentResponse;
import com.solidcode.zilchpay.exception.UserNotFoundException;
import com.solidcode.zilchpay.provider.DebitService;
import com.solidcode.zilchpay.provider.request.PaymentProviderRequest;
import com.solidcode.zilchpay.provider.response.PaymentProviderResponse;
import com.solidcode.zilchpay.repository.TransactionRepository;
import com.solidcode.zilchpay.repository.ZilchUserRepository;
import com.solidcode.zilchpay.repository.entity.ZilchTransaction;
import com.solidcode.zilchpay.repository.entity.ZilchUser;
import com.solidcode.zilchpay.util.PaymentType;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RefreshScope
public class PaymentService {

  private StoreClient storeClient;
  private DebitService debitService;
  private ZilchUserRepository zilchUserRepository;
  private TransactionRepository transactionRepository;

  @Autowired
  public PaymentService(StoreClient storeClient, DebitService debitService,
      ZilchUserRepository zilchUserRepository, TransactionRepository transactionRepository) {
    this.storeClient = storeClient;
    this.debitService = debitService;
    this.zilchUserRepository = zilchUserRepository;
    this.transactionRepository = transactionRepository;
  }

  public PaymentResponse pay(PaymentRequest request) {

    ZilchUser zilchUser = zilchUserRepository.findByPaymentToken(request.getPaymentToken())
        .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND, "paymentToken"));

    List<String> keys = request.getProductKeys().stream()
        .map(product -> product.getProductKey())
        .collect(Collectors.toList());
    BigDecimal amount = storeClient.getAmount(keys).getAmount();

    PaymentProviderRequest paymentProviderRequest = PaymentProviderRequest.builder()
        .name(request.getMerchantName())
        .amount(amount)
        .cardNumber(zilchUser.getDebitCardNumber())
        .cvv(zilchUser.getDebitCvv())
        .expiryDate(zilchUser.getDebitExpiry())
        .build();
    PaymentProviderResponse paymentProviderResponse = debitService.pay(paymentProviderRequest);

    if (paymentProviderResponse.getStatus().equals("success")) {

      ZilchTransaction transaction = ZilchTransaction.builder()
          .userKey(zilchUser.getUserKey())
          .paymentToken(request.getPaymentToken())
          .merchantName(request.getMerchantName())
          .amount(amount)
          .createdDate(Timestamp.valueOf(now()))
          .build();
      transactionRepository.save(transaction);
      BigDecimal cashback = calculateCashback(amount, request.getPaymentType());
      String apr = calculateAPR(amount, request.getPaymentType());
      return PaymentResponse.builder()
          .status("success")
          .cashback(cashback)
          .APR(apr)
          .build();
    }
    throw new UserNotFoundException(USER_NOT_FOUND, "paymentToken");
  }

  private String calculateAPR(BigDecimal amount, String paymentType) {

    String apr = "0%";
    if (PaymentType.valueOf(paymentType).equals(FOUR)) {
      if (amount.compareTo(new BigDecimal("1000")) == 1) {
        apr = "10%";
      } else {
        apr = "5%";
      }
    }
    return apr;
  }

  private BigDecimal calculateCashback(BigDecimal amount, String paymentType) {
    return amount.multiply(PaymentType.valueOf(paymentType).getCoefficient());
  }
}
