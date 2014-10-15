package com.johnkuper.epam.domain;


public class MerchantDomain {

	private int id;
	private String merchName;
	private String merchSurname;
	private String merchPatronymic;

	public MerchantDomain() {
	}

	public MerchantDomain(String merchName, String merchSurname,
			String merchPatronymic) {
		this.merchName = merchName;
		this.merchSurname = merchSurname;
		this.merchPatronymic = merchPatronymic;
	}

	@Override
	public String toString() {
		return "MerchantDomain: {id = " + id + "; merchName = " + merchName
				+ "; merchSurname = " + merchSurname + "; merchPatronymic = "
				+ merchPatronymic + "}";
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getMerchSurname() {
		return merchSurname;
	}

	public void setMerchSurname(String merchSurname) {
		this.merchSurname = merchSurname;
	}

	public String getMerchPatronymic() {
		return merchPatronymic;
	}

	public void setMerchPatronymic(String merchPatronymic) {
		this.merchPatronymic = merchPatronymic;
	}

}
