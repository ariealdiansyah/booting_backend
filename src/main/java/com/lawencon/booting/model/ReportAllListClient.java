package com.lawencon.booting.model;

import java.util.Date;

public class ReportAllListClient {

	private String agents_name;
	private String companies_name;
	private Date startDate;
	private Date endDate;

	public String getAgents_name() {
		return agents_name;
	}

	public void setAgents_name(String agents_name) {
		this.agents_name = agents_name;
	}

	public String getCompanies_name() {
		return companies_name;
	}

	public void setCompanies_name(String companies_name) {
		this.companies_name = companies_name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
