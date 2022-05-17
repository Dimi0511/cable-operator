package com.operator.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.operator.model.Channel;
import com.operator.model.Contract;
import com.operator.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
//	@Query("select s from Contract c where c.category = ?1")
//	public List<Contract> getAllContracts();

}
