package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.TicketDetails;

public interface TicketDetailsDao {

	TicketDetails insert(TicketDetails data) throws Exception;
	
	List<TicketDetails> getListTicketDetails(String data) throws Exception;
}
