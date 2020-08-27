package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.Tickets;

@Repository
public class TicketsDaoImpl extends BaseDao implements TicketsDao{

	@Override
	public Tickets insert(Tickets data) throws Exception {
		em.persist(data);
		return data;
	}

	@Override
	public Tickets update(Tickets data) throws Exception {
		return em.merge(data);
	}

	@Override
	public List<Tickets> getListTickets() throws Exception {
		return em.createQuery("FROM Tickets", Tickets.class).getResultList();
	}

	@Override
	public void delete(String id) throws Exception {
		em.createQuery("DELETE from Tickets where id = :id")
		.setParameter("id", id);
	}

	@Override
	public List<Tickets> getListByIdUser(String data) throws Exception {
		return em.createQuery("FROM Tickets WHERE idCustomer.id = :id", Tickets.class)
				.setParameter("id", data)
				.getResultList();
	}

	@Override
	public List<Tickets> getListByIdCompany(String data) throws Exception {
		return em.createQuery("FROM Tickets WHERE idCustomer.idCompany.id = :id", Tickets.class)
				.setParameter("id", data)
				.getResultList();
	} 

	@Override
	public List<Tickets> getListByIdAgent(String data) throws Exception {
		return em.createQuery("FROM Tickets WHERE idCustomer.idCompany.id in :id", Tickets.class)
				.setParameter("id", data)
				.getResultList();
	}

}
