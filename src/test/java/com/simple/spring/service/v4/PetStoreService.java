package com.simple.spring.service.v4;


import com.simple.spring.beans.factory.annotation.Autowired;
import com.simple.spring.dao.v4.AccountDao;
import com.simple.spring.dao.v4.ItemDao;
import com.simple.spring.stereotype.Component;

@Component(value="petStore")
public class PetStoreService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private ItemDao itemDao;
	
	public AccountDao getAccountDao() {
		return accountDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}
	
}