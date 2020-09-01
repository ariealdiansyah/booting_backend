package com.lawencon.booting.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.TicketStatus;
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
		return em.createQuery("FROM Tickets ORDER BY createdAt DESC", Tickets.class).getResultList();
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
		List<Tickets> a = em.createQuery("FROM Tickets WHERE idCustomer.idCompany.id = :id", Tickets.class)
				.setParameter("id", data)
				.getResultList();
		
		return a;
	} 

	@Override
	public List<Tickets> getListByIdAgent(List<String> listData) throws Exception {
		return em.createQuery("FROM Tickets WHERE idCustomer.idCompany.id IN (:id)", Tickets.class)
				.setParameter("id", listData).getResultList();
	}

	@Override
	public TicketStatus selectStatus() throws Exception {
		List<Object[]> obj = em.createQuery("SELECT COUNT(id), idStatus.id FROM Tickets GROUP BY idStatus.id", Object[].class)
				.getResultList();
		TicketStatus ticket = new TicketStatus();
		ticket.setTicketOpen((Long) obj.get(0)[0]);
		ticket.setTicketClose((Long) obj.get(1)[0]);
		ticket.setTicketReopen((Long) obj.get(2)[0]);
		System.out.println(ticket);
		return ticket;
	}

}
