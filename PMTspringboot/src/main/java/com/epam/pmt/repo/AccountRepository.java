package com.epam.pmt.repo;


import org.springframework.data.repository.CrudRepository;


import com.epam.pmt.entities.Account;

public interface AccountRepository extends CrudRepository<Account,String>{

}
