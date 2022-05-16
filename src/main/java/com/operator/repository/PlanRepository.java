package com.operator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.operator.model.Plan;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
