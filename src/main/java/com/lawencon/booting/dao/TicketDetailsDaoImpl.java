package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.TicketDetails;

@Repository
public class TicketDetailsDaoImpl extends BaseDao implements TicketDetailsDao{

	@Override
	public TicketDetails insert(TicketDetails data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public List<TicketDetails> getListTicketDetails(String data) throws Exception {
		return em.createQuery("FROM TicketDetails WHERE idTicket.code = :code", TicketDetails.class)
				.setParameter("code", data).getResultList();
	}

}
