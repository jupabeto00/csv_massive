package com.ha.csv_massive.application.impl;

import com.ha.csv_massive.application.PaymentDataService;
import com.ha.csv_massive.domain.model.PaymentData;
import com.ha.csv_massive.domain.ports.PaymentDataPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentDataServiceImpl implements PaymentDataService {

	private PaymentDataPort paymentDataPort;

	@Autowired
	public void setPaymentDataPort(PaymentDataPort paymentDataPort) {
		this.paymentDataPort = paymentDataPort;
	}

	@Override
	public void saveBatch(List<PaymentData> paymentDataList) {
		log.debug("Start -> Save Batch PaymentData List: {}", paymentDataList.size());
		paymentDataPort.saveAll(paymentDataList);
		log.debug("End -> Save Batch PaymentData List: {}", paymentDataList.size());
	}
}
