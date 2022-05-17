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
import com.operator.model.Plan;
import com.operator.repository.PlanRepository;

@RestController
@RequestMapping("/api/v1")
public class PlanController {

	@Autowired
	private PlanRepository planRepository;

	@GetMapping("/plans/read")
	public List<Plan> getAllPlans() {
		return planRepository.findAll();
	}

	@PostMapping("/plans/create")
	public Plan createPlan(@Valid @RequestBody Plan plan) {
		return planRepository.save(plan);
	}

	@GetMapping("/plans/read/{id}")
	public Plan getPlanById(@PathVariable(value = "id") Long planId) {
		return planRepository.findById(planId).orElseThrow(() -> new ResourceNotFoundException("Plan", "id", planId));
	}

	@PutMapping("/plans/update/{id}")
	public Plan updatePlan(@PathVariable(value = "id") Long planId, @Valid @RequestBody Plan planDetails) {

		Plan plan = planRepository.findById(planId)
				.orElseThrow(() -> new ResourceNotFoundException("Plan", "id", planId));

		plan.setChannels(planDetails.getChannels());

		Plan updatedPlan = planRepository.save(plan);
		return updatedPlan;
	}

	@DeleteMapping("/plans/delete/{id}")
	public ResponseEntity<?> deletePlan(@PathVariable(value = "id") Long planId) {
		Plan plan = planRepository.findById(planId)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier", "id", planId));

		planRepository.delete(plan);

		return ResponseEntity.ok().build();
	}
}
