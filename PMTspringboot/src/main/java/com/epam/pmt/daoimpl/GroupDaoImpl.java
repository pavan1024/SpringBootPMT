package com.epam.pmt.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.epam.pmt.business.MasterProvider;
import com.epam.pmt.dao.GroupDao;
import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;
import com.epam.pmt.factory.SingletonFactory;
@Component
@Primary
public class GroupDaoImpl implements GroupDao {
	EntityManagerFactory factory;
	EntityManager manager;
	@Autowired
	SingletonFactory singletonFactory;

	Master master = MasterProvider.getMaster();
	@Override
	public List<Account> displayByGroup(String groupname) {
		List<Account> groupAccounts = null;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		try {
			Query query = manager.createQuery("select u from Account u where u.groupname=?1");
			query.setParameter(1, groupname);
			groupAccounts = query.getResultList();
		} catch (IllegalStateException e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
		return groupAccounts;
	}

	@Override
	public boolean modifyGroup(String groupname, String newGroupname) {
		boolean status = false;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		
		Query query = manager.createQuery("select a from Account a where a.groupname=?1 and a.master=?2");
		query.setParameter(1, groupname);
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();

		try {
			if (!accounts.isEmpty()) {
				accounts.stream().forEach(i -> i.setGroupname(newGroupname));
				master.setAccounts(accounts);
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
	public boolean deleteGroup(String groupname) {
		boolean status = false;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		Query query = manager.createQuery("select a from Account a where a.groupname=?1 and a.master=?2");
		query.setParameter(1, groupname);
		query.setParameter(2, master);
		List<Account> accounts = query.getResultList();
		try {
			if (!accounts.isEmpty()) {
				for (int i = 0; i < accounts.size(); i++) {
					manager.getTransaction().begin();
					manager.remove(accounts.get(i));
					manager.getTransaction().commit();
				}
				status = true;
			}

		} catch (IllegalStateException e) {
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}

		return status;

	}
}
