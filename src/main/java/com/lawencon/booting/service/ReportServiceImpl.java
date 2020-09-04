package com.lawencon.booting.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.ReportDao;
import com.lawencon.booting.model.ReportAllListClient;
import com.lawencon.booting.model.ReportTotalTicketAgent;
import com.lawencon.booting.model.Users;

@Service
@Transactional
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private AgentRelationsService agentService;
	
	@Override
	public List<ReportAllListClient> getReportListAllAgentRelations() throws Exception {
		return reportDao.getReportListAllAgentRelations();
	}

	@Override
	public List<ReportTotalTicketAgent> getReportTotalTicketAgent(Users data) throws Exception {
		List<String> listCompany = agentService.getListCompanies(data);
		return reportDao.getReportTotalTicketAgent(listCompany);
	}
}
