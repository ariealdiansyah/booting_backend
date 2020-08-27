package com.lawencon.booting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.ClientProductsDao;
import com.lawencon.booting.model.ClientProducts;

@Service
@Transactional
public class ClientProductsServiceImpl extends BaseService implements ClientProductsService {

	@Autowired
	private ClientProductsDao clientProductsDao;

	@Override
	public ClientProducts insert(ClientProducts data) throws Exception {
//		data.setId(getUuid());
		return clientProductsDao.insert(data);
	}

	@Override
	public ClientProducts update(ClientProducts data) throws Exception {
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
		return clientProductsDao.getListByCompany(data);
	}
}
