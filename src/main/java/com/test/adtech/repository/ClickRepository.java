package com.test.adtech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.adtech.entity.Click;

public interface ClickRepository extends MongoRepository<Click, String>{

}
