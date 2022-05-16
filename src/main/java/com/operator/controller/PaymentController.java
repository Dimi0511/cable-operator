package com.operator.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operator.exception.PaymentNotValidException;
import com.operator.model.Payment;
import com.operator.repository.PaymentRepository; 

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@PostMapping("/payments")
	public Payment createPayment(@Valid @RequestBody Payment payment) {
		BigDecimal contractFee = payment.getFee();
		if(payment.getFee().compareTo(contractFee) <= -1) {
			throw new PaymentNotValidException("Payment", "id", payment.getFee());
		} else {
			return paymentRepository.save(payment); 
		}
	}

}
