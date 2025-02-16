package com.ha.csv_massive.driven.postgresql.repository;

import com.ha.csv_massive.driven.postgresql.entity.PaymentDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDataRepository extends JpaRepository<PaymentDataEntity, Long> {
}