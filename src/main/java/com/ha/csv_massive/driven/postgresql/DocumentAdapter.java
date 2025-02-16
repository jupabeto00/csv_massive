package com.ha.csv_massive.driven.postgresql;

import com.ha.csv_massive.domain.model.Document;
import com.ha.csv_massive.domain.ports.DocumentPort;
import com.ha.csv_massive.driven.postgresql.entity.DocumentEntity;
import com.ha.csv_massive.driven.postgresql.mapper.DocumentMapper;
import com.ha.csv_massive.driven.postgresql.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentAdapter implements DocumentPort {

	private DocumentRepository documentRepository;
	private DocumentMapper documentMapper;

	@Autowired
	public void setDocumentRepository(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}

	@Autowired
	public void setDocumentMapper(DocumentMapper documentMapper) {
		this.documentMapper = documentMapper;
	}

	@Override
	public List<Document> saveAll(List<Document> documentList) {
		List<DocumentEntity> documentEntityList = documentMapper.toEntity(documentList);
		List<Document> documentListSaved = documentMapper.toDto(
				documentRepository.saveAll(documentEntityList
				));
		return documentListSaved.isEmpty() ? null : documentListSaved;
	}
}
