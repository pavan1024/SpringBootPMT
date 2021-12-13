package com.epam.pmt.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.pmt.entities.Master;
import com.epam.pmt.factory.SingletonFactory;

@Component
public class Login {
	@Autowired
	MasterUserService masterUserService;
	@Autowired
	SingletonFactory singletonFactory;
	EntityManagerFactory factory;
	EntityManager manager;

	public boolean login(String userName, String password) {
		boolean status = false;
		factory = singletonFactory.getEntityManagerFactory();
		manager = factory.createEntityManager();
		if (password.equals(manager.find(Master.class, userName).getPassword())) {
			MasterProvider.setMaster(userName, password);
			status = true;
		}
		return status;
	}

	public boolean checkIfUserNameExists(String userName) {
		return masterUserService.checkIfMasterExists(userName);
	}

}
