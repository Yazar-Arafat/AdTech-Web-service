package com.test.adtech.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Clickseq.COLLECTION_NAME)
public class Clickseq {

	public static final String COLLECTION_NAME = "clicksequences";

	@Id
	private String id;
	private Long sequence;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSequence() {
		return sequence;
	}

	public void setSequence(Long sequence) {
		this.sequence = sequence;
	}
}
