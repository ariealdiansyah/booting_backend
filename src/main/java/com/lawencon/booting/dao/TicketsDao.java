package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.Companies;
import com.lawencon.booting.model.TicketCharts;
import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Tickets;

public interface TicketsDao {

	Tickets insert(Tickets data) throws Exception;

	Tickets update(Tickets data) throws Exception;
	
	List<Tickets> getListTickets() throws Exception;
	
	Tickets getTicketByCode(Tickets data) throws Exception;
	
	List<?> getListRelations(String data) throws Exception;
	
	TicketStatus selectStatus() throws Exception;
	
	TicketStatus statusAgent(List<String> data) throws Exception;
	
	TicketStatus statusClient(String data) throws Exception;
	
	TicketStatus statusCustomer(String data) throws Exception;

	void delete(String id) throws Exception;

	List<Tickets> getListByIdUser(String data) throws Exception;

	List<Tickets> getListByIdCompany(String data) throws Exception;

	List<Tickets> getListByIdAgent(List<String> data) throws Exception;
	
	List<TicketCharts> getListTicketCharts(Long data) throws Exception;
	
	List<TicketCharts> getChartsByClient(Companies data) throws Exception;
	
	List<TicketCharts> getChartsByAgent(List<String> data) throws Exception;
	
}
