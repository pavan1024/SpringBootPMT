package com.epam.pmt.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.pmt.business.MasterProvider;
import com.epam.pmt.dao.AccountDao;
import com.epam.pmt.entities.*;
import com.epam.pmt.factory.SingletonFactory;

@Component
@Primary
public class AccountDaoImpl implements AccountDao{
	EntityManagerFactory factory;
	EntityManager manager;
	@Autowired
	SingletonFactory singletonFactory;
	Master master = MasterProvider.getMaster();

	String jpqlQuery= "select a from Account a where a.url=?1 and a.master=?2";
	
	
	@Override
	public boolean createAccount(Account account) {
		boolean status = false;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		List<Account> list = manager.find(Master.class, master.getUsername()).getAccounts();
		list.add(account);
		master.setAccounts(list);
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
	
	@Override
	public String readPassword(Account account) {
		String password="";
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		Query query = manager.createQuery(jpqlQuery);
		query.setParameter(1, account.getUrl());
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();
		try {
			password = accounts.get(0).getPassword();
		} catch (IndexOutOfBoundsException | IllegalStateException ex) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return password;
	}

	@Override
	public boolean deleteAccount(Account account) {
		boolean status = false;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		
		Query query = manager.createQuery(jpqlQuery);
		query.setParameter(1, account.getUrl());
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();
		try {
				manager.getTransaction().begin();
				manager.remove(accounts.get(0));
				manager.getTransaction().commit();
				status = true;
		} catch (IllegalStateException e) {
			manager.getTransaction().rollback();

		} finally {

			manager.close();
		}
		return status;

	}


	@Override
	public boolean updateAccount(Account account) {
		boolean status = false;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		Query query = manager.createQuery(jpqlQuery);
		query.setParameter(1, account.getUrl());
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();
		
		try {
		if (!accounts.isEmpty()) {
			manager.getTransaction().begin();
			manager.merge(master);
			manager.getTransaction().commit();
			status = true;
		}
		} catch (IllegalStateException e) {
			manager.getTransaction().rollback();

		} finally {

			manager.close();
		}
		return status;
	}
	
	
	@Override
	public List<Account> getAll(){
		List<Account> accounts=null;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		Query query = manager.createQuery("select a from Account a where a.master=?1");
		query.setParameter(1, master);
		accounts = query.getResultList();
		manager.close();
		return accounts;
	}


}
