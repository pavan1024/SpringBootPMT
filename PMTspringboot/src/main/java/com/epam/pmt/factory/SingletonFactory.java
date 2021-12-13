package com.epam.pmt.factory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.stereotype.Component;
@Component
public class SingletonFactory {
	EntityManagerFactory entityManagerFactory;

	private SingletonFactory() {
	}

	public EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory("pmt-database");
		}
		return entityManagerFactory;
	}

}
