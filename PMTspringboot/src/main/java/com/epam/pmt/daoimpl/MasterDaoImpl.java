package com.epam.pmt.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.pmt.dao.MasterDao;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.factory.SingletonFactory;

@Component
@Primary
public class MasterDaoImpl implements MasterDao {
	EntityManagerFactory factory;
	EntityManager manager;
	@Autowired
	SingletonFactory singletonFactory;

	public boolean createMaster(Master master) {
		boolean status = false;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
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
}
