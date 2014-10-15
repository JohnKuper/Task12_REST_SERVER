package com.johnkuper.epam.domain;

import java.math.BigDecimal;
import java.sql.Date;

public class SaleDomain {

	private int id;
	private CarDomain car;
	private CustomerDomain customer;
	private MerchantDomain merchant;
	private BigDecimal salePrice;
	private Date dateOfSale;

	public SaleDomain() {

	}

	public SaleDomain(CarDomain car, CustomerDomain customer,
			MerchantDomain merchant, BigDecimal salePrice, Date saleTime) {
		this.car = car;
		this.customer = customer;
		this.merchant = merchant;
		this.salePrice = salePrice;
		this.dateOfSale = saleTime;
	}

	@Override
	public String toString() {
		return String.format("SaleDomain: {id = " + id + "; salePrice = "
				+ salePrice + "; dateOfSale = " + dateOfSale + "%n" + car
				+ "%n" + customer + "%n" + merchant + "%n");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CarDomain getCar() {
		return car;
	}

	public void setCar(CarDomain car) {
		this.car = car;
	}

	public CustomerDomain getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDomain customer) {
		this.customer = customer;
	}

	public MerchantDomain getMerchant() {
		return merchant;
	}

	public void setMerchant(MerchantDomain merchant) {
		this.merchant = merchant;
	}

	public BigDecimal getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}

	public Date getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

}
