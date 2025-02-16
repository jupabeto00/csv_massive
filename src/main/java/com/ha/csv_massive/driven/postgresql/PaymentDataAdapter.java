package com.ha.csv_massive.driven.postgresql;

import com.ha.csv_massive.domain.model.PaymentData;
import com.ha.csv_massive.domain.ports.PaymentDataPort;
import com.ha.csv_massive.driven.postgresql.entity.PaymentDataEntity;
import com.ha.csv_massive.driven.postgresql.mapper.PaymentDataMapper;
import com.ha.csv_massive.driven.postgresql.repository.PaymentDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentDataAdapter implements PaymentDataPort {

	private PaymentDataRepository paymentDataRepository;
	private PaymentDataMapper paymentDataMapper;

	@Autowired
	public void setPaymentDataRepository(PaymentDataRepository paymentDataRepository) {
		this.paymentDataRepository = paymentDataRepository;
	}

	@Autowired
	public void setPaymentDataMapper(PaymentDataMapper paymentDataMapper) {
		this.paymentDataMapper = paymentDataMapper;
	}

	@Override
	public void saveAll(List<PaymentData> paymentData) {
		List<PaymentDataEntity> paymentDataEntityList = paymentDataMapper.toEntity(paymentData);
		paymentDataRepository.saveAll(paymentDataEntityList);
	}
}
