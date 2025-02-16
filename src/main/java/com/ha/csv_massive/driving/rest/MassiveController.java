package com.ha.csv_massive.driving.rest;

import com.ha.csv_massive.application.MassiveService;
import com.ha.csv_massive.driving.rest.dto.MetadataCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController()
@RequestMapping("/massive")
public class MassiveController {

	private MassiveService massiveService;

	@Autowired
	public void setMassiveService(MassiveService massiveService) {
		this.massiveService = massiveService;
	}

	@PostMapping(value = "/bulk-csv", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> processCsvFile(
			@RequestPart("meta-data") MetadataCSV metadata,
			@RequestPart("file-data") MultipartFile file
	) {
		massiveService.processCsvFile(metadata, file);
		return ResponseEntity.ok("OK");
	}
}
