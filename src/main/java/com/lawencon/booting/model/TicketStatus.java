package com.lawencon.booting.model;

public class TicketStatus {

	private Long ticketOpen = 0L;
	private Long ticketClose = 0L;
	private Long ticketReopen = 0L;
	
	
	public Long getTicketOpen() {
		return ticketOpen;
	}
	public void setTicketOpen(Long ticketOpen) {
		this.ticketOpen = ticketOpen;
	}
	public Long getTicketClose() {
		return ticketClose;
	}
	public void setTicketClose(Long ticketClose) {
		this.ticketClose = ticketClose;
	}
	public Long getTicketReopen() {
		return ticketReopen;
	}
	public void setTicketReopen(Long ticketReopen) {
		this.ticketReopen = ticketReopen;
	}
}
