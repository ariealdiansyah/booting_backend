package com.lawencon.booting.service;

import java.util.List;

import com.lawencon.booting.model.ReportAllListClient;
import com.lawencon.booting.model.ReportTotalTicketAgent;
import com.lawencon.booting.model.Users;

public interface ReportService {

	List<ReportAllListClient> getReportListAllAgentRelations() throws Exception;
	
	List<ReportTotalTicketAgent> getReportTotalTicketAgent(Users data) throws Exception;
}
