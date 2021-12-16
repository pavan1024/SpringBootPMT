package com.epam.pmt.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;

public interface AccountRepository extends CrudRepository<Account, String> {
	public Account findByUrlAndMaster(String url, Master master);

	public List<Account> findByMaster(Master master);
	
}
