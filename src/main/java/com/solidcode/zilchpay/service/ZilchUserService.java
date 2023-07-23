package com.solidcode.zilchpay.service;

import static java.time.LocalDateTime.now;

import com.solidcode.zilchpay.dto.request.ZilchUserRequest;
import com.solidcode.zilchpay.dto.response.ZilchUserResponse;
import com.solidcode.zilchpay.mapper.ZilchUserMapper;
import com.solidcode.zilchpay.repository.ZilchUserRepository;
import com.solidcode.zilchpay.repository.entity.ZilchUser;
import com.solidcode.zilchpay.util.ZilchCard;
import com.solidcode.zilchpay.util.ZilchCardGenerator;
import java.sql.Timestamp;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RefreshScope
public class ZilchUserService {

  private ZilchUserRepository zilchUserRepository;
  private ZilchUserMapper zilchUserMapper;
  private ZilchCardGenerator zilchCardGenerator;

  @Autowired
  public ZilchUserService(ZilchUserRepository zilchUserRepository,
      ZilchUserMapper zilchUserMapper, ZilchCardGenerator zilchCardGenerator) {
    this.zilchUserRepository = zilchUserRepository;
    this.zilchUserMapper = zilchUserMapper;
    this.zilchCardGenerator = zilchCardGenerator;
  }

  public ZilchUserResponse register(ZilchUserRequest request) {

    log.info("ZilchUserService: register {}", request.toString());
    ZilchUser zilchUser = zilchUserMapper.toZilchUser(request);
    ZilchCard zilchCard = zilchCardGenerator.generateCard();
    zilchUser.setZilchCardNumber(zilchCard.getCardNumber());
    zilchUser.setZilchCvv(zilchCard.getCvv());
    zilchUser.setZilchExpiry(zilchCard.getExpiryDate());
    zilchUser.setZilchCreditLimit(zilchCard.getCreditLimit());
    zilchUser.setPaymentToken(zilchCard.getPaymentToken());
    zilchUser.setCreatedDate(Timestamp.valueOf(now()));
    zilchUser.setUserKey(UUID.randomUUID().toString());
    zilchUserRepository.save(zilchUser);
    return zilchUserMapper.toZilchUserResponse(zilchCard);
  }
}
