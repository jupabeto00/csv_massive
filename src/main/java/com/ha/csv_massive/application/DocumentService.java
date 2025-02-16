package com.ha.csv_massive.application;

import com.ha.csv_massive.domain.model.Document;

import java.util.List;

public interface DocumentService {
	List<Document> saveAll(List<Document> documentList);
}
