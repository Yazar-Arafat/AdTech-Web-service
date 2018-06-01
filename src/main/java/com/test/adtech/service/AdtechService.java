package com.test.adtech.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.mongodb.DBObject;
import com.test.adtech.entity.Click;
import com.test.adtech.entity.Delivery;
import com.test.adtech.entity.Install;


public interface AdtechService {
	
	public ResponseEntity<Delivery> saveDev(Delivery dev);
	public ResponseEntity<Click> saveClick(Click dev);
	public ResponseEntity<Install> saveInstall(Install dev);
	public Long getDeliveryDateRange(List<String> dates) throws ParseException;
	public Long getClicksDateRange(List<String> dates) throws ParseException;
	public Long getInstallDateRange(List<String> dates) throws ParseException;
	public Iterable<DBObject> getCategories(List<String> dates, String browser) throws ParseException;
	public Iterable<DBObject> getAllCategories(List<String> dates, String browser,String os) throws ParseException;

}
