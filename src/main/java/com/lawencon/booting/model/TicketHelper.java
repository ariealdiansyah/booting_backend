package com.lawencon.booting.model;

import java.util.List;

public class TicketHelper {

	private TicketDetails ticketDetail;
	private List<Attachments> attachments;
	
	public TicketDetails getTicketDetail() {
		return ticketDetail;
	}
	public void setTicketDetail(TicketDetails ticketDetail) {
		this.ticketDetail = ticketDetail;
	}
	public List<Attachments> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachments> attachments) {
		this.attachments = attachments;
	} 
}
