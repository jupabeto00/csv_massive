package com.ha.csv_massive.driven.postgresql.repository;

import com.ha.csv_massive.driven.postgresql.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}