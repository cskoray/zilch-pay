package com.solidcode.zilchpay.service;

import static java.time.LocalDateTime.now;

import com.solidcode.zilchpay.dto.request.ZilchBrandRequest;
import com.solidcode.zilchpay.dto.response.ZilchBrandResponse;
import com.solidcode.zilchpay.repository.ZilchBrandRepository;
import com.solidcode.zilchpay.repository.entity.ZilchBrand;
import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RefreshScope
public class ZilchBrandService {

  private ZilchBrandRepository zilchBrandRepository;

  @Autowired
  public ZilchBrandService(ZilchBrandRepository zilchBrandRepository) {
    this.zilchBrandRepository = zilchBrandRepository;
  }

  public ZilchBrandResponse register(ZilchBrandRequest request) {

    ZilchBrand zilchBrand = ZilchBrand.builder()
        .name(request.getName())
        .createdDate(Timestamp.valueOf(now()))
        .build();
    zilchBrandRepository.save(zilchBrand);
    return ZilchBrandResponse.builder()
        .status("success")
        .build();
  }
}
