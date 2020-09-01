package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Tickets;

public interface TicketsDao {

	Tickets insert(Tickets data) throws Exception;

	Tickets update(Tickets data) throws Exception;

	List<Tickets> getListTickets() throws Exception;
	
	TicketStatus selectStatus() throws Exception;

	void delete(String id) throws Exception;

	List<Tickets> getListByIdUser(String data) throws Exception;

	List<Tickets> getListByIdCompany(String data) throws Exception;

	List<Tickets> getListByIdAgent(List<String> data) throws Exception;
}
