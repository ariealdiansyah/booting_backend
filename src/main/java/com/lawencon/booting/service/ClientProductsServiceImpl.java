package com.lawencon.booting.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.ClientProductsDao;
import com.lawencon.booting.model.ClientProducts;
import com.lawencon.booting.model.Companies;

@Service
@Transactional
public class ClientProductsServiceImpl extends BaseService implements ClientProductsService {

	@Autowired
	private ClientProductsDao clientProductsDao;
	
	@Autowired
	private CompaniesService companiesService;

	@Override
	public ClientProducts insert(ClientProducts data) throws Exception {
//		data.setId(getUuid());
		data.setCreatedAt(new Date());
		data.setTicketUrgent(3);
		data.setTicketMedium(7);
		return clientProductsDao.insert(data);
	}

	@Override
	public ClientProducts update(ClientProducts data) throws Exception {
		data.setUpdatedAt(new Date());
		return clientProductsDao.update(data);
	}

	@Override
	public List<ClientProducts> getListClientProducts() throws Exception {
		return clientProductsDao.getListClientProducts();
	}

	@Override
	public void delete(String id) throws Exception {
		clientProductsDao.delete(id);
	}

	@Override
	public List<ClientProducts> getListByCompany(ClientProducts data) throws Exception {
		Companies comp = companiesService.getCompanyByName(data.getIdCompany());
		data.setIdCompany(comp);
		return clientProductsDao.getListByCompany(data);
	}

	@Override
	public List<String> getListIdCompany(Companies data) throws Exception {
		return clientProductsDao.getListIdCompany(data);
	}

	@Override
	public List<ClientProducts> getListClientProductsActive() throws Exception {
		return clientProductsDao.getListClientProductsActive();
	}
}
