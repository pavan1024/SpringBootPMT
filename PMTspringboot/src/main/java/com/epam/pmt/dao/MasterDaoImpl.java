package com.epam.pmt.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.factory.SingletonFactory;

@Component
public class MasterDaoImpl {
	@Autowired
	SingletonFactory singletonFactory;
	EntityManagerFactory factory;
	EntityManager manager;

public boolean createMaster(String userName, String password) {
	boolean status = false;
	factory = singletonFactory.getEntityManagerFactory();
	manager = factory.createEntityManager();
	Master master = new Master();
	master.setUsername(userName);
	master.setPassword(password);
	List<Account> accounts = new ArrayList<>();
	master.setAccounts(accounts);

	try {
		manager.getTransaction().begin();
		manager.merge(master);
		manager.getTransaction().commit();
		status = true;
	} catch (IllegalStateException e) {
			manager.getTransaction().rollback();
	} finally {
			manager.close();
	}

	return status;

}

public boolean checkIfMasterExists(String userName) {
	boolean status = false;
	factory = singletonFactory.getEntityManagerFactory();
	manager = factory.createEntityManager();
	try {
		Master account = manager.find(Master.class, userName);
		if (account.getUsername().equals(userName)) {
			status = true;
		}
	} catch (Exception e) {
		e.getStackTrace();
	}
	return status;
}
}
