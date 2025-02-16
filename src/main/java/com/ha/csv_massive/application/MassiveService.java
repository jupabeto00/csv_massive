package com.ha.csv_massive.application;

import com.ha.csv_massive.driving.rest.dto.MetadataCSV;
import org.springframework.web.multipart.MultipartFile;

public interface MassiveService {
	void processCsvFile(MetadataCSV metadata, MultipartFile file);
}
