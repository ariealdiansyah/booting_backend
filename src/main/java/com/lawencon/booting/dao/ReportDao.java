package com.lawencon.booting.dao;

import java.util.List;

import com.lawencon.booting.model.ReportAllListClient;
import com.lawencon.booting.model.ReportTotalTicketAgent;

public interface ReportDao {

	List<ReportAllListClient> getReportListAllAgentRelations() throws Exception;
	
	List<ReportTotalTicketAgent> getReportTotalTicketAgent(List<String> listCompany) throws Exception;
}
