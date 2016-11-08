package com.ifscsearch.dataaccess.domain;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class Branch {

	@Id
	@Field
	private String id;
	@Field
	private String name;
	@Field
	private String bank;
	@Field
	private String ifsc;
	@Field
	private String micr;
	@Field
	private String state;
	@Field
	private String city;
	@Field
	private String district;
	@Field
	private String address;
	@Field
	private String contact;

	public Branch() {
	}

	public Branch(String name, String bank, String ifsc, String micr,
			String state, String city, String district, String address,
			String contact) {
		super();
		this.name = name;
		this.bank = bank;
		this.ifsc = ifsc;
		this.micr = micr;
		this.state = state;
		this.city = city;
		this.district = district;
		this.address = address;
		this.contact = contact;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getMicr() {
		return micr;
	}

	public void setMicr(String micr) {
		this.micr = micr;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getId() {
		return id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
