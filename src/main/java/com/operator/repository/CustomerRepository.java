package com.operator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.operator.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
