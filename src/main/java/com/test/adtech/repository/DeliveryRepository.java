package com.test.adtech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.adtech.entity.Delivery;

public interface DeliveryRepository extends MongoRepository<Delivery, String>{
	
//	public void getdevCount(@Param("time") String start, @Param("end") String end);
	
}
