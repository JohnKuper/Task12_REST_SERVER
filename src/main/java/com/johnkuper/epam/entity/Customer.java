package com.johnkuper.epam.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_customer")
	private int id;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String surname;

	@Column(length = 100)
	private String patronymic;

	@Column(length = 4)
	private String passport_series;

	@Column(length = 6)
	private String passport_number;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	public Customer() {
	}

	public Customer(String name, String surname, String patronic,
			String passport_series, String passport_number, Date birthdate) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronic;
		this.passport_series = passport_series;
		this.passport_number = passport_number;
		this.birthdate = birthdate;
	}

	@Override
	public String toString() {
		return " Customer: {id = " + id + "; name = " + name + "; surname = "
				+ surname + "; patronymic = " + patronymic
				+ "; passport_series = " + passport_series
				+ "; passport_number = " + passport_number + "; birthdate = "
				+ birthdate + "} ";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getPassport_series() {
		return passport_series;
	}

	public void setPassport_series(String passport_series) {
		this.passport_series = passport_series;
	}

	public String getPassport_number() {
		return passport_number;
	}

	public void setPassport_number(String passport_number) {
		this.passport_number = passport_number;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
