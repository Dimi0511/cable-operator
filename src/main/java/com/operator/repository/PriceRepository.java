package com.operator.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.operator.model.Channel;
import com.operator.model.Price;

public interface PriceRepository extends JpaRepository<Channel, Serializable> {
	
	@Query("select p from Price p where p.supplier.id = ?1 AND p.channel.id = ?2" )
	public Price findChannelPriceBySupplier(Long supplierId, Long channelId); 

}
