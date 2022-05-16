package com.operator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.operator.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
