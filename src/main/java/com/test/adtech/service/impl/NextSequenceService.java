package com.test.adtech.service.impl;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.test.adtech.entity.Clickseq;
import com.test.adtech.entity.Devseq;
import com.test.adtech.entity.Installseq;

@Service
public class NextSequenceService {

	@Autowired
	private MongoOperations mongoOperations;

	public int getNextSequenceDevId(String key) {

		Devseq sequence = mongoOperations.findAndModify(query(where("_id").is(key)), new Update().inc("sequence", 1),
				options().returnNew(true).upsert(true), Devseq.class);
		return sequence.getSequence().intValue();
	}
	
	public int getNextSequenceClickId(String key) {

		Clickseq sequence = mongoOperations.findAndModify(query(where("_id").is(key)), new Update().inc("sequence", 1),
				options().returnNew(true).upsert(true), Clickseq.class);
		return sequence.getSequence().intValue();
	}
	
	public int getNextSequenceInstallId(String key) {

		Installseq sequence = mongoOperations.findAndModify(query(where("_id").is(key)), new Update().inc("sequence", 1),
				options().returnNew(true).upsert(true), Installseq.class);
		return sequence.getSequence().intValue();
	}
}
