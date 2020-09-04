package com.lawencon.booting.model;

public class ReportTotalTicketAgent {

	private String customers_name;
	private String companies_name;
	private Long total_ticket;
	private Long solved_ticket;

	public String getCustomers_name() {
		return customers_name;
	}

	public void setCustomers_name(String customers_name) {
		this.customers_name = customers_name;
	}

	public String getCompanies_name() {
		return companies_name;
	}

	public void setCompanies_name(String companies_name) {
		this.companies_name = companies_name;
	}

	public Long getTotal_ticket() {
		return total_ticket;
	}

	public void setTotal_ticket(Long total_ticket) {
		this.total_ticket = total_ticket;
	}

	public Long getSolved_ticket() {
		return solved_ticket;
	}

	public void setSolved_ticket(Long solved_ticket) {
		this.solved_ticket = solved_ticket;
	}

}
