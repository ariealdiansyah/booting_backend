package com.lawencon.booting.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.booting.dao.ReportDao;
import com.lawencon.booting.model.Companies;
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
	
	@Autowired
	private CompaniesService companiesService;
	
	@Override
	public List<ReportAllListClient> getReportListAllAgentRelations() throws Exception {
		return reportDao.getReportListAllAgentRelations();
	}

	@Override
	public List<ReportTotalTicketAgent> getReportTotalTicketAgent(Users data) throws Exception {
		List<String> listCompany = agentService.getListCompanies(data);
		return reportDao.getReportTotalTicketAgent(listCompany);
	}

	@Override
	public List<ReportTotalTicketAgent> getReportTicketCompany(Companies comp) throws Exception {
		comp = companiesService.getCompanyByCode(comp);
		List<String> company = new ArrayList<>();
		company.add(comp.getId());
		return reportDao.getReportTotalTicketAgent(company);
	}
}
