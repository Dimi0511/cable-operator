package com.operator.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.operator.exception.ResourceNotFoundException;
import com.operator.model.Contract;
import com.operator.model.Customer;
import com.operator.model.Payment;
import com.operator.repository.CustomerRepository;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
	
     @Autowired
     CustomerRepository customerRepository;
     
     @GetMapping("/customers/read")
     public List<Customer> getAllCustomers() {
         return customerRepository.findAll();
     }
     
     
     @PostMapping("/customers/create")
     public Customer createCustomer(@Valid @RequestBody Customer customer) {
         return customerRepository.save(customer);
     }
     
     @GetMapping("/customers/read/{id}")
     public Customer getCustomertById(@PathVariable(value = "id") Long customerId) {
         return customerRepository.findById(customerId)
                 .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
     }
     
     @GetMapping("/customers/read/{id}/payments")
     public List<Payment> getAllPayments(@PathVariable(value = "id") Long customerId) {
    	 Customer customer = customerRepository.getById(customerId);
    	 List<Contract> contractList = customer.getContractList();
    	 List<Payment> payments = new ArrayList<Payment>();
    	 for (Contract contract : contractList) {
    		 payments.addAll(contract.getPaymentList());
    	 }
    	 return payments; 
     }
     
     @GetMapping("/customers/read/{id}/contracts")
     public List<Contract> getAllContractsByCustomertById(@PathVariable(value = "id") Long customerId) {
         Customer customer = customerRepository.findById(customerId)
                 .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
         return customer.getContractList();
     }
     
     @PutMapping("/customers/update/{id}")
     public Customer updateCustomer(@PathVariable(value = "id") Long customerId,
                                            @Valid @RequestBody Customer customerDetails) {

         Customer customer = customerRepository.findById(customerId)
                 .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));
         customer.setContractList(customerDetails.getContractList());
         customer.setEmail(customerDetails.getEmail());
         customer.setFirstName(customerDetails.getFirstName());
         customer.setLastName(customerDetails.getLastName());
         customer.setPIN(customerDetails.getPIN());


         Customer updatedCustomer = customerRepository.save(customer);
         return updatedCustomer;
     }
     
     @DeleteMapping("/customers/delete/{id}")
     public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long customerId) {
         Customer customer = customerRepository.findById(customerId)
                 .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", customerId));

         customerRepository.delete(customer);

         return ResponseEntity.ok().build();
     }
     
}
