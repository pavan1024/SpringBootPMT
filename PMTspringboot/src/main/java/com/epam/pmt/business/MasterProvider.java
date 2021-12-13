package com.epam.pmt.business;

import org.springframework.stereotype.Component;

import com.epam.pmt.entities.Master;

@Component
public class MasterProvider {
	static Master master = new Master();

	public static void setMaster(String userName, String password) {
		master.setUsername(userName);
		master.setPassword(password);
	}

	public static Master getMaster() {
		return master;
	}

}
