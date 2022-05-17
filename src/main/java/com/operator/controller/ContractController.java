package com.operator.controller;

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
import com.operator.repository.ContractRepository;

@RestController
@RequestMapping("/api/v1")
public class ContractController {
	
	@Autowired
	private ContractRepository contractRepository;
	
    @GetMapping("/contracts/read")
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }
    
    @PostMapping("/contracts/create")
    public Contract createContract(@Valid @RequestBody Contract contract) {
        return contractRepository.save(contract);
    }
    
    @GetMapping("/contracts/read/{id}")
    public Contract getContractById(@PathVariable(value = "id") Long contractId) {
        return contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", contractId));
    }
    
    @PutMapping("/contracts/update/{id}")
    public Contract updateContract(@PathVariable(value = "id") Long contractId,
                                           @Valid @RequestBody Contract contractDetails) {

        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", contractId));

        contract.setContractNumber(contractDetails.getContractNumber());

        Contract updatedContract = contractRepository.save(contract);
        return updatedContract;
    }
    
    @DeleteMapping("/contracts/delete/{id}")
    public ResponseEntity<?> deleteContract(@PathVariable(value = "id") Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFoundException("Contract", "id", contractId));

        contractRepository.delete(contract);

        return ResponseEntity.ok().build();
    }
    
    


}
