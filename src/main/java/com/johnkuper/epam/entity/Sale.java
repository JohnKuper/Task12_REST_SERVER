package com.johnkuper.epam.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sales")
public class Sale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_sale")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_car")
	private Car car;

	@ManyToOne
	@JoinColumn(name = "id_customer")
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "id_merchant")
	private Merchant merchant;

	private BigDecimal price;

	@Column(name = "sale_date", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Temporal(TemporalType.TIMESTAMP)
	private Date saleDate;

	public Sale() {
	}

	public Sale(Car car, Customer customer, Merchant merchant, BigDecimal price) {
		this.car = car;
		this.customer = customer;
		this.merchant = merchant;
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("%n Sale: id = " + id + "; price = " + price
				+ "; sale_date = " + saleDate + "%n" + car + "%n" + customer
				+ "%n" + merchant + "%n");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

}
