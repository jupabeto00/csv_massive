package com.ha.csv_massive.application.impl;

import com.ha.csv_massive.application.AccountService;
import com.ha.csv_massive.application.DocumentService;
import com.ha.csv_massive.application.MassiveService;
import com.ha.csv_massive.application.PaymentDataService;
import com.ha.csv_massive.domain.model.Account;
import com.ha.csv_massive.domain.model.Document;
import com.ha.csv_massive.domain.model.PaymentData;
import com.ha.csv_massive.domain.model.enums.AccountType;
import com.ha.csv_massive.domain.model.enums.DocumentType;
import com.ha.csv_massive.driving.rest.dto.MetadataCSV;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class MassiveServiceImpl implements MassiveService {

	private PaymentDataService paymentDataService;
	private DocumentService documentService;
	private AccountService accountService;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;

	@Autowired
	public void setPaymentDataService(PaymentDataService paymentDataService) {
		this.paymentDataService = paymentDataService;
	}

	@Autowired
	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	@Autowired
	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	@Override
	public void processCsvFile(MetadataCSV metadata, MultipartFile file) {
		log.debug("Start -> Process CSV: {}", file.getName());
		Path filePath = Path.of(Objects.requireNonNull(file.getOriginalFilename()));

		try (Reader reader = Files.newBufferedReader(filePath); CSVReader csvReader = createCsvReader(reader)) {
			List<String[]> batch = new ArrayList<>();
			String[] line;

			log.debug("Start -> Read lines: {}", file.getName());
			while ((line = csvReader.readNext()) != null) {
				batch.add(line);

				if (batch.size() == batchSize) {
					log.debug("Start -> Batch: {}", batch.size());
					processBatchCsv(batch);
					batch.clear();
				}
			}
		} catch (Exception e) {
			log.error("Error -> Process CSV: {}", file.getName(), e);
			throw new RuntimeException(e);
		}

		log.debug("End -> Process CSV: {}", file.getName());
	}

	private void processBatchCsv(List<String[]> batch) {
		log.debug("Start -> Process Map CSV: {}", batch.size());

		sendToSavePayments(batch,
				sendToSaveDocuments(batch),
				sendToSaveAccounts(batch)
		);

		log.debug("End -> Process Map CSV: {}", batch.size());
	}

	private CSVReader createCsvReader(Reader reader) {
		CSVParser parser = new CSVParserBuilder().withSeparator(',').withIgnoreQuotations(true).build();

		return new CSVReaderBuilder(reader).withSkipLines(1).withCSVParser(parser).build();
	}

	private List<Document> sendToSaveDocuments(List<String[]> batch) {
		log.debug("Start -> Save Documents: {}", batch.size());
		return documentService.saveAll(batch.stream()
				.map(data ->
						Document.builder()
								.type(DocumentType.fromCode(data[0]).toString())
								.number(data[1])
								.build()
				)
				.toList());
	}

	private List<Account> sendToSaveAccounts(List<String[]> batch) {
		log.debug("Start -> Save Accounts: {}", batch.size());
		return accountService.saveAll(batch.stream()
				.map(data ->
						Account.builder()
								.type(AccountType.fromCode(data[4]).toString())
								.number(data[5])
								.build()
				)
				.toList());
	}

	private void sendToSavePayments(List<String[]> batch, List<Document> documentsSaved, List<Account> accountsSaved) {
		log.debug("Start -> Save Payments: {}", batch.size());
		paymentDataService.saveBatch(batch.stream()
				.map(data -> PaymentData.builder()
						.document(documentsSaved.stream()
								.filter(document -> document.type().equals(DocumentType.fromCode(data[0]).toString()) &&
										document.number().equals(data[1]))
								.findFirst()
								.orElseThrow(() -> new IllegalArgumentException("Not matching document found"))
						)
						.name(data[2])
						.bank(data[3])
						.account(accountsSaved.stream()
								.filter(account -> account.type().equals(AccountType.fromCode(data[4]).toString()) &&
										account.number().equals(data[5]))
								.findFirst()
								.orElseThrow(() -> new IllegalArgumentException("Not matching account found"))
						)
						.amount(data[6])
						.reference(data[7])
						.build())
				.toList());
	}
}
