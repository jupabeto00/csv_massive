package com.ha.csv_massive.domain.ports;

import com.ha.csv_massive.domain.model.Document;

import java.util.List;

public interface DocumentPort {
	List<Document> saveAll(List<Document> documentList);
}
