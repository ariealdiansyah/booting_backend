package com.lawencon.booting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lawencon.booting.dao.ProductClientsDao;

@Service
@Transactional
public class ProductClientsServiceImpl implements ProductClientsService{

	@Autowired
	private ProductClientsDao productClientDao;
}
