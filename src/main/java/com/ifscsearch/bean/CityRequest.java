package com.ifscsearch.bean;

import javax.validation.constraints.NotNull;

public class CityRequest {

	@NotNull
	private String bank;

	@NotNull
	private String state;

	@NotNull
	private String district;

	public CityRequest() {
		super();
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
