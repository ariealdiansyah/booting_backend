package com.lawencon.booting.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.ReportAllListClient;
import com.lawencon.booting.model.ReportTotalTicketAgent;

@Repository
public class ReportDaoImpl extends BaseDao implements ReportDao {

	@Override
	public List<ReportAllListClient> getReportListAllAgentRelations() throws Exception {
		List<Object[]> aaData = em.createQuery("SELECT ar.idAgent.name as agents_name, ar.idCompany.name as companies_name, startDate, endDate FROM AgentRelations ar", Object[].class)
				.getResultList(); 
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
		List<Object[]> aaData = em.createQuery("SELECT tkt.idCustomer.name as customers_name, tkt.idCustomer.idCompany.name as companies_name, count(tkt.id) as total_ticket FROM Tickets tkt WHERE tkt.idCustomer.idCompany.id IN (:listCompany) GROUP BY customers_name, companies_name", Object[].class)
				.setParameter("listCompany", listCompany)
				.getResultList();
		
		List<Object[]> aaaData = em.createQuery("SELECT tkt.idCustomer.name as customers_name, tkt.idCustomer.idCompany.name as companies_name, count(tkt.id) as total_ticket FROM Tickets tkt WHERE tkt.idCustomer.idCompany.id IN (:listCompany) AND tkt.idStatus.code = 'CL' GROUP BY customers_name, companies_name", Object[].class)
				.setParameter("listCompany", listCompany)
				.getResultList();
		
		List<ReportTotalTicketAgent> listData = new ArrayList<>();
		List<ReportTotalTicketAgent> listDataa = new ArrayList<>();
		aaData.forEach(l -> {
			ReportTotalTicketAgent data = new ReportTotalTicketAgent();
			data.setCustomers_name((String) l[0]);
			data.setCompanies_name((String) l[1]);
			data.setTotal_ticket((Long) l[2]);
			listData.add(data);
		});
		aaaData.forEach(l -> {
			ReportTotalTicketAgent data = new ReportTotalTicketAgent();
			data.setSolved_ticket((Long) l[2]);
			listDataa.add(data);
		});
		for(int i = 0; i < listDataa.size(); i++) {
//			ReportTotalTicketAgent data = new ReportTotalTicketAgent();
//			data = listData.get(i);
//			data.setSolved_ticket(listDataa.get(i).getSolved_ticket());
			listData.get(i).setSolved_ticket(listDataa.get(i).getSolved_ticket());
		}
			
		return listData;
	}
}
