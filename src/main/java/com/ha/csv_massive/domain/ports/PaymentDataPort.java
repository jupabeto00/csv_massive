package com.ha.csv_massive.domain.ports;

import com.ha.csv_massive.domain.model.PaymentData;

import java.util.List;

public interface PaymentDataPort {
	void saveAll(List<PaymentData> paymentData);
}
