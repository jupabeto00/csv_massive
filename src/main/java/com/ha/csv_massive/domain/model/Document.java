package com.ha.csv_massive.domain.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record Document(
	Long id,
	String type,
	String number
) implements Serializable {
}