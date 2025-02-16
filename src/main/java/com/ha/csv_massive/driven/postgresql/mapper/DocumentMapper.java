package com.ha.csv_massive.driven.postgresql.mapper;

import com.ha.csv_massive.domain.model.Document;
import com.ha.csv_massive.driven.postgresql.entity.DocumentEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DocumentMapper {
	DocumentEntity toEntity(Document document);
	List<DocumentEntity> toEntity(List<Document> document);

	Document toDto(DocumentEntity documentEntity);
	List<Document> toDto(List<DocumentEntity> documentEntity);
}