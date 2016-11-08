package com.ifscsearch.bean;

import javax.validation.constraints.NotNull;

public class BranchRequest {

	@NotNull
	private String bank;

	@NotNull
	private String state;

	@NotNull
	private String district;

	@NotNull
	private String city;

	private String branch;

	public BranchRequest() {
		super();
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
