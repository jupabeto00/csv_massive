package com.ha.csv_massive.application;

import com.ha.csv_massive.domain.model.PaymentData;

import java.util.List;

public interface PaymentDataService {
	void saveBatch(List<PaymentData> paymentDataList);
}
