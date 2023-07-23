package com.solidcode.zilchpay.repository.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "brands")
public class ZilchBrand {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private long id;

  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "created_date", updatable = false, nullable = false)
  private Timestamp createdDate;
}
