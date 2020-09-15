package com.lawencon.booting.model;

public class ReportTotalTicketAgent {

	private String customers_name;
	private String companies_name;
	private String subject;
	private String ticket_status;
	private String classification;
	
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTicket_status() {
		return ticket_status;
	}

	public void setTicket_status(String ticket_status) {
		this.ticket_status = ticket_status;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

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

}
