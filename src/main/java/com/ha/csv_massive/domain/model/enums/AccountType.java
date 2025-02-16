package com.ha.csv_massive.domain.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum AccountType {
	CREDIT("1"),
	DEBIT("2");

	private final String code;

	AccountType(String code) {
		this.code = code;
	}

	public static AccountType fromCode(String code) {
		return Arrays.stream(values())
				.filter(type -> type.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid AccountType code: " + code));
	}
}
