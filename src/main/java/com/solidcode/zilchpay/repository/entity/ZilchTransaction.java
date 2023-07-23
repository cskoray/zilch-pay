package com.solidcode.zilchpay.repository.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "transactions")
public class ZilchTransaction {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "user_key", nullable = false)
  private String userKey;

  @Column(name = "payment_token", nullable = false)
  private String paymentToken;

  @Column(name = "merchant_name", nullable = false)
  private String merchantName;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "created_date", updatable = false, nullable = false)
  private Timestamp createdDate;
}
