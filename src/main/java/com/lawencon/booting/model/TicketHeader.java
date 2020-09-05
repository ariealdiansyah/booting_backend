package com.lawencon.booting.model;

public class TicketHeader {

	private Tickets idTicket;
	private Users idAgent;
	
	public Tickets getIdTicket() {
		return idTicket;
	}
	public void setIdTicket(Tickets idTicket) {
		this.idTicket = idTicket;
	}
	public Users getIdAgent() {
		return idAgent;
	}
	public void setIdAgent(Users idAgent) {
		this.idAgent = idAgent;
	}
}
