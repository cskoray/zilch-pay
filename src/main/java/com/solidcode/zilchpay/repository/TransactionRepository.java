package com.solidcode.zilchpay.repository;

import com.solidcode.zilchpay.repository.entity.ZilchTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<ZilchTransaction, Long> {

}
