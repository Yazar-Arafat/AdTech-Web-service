package com.test.adtech.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.test.adtech.entity.Install;

public interface InstallRepository extends MongoRepository<Install, String> {

}
