package com.operator.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.operator.model.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Serializable> {

	@Query("select c from Channel c where c.category = ?1")
	public List<Channel> findByCategory(String category);

}
