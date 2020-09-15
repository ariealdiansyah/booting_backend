package com.lawencon.booting.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.ReportAllListClient;
import com.lawencon.booting.model.ReportTotalTicketAgent;
import com.lawencon.booting.utility.Hibernate;

@Repository
public class ReportDaoImpl extends BaseDao implements ReportDao {

	@Override
	public List<ReportAllListClient> getReportListAllAgentRelations() throws Exception {
		List<Object[]> aaData = em.createQuery(
				"SELECT ar.idAgent.name as agents_name, ar.idCompany.name as companies_name, startDate, endDate FROM AgentRelations ar",
				Object[].class).getResultList();
		List<ReportAllListClient> listData = new ArrayList<>();
		aaData.forEach(l -> {
			ReportAllListClient data = new ReportAllListClient();
			data.setAgents_name((String) l[0]);
			data.setCompanies_name((String) l[1]);
			data.setStartDate((Date) l[2]);
			data.setEndDate((Date) l[3]);
			listData.add(data);
		});
		return listData;
	}

	@Override
	public List<ReportTotalTicketAgent> getReportTotalTicketAgent(List<String> listCompany) throws Exception {
		StringBuilder query = new StringBuilder();
		query.append("SELECT tkt.idCustomer.name as customers_name, tkt.idCustomer.idCompany.name as companies_name, ");
		query.append("tkt.subject as subject, tkt.idStatus.name as ticket_status, tkt.idClassification.name as classification ");
		query.append("FROM Tickets tkt WHERE tkt.idCustomer.idCompany.id IN :id ");
		List<Object[]> listData = em.createQuery(query.toString(), Object[].class)
				.setParameter("id", listCompany).getResultList();
				
		return Hibernate.bMapperList(listData, ReportTotalTicketAgent.class, "customers_name", "companies_name", "subject", "ticket_status", "classification");
	}

//	@Override
//	public List<ReportTotalTicketAgent> getReportTotalTicketAgent(List<String> listCompany) throws Exception {
//		List<Object[]> aaData = em.createQuery("SELECT tkt.idCustomer.name as customers_name, tkt.idCustomer.idCompany.name as companies_name, count(tkt.id) as total_ticket, count(sts.id) as solved_ticket FROM Tickets tkt LEFT JOIN Status sts ON sts.id = tkt.idStatus AND sts.code = 'CL' WHERE tkt.idCustomer.idCompany.id IN (:listCompany) GROUP BY customers_name, companies_name", Object[].class)
//				.setParameter("listCompany", listCompany)
//				.getResultList();
//		
//		List<ReportTotalTicketAgent> listData = new ArrayList<>();
//		
//		aaData.forEach(l -> {
//			ReportTotalTicketAgent data = new ReportTotalTicketAgent();
//			data.setCustomers_name((String) l[0]);
//			data.setCompanies_name((String) l[1]);
//			data.setTotal_ticket((Long) l[2]);
//			data.setSolved_ticket((Long) l[3]);
//			listData.add(data);
//		});
//		return listData;
//	}
}
