package com.ha.csv_massive.driven.postgresql.repository;

import com.ha.csv_massive.driven.postgresql.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}