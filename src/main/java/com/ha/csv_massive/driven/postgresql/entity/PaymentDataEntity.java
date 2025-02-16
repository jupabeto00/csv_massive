package com.ha.csv_massive.driven.postgresql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment_data_entity")
public class PaymentDataEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn
	private DocumentEntity document;

	@Column(name = "name")
	private String name;

	@Column(name = "bank")
	private String bank;

	@ManyToOne
	@JoinColumn
	private AccountEntity account;

	@Column(name = "amount")
	private String amount;

	@Column(name = "reference")
	private String reference;

}