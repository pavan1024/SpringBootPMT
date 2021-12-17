package com.epam.pmt.util;

import org.springframework.stereotype.Component;

import com.epam.pmt.entities.Master;

@Component
public class MasterProvider {
	static Master master = new Master();

	public static void setMaster(String username, String password) {
		master.setUsername(username);
		master.setPassword(password);
	}

	public static Master getMaster() {
		return master;
	}

}
