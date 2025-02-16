package com.ha.csv_massive.domain.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record Account(
	Long id,
	String number,
	String type
) implements Serializable {
}