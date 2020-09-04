package com.lawencon.booting.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.booting.model.TicketCharts;
import com.lawencon.booting.model.TicketStatus;
import com.lawencon.booting.model.Tickets;

@Repository
public class TicketsDaoImpl extends BaseDao implements TicketsDao {

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
		em.createQuery("DELETE from Tickets where id = :id").setParameter("id", id);
	}

	@Override
	public List<Tickets> getListByIdUser(String data) throws Exception {
		return em.createQuery("FROM Tickets WHERE idCustomer.id = :id ORDER BY createdAt DESC", Tickets.class)
				.setParameter("id", data).getResultList();
	}

	@Override
	public List<Tickets> getListByIdCompany(String data) throws Exception {
		List<Tickets> a = em.createQuery("FROM Tickets WHERE idCustomer.idCompany.id = :id ORDER BY createdAt DESC", Tickets.class)
				.setParameter("id", data).getResultList();

		return a;
	}

	@Override
	public List<Tickets> getListByIdAgent(List<String> listData) throws Exception {
		return em.createQuery("FROM Tickets WHERE idCustomer.idCompany.id IN (:id) ORDER BY createdAt DESC", Tickets.class)
				.setParameter("id", listData).getResultList();
	}

	@Override
	public TicketStatus selectStatus() throws Exception {
		List<Object[]> obj = em
				.createQuery("SELECT COUNT(id), idStatus.code FROM Tickets GROUP BY idStatus.code", Object[].class)
				.getResultList();
		TicketStatus ticket = new TicketStatus();
		for (int i = 0; i < obj.size(); i++) {
			if ("OP".equals(obj.get(i)[1])) {
				ticket.setTicketOpen((Long) obj.get(i)[0]);
			} else if ("CL".equals(obj.get(i)[1])) {
				ticket.setTicketClose((Long) obj.get(i)[0]);
			} else {
				ticket.setTicketReopen((Long) obj.get(i)[0]);
			}
		}
//		ticket.setTicketOpen((Long) obj.get(0)[0]);
//		ticket.setTicketClose((Long) obj.get(1)[0]);
//		ticket.setTicketReopen((Long) obj.get(2)[0]);
//		System.out.println(ticket);
		return ticket;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketCharts> getListTicketCharts(Long data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tms.name, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 1 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as January, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 2 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as February, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 3 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as March, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 4 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as April, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 5 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as May, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 6 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as June, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 7 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as July, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 8 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as August, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 9 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as September, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 10 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as October, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 11 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as November, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 12 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) ) as December ");
		sql.append("FROM tb_m_status tms ORDER BY tms.name");

//		@SuppressWarnings("unchecked")
		List<Object[]> listData = em.createNativeQuery(sql.toString())
				.setParameter("year", data).getResultList();
		List<TicketCharts> listCharts = new ArrayList<>();

		listData.forEach(l -> {
			TicketCharts ticket = new TicketCharts();
			ticket.setName((String) l[0]);
			ticket.setJanuary((BigInteger) l[1]);
			ticket.setFebruary((BigInteger) l[2]);
			ticket.setMarch((BigInteger) l[3]);
			ticket.setApril((BigInteger) l[4]);
			ticket.setMay((BigInteger) l[5]);
			ticket.setJune((BigInteger) l[6]);
			ticket.setJuly((BigInteger) l[7]);
			ticket.setAugust((BigInteger) l[8]);
			ticket.setSeptember((BigInteger) l[9]);
			ticket.setOctober((BigInteger) l[10]);
			ticket.setNovember((BigInteger) l[11]);
			ticket.setDecember((BigInteger) l[12]);
			listCharts.add(ticket);
		});
		return listCharts;
	}

	@Override
	public TicketStatus statusAgent(List<String> listData) throws Exception {
		List<Object[]> obj = em.createQuery(
				"SELECT COUNT(id), idStatus.code FROM Tickets WHERE idCustomer.idCompany.id IN (:id) GROUP BY idStatus.code",
				Object[].class).setParameter("id", listData).getResultList();
		TicketStatus ticket = new TicketStatus();
		for (int i = 0; i < obj.size(); i++) {
			if ("OP".equals(obj.get(i)[1])) {
				ticket.setTicketOpen((Long) obj.get(i)[0]);
			} else if ("CL".equals(obj.get(i)[1])) {
				ticket.setTicketClose((Long) obj.get(i)[0]);
			} else {
				ticket.setTicketReopen((Long) obj.get(i)[0]);
			}
		}
		return ticket;
	}

	@Override
	public TicketStatus statusClient(String data) throws Exception {
		List<Object[]> obj = em.createQuery(
				"SELECT COUNT(id), idStatus.code FROM Tickets WHERE idCustomer.idCompany.id = :id GROUP BY idStatus.code",
				Object[].class).setParameter("id", data).getResultList();
		TicketStatus ticket = new TicketStatus();
		for (int i = 0; i < obj.size(); i++) {
			if ("OP".equals(obj.get(i)[1])) {
				ticket.setTicketOpen((Long) obj.get(i)[0]);
			} else if ("CL".equals(obj.get(i)[1])) {
				ticket.setTicketClose((Long) obj.get(i)[0]);
			} else {
				ticket.setTicketReopen((Long) obj.get(i)[0]);
			}
		}
//		ticket.setTicketOpen((Long) obj.get(0)[0]);
//		ticket.setTicketClose((Long) obj.get(1)[0]);
//		ticket.setTicketReopen((Long) obj.get(2)[0]);
		return ticket;
	}

	@Override
	public TicketStatus statusCustomer(String data) throws Exception {
		List<Object[]> obj = em.createQuery(
				"SELECT COUNT(id), idStatus.code FROM Tickets WHERE idCustomer.id = :id GROUP BY idStatus.code",
				Object[].class).setParameter("id", data).getResultList();
		TicketStatus ticket = new TicketStatus();
		for (int i = 0; i < obj.size(); i++) {
			if ("OP".equals(obj.get(i)[1])) {
				ticket.setTicketOpen((Long) obj.get(i)[0]);
			} else if ("CL".equals(obj.get(i)[1])) {
				ticket.setTicketClose((Long) obj.get(i)[0]);
			} else {
				ticket.setTicketReopen((Long) obj.get(i)[0]);
			}
		}
//		ticket.setTicketOpen((Long) obj.get(0)[0]);
//		ticket.setTicketClose((Long) obj.get(1)[0]);
//		ticket.setTicketReopen((Long) obj.get(2)[0]);
		return ticket;
	}

	@Override
	public Tickets getTicketByCode(Tickets data) throws Exception {
		List<Tickets> listData = em.createQuery("FROM Tickets WHERE code = :code", Tickets.class)
				.setParameter("code", data.getCode()).getResultList();
		return !listData.isEmpty() ? listData.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TicketCharts> getChartsByClient(Long data) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tms.name, ");
		sql.append("(select count(trht.id) from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 1 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as January, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 2 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as February, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 3 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as March, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 4 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as April, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 5 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as May, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 6 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as June, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 7 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as July, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 8 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as August, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 9 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year)  and rs.code = 'CLI' ) as September, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 10 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year)  and rs.code = 'CLI' ) as October, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 11 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI') as November, ");
		sql.append("(select count(trht.id)from tb_r_hdr_tickets trht ");
		sql.append("join tb_m_users usr on usr.id = trht.id_customer ");
		sql.append("join tb_m_roles rs on rs.id = usr.id_role ");
		sql.append("right join tb_m_status tms2 ON tms2.id = trht.id_status ");
		sql.append("where extract (month from trht.created_at ) = 12 and tms.id = tms2.id ");
		sql.append("and (extract (year from trht.created_at ) = :year) and rs.code = 'CLI' ) as December ");
		sql.append("FROM tb_m_status tms ORDER BY tms.name");

//		@SuppressWarnings("unchecked")
		List<Object[]> listData = em.createNativeQuery(sql.toString())
				.setParameter("year", data).getResultList();
		List<TicketCharts> listCharts = new ArrayList<>();

		listData.forEach(l -> {
			TicketCharts ticket = new TicketCharts();
			ticket.setName((String) l[0]);
			ticket.setJanuary((BigInteger) l[1]);
			ticket.setFebruary((BigInteger) l[2]);
			ticket.setMarch((BigInteger) l[3]);
			ticket.setApril((BigInteger) l[4]);
			ticket.setMay((BigInteger) l[5]);
			ticket.setJune((BigInteger) l[6]);
			ticket.setJuly((BigInteger) l[7]);
			ticket.setAugust((BigInteger) l[8]);
			ticket.setSeptember((BigInteger) l[9]);
			ticket.setOctober((BigInteger) l[10]);
			ticket.setNovember((BigInteger) l[11]);
			ticket.setDecember((BigInteger) l[12]);
			listCharts.add(ticket);
		});
		return listCharts;
	}

}
