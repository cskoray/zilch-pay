package com.solidcode.zilchpay.util;

import static java.lang.String.valueOf;
import static java.util.Calendar.YEAR;

import com.solidcode.zilchpay.util.ZilchCard;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RefreshScope
@ToString
public class ZilchCardGenerator {

  public ZilchCard generateCard() {

    log.info("ZilchCardGenerator: generateCardNumber");
    Random random = new Random();
    String cardNumber = getCardNumber(random);
    String expiry = getExpiry(random);

    return ZilchCard.builder().cardNumber(cardNumber).cvv(valueOf(random.nextInt(900) + 100))
        .expiryDate(expiry).creditLimit(new BigDecimal(100))
        .paymentToken(UUID.randomUUID().toString()).build();
  }

  private String getCardNumber(Random random) {

    return "5169" + String.format("%04d", random.nextInt(10000)) + String.format("%04d",
        random.nextInt(10000)) + String.format("%04d", random.nextInt(10000));
  }

  private String getExpiry(Random random) {

    String month = getMonth();
    int lastTwoDigits = (Calendar.getInstance().get(YEAR) % 100) + 5;
    return month + lastTwoDigits;
  }

  private String getMonth() {

    int randomNum = ThreadLocalRandom.current().nextInt(1, 12);
    if (randomNum < 10) {
      return "0" + randomNum;
    } else {
      return valueOf(randomNum);
    }
  }
}
