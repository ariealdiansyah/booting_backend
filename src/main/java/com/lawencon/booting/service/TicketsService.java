package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.TicketDetails;
import com.lawencon.booting.model.Tickets;

public interface TicketsService {

	Tickets insert(Tickets data) throws Exception;

	Tickets insert(TicketDetails data) throws Exception;

	Tickets update(Tickets data) throws Exception;

	List<Tickets> getListTickets() throws Exception;

	void delete(String id) throws Exception; 

	List<Tickets> getListByIdUser(String data) throws Exception;

	List<Tickets> getListByIdCompany(String data) throws Exception;

	List<Tickets> getListByIdAgent(String data) throws Exception;
}
