package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "customer_id")
	private Integer customerId;

	@Column(name = "ordered_on")
	private LocalDate orderedOn;

	@Column(name = "total_price")
	private Integer totalPrice;

	private String address;

	@Column(name = "card_number")
	private String cardNumber;

	@Column(name = "security_code")
	private String securityCode;

	@Column(name = "card_limit")
	private LocalDate cardLimit;

	public Order() {
	}

	public Order(Integer customerId, LocalDate orderedOn, Integer totalPrice, String address, String cardNumber,
			String securityCode, LocalDate cardLimit) {
		this.customerId = customerId;
		this.orderedOn = orderedOn;
		this.totalPrice = totalPrice;
		this.address = address;
		this.cardNumber = cardNumber;
		this.securityCode = securityCode;
		this.cardLimit = cardLimit;
	}

	public Integer getId() {
		return id;
	}

	public LocalDate getOrderedOn() {
		return orderedOn;
	}

	public String getAddress() {
		return address;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}
}