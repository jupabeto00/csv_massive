package com.ha.csv_massive.application.impl;

import com.ha.csv_massive.application.DocumentService;
import com.ha.csv_massive.domain.model.Document;
import com.ha.csv_massive.domain.ports.DocumentPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

	private DocumentPort documentPort;

	@Autowired
	public void setDocumentPort(DocumentPort documentPort) {
		this.documentPort = documentPort;
	}

	@Override
	public List<Document> saveAll(List<Document> documentList) {
		log.debug("Start -> Save Batch Document List: {}", documentList.size());
		List<Document> documents = documentPort.saveAll(documentList);
		log.debug("End -> Save Batch Document List: {}", documentList.size());
		return documents.isEmpty() ? null : documents;
	}
}
