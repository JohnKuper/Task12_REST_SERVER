package com.johnkuper.epam.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "store")
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_store")
	private int id;

	@ManyToOne
	@JoinColumn(name = "id_car", nullable = false)
	private Car car;

	private int count;

	private BigDecimal price;

	@Column(length = 1, columnDefinition = "BIT")
	private boolean testdrive_avaible;

	public Store() {
	}

	public Store(Car car, int count, BigDecimal price, boolean testdrive_avaible) {
		this.car = car;
		this.count = count;
		this.price = price;
		this.testdrive_avaible = testdrive_avaible;
	}

	@Override
	public String toString() {
		return " Store: {id = " + id + "; car = " + car + "; count = " + count
				+ "; price = " + price + "; testdrive_avaible = "
				+ testdrive_avaible + "} ";
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public boolean isTestdrive_avaible() {
		return testdrive_avaible;
	}

	public void setTestdrive_avaible(boolean testdrive_avaible) {
		this.testdrive_avaible = testdrive_avaible;
	}

}
