package com.epam.pmt.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.epam.pmt.entities.Account;
import com.epam.pmt.entities.Master;

public interface AccountRepository extends CrudRepository<Account, String> {
	public Account findByUrlAndMaster(String url, Master master);

	public List<Account> findByMaster(Master master);

	@Query("SELECT acc FROM Account acc WHERE acc.groupname = ?1 AND acc.master = ?2")
	public List<Account> findByGroupnameAndMaster(String groupname, Master master);

}
