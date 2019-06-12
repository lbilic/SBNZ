package com.siem.siem.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.siem.siem.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
	List<Account> findAll();
	Account findByUsername(String username);
}
