package com.solidcode.zilchpay.repository;

import com.solidcode.zilchpay.repository.entity.ZilchUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZilchUserRepository extends JpaRepository<ZilchUser, Long> {

  Optional<ZilchUser> findByPaymentToken(String paymentToken);

}
