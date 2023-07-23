package com.solidcode.zilchpay.mapper;

import com.solidcode.zilchpay.dto.request.ZilchUserRequest;
import com.solidcode.zilchpay.dto.response.ZilchUserResponse;
import com.solidcode.zilchpay.repository.entity.ZilchUser;
import com.solidcode.zilchpay.util.ZilchCard;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ZilchUserMapper {

  @Mapping(target = "debitCardNumber", source = "cardNumber")
  @Mapping(target = "debitCvv", source = "cvv")
  @Mapping(target = "debitExpiry", source = "expiryDate")
  ZilchUser toZilchUser(ZilchUserRequest zilchUserRequest);

  ZilchUserResponse toZilchUserResponse(ZilchCard zilchCard);
}
