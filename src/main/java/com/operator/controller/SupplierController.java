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
import com.operator.model.Supplier;
import com.operator.repository.SupplierRepository;

@RestController
@RequestMapping("/api/v1")
public class SupplierController {

	@Autowired
	private SupplierRepository supplierRepository;

	@GetMapping("/suppliers/read")
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAll();
	}

	@PostMapping("/suppliers/create")
	public Supplier createSupplier(@Valid @RequestBody Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@GetMapping("/suppliers/read/{id}")
	public Supplier getSupplierById(@PathVariable(value = "id") Long supplierId) {
		return supplierRepository.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", supplierId));
	}

	@PutMapping("/suppliers/update/{id}")
	public Supplier updateSupplier(@PathVariable(value = "id") Long supplierId,
			@Valid @RequestBody Supplier supplierDetails) {

		Supplier supplier = supplierRepository.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", supplierId));

		supplier.setName(supplierDetails.getName());
		supplier.setChannels(supplierDetails.getChannels());

		Supplier updatedSupplier = supplierRepository.save(supplier);
		return updatedSupplier;
	}

	@DeleteMapping("/suppliers/delete/{id}")
	public ResponseEntity<?> deleteSupplier(@PathVariable(value = "id") Long supplierId) {
		Supplier supplier = supplierRepository.findById(supplierId)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", supplierId));

		supplierRepository.delete(supplier);

		return ResponseEntity.ok().build();
	}

}
