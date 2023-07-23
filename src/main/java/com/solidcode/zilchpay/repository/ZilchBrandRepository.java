package com.solidcode.zilchpay.repository;

import com.solidcode.zilchpay.repository.entity.ZilchBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZilchBrandRepository extends JpaRepository<ZilchBrand, Long> {

}
