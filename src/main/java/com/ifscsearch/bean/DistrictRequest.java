package com.ifscsearch.bean;

import javax.validation.constraints.NotNull;

public class DistrictRequest {

	@NotNull
	private String bank;

	@NotNull
	private String state;

	public DistrictRequest() {
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
}
