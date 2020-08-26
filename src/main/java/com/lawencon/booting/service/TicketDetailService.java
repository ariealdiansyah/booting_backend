package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.TicketDetails;

public interface TicketDetailService {
	
	TicketDetails insert(TicketDetails data) throws Exception;
	
	List<TicketDetails> getListTicketDetails(String data) throws Exception;

}
