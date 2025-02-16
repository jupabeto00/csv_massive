package com.ha.csv_massive.domain.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record PaymentData(
	Long id,
	Document document,
	String name,
	String bank,
	Account account,
	String amount,
	String reference
) implements Serializable {
}