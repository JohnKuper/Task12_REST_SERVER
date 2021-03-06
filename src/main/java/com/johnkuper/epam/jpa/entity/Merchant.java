package com.johnkuper.epam.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchants")
public class Merchant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_merchant")
	private int id;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String surname;

	@Column(length = 100)
	private String patronymic;

	public Merchant() {
	}

	public Merchant(String name, String surname, String patronymic) {
		this.name = name;
		this.surname = surname;
		this.patronymic = patronymic;
	}

	@Override
	public String toString() {
		return " Merchant: {id = " + id + "; name = " + name + "; surname = "
				+ surname + "; patronymic = " + patronymic + "} ";
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

	

}
