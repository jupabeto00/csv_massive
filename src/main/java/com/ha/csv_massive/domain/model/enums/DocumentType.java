package com.ha.csv_massive.domain.model.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DocumentType {
	CC("1"),
	TI("2"),
	CE("3"),
	CP("4"),
	PA("5");

	private final String code;

	DocumentType(String code) {
		this.code = code;
	}

	public static DocumentType fromCode(String code) {
		return Arrays.stream(values())
				.filter(type -> type.getCode().equals(code))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid DocumentType code: " + code));
	}
}
