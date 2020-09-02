package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.TicketCharts;
import com.lawencon.booting.model.TicketDetails;
import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Tickets;
import com.lawencon.booting.model.Users;

public interface TicketsService {

	Tickets insert(Tickets data) throws Exception;

	Tickets insert(TicketDetails data) throws Exception;

	Tickets update(Tickets data) throws Exception;

	List<Tickets> getListTickets() throws Exception;
	
	TicketStatus selectStatus() throws Exception;

	void delete(String id) throws Exception; 

	List<Tickets> getListByIdUser(Users data) throws Exception;

	List<Tickets> getListByIdCompany(Companies data) throws Exception;

	List<Tickets> getListByIdAgent(Users data) throws Exception;
	
	List<TicketCharts> getListTicketCharts(TicketCharts data) throws Exception;
}
