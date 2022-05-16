package com.operator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.operator.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
