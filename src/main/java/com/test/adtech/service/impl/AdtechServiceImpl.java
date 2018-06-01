package com.test.adtech.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.test.adtech.entity.Click;
import com.test.adtech.entity.Clickseq;
import com.test.adtech.entity.Delivery;
import com.test.adtech.entity.Devseq;
import com.test.adtech.entity.Install;
import com.test.adtech.entity.Installseq;
import com.test.adtech.repository.ClickRepository;
import com.test.adtech.repository.DeliveryRepository;
import com.test.adtech.repository.InstallRepository;
import com.test.adtech.service.AdtechService;

@Service
public class AdtechServiceImpl implements AdtechService {

	@Autowired
	DeliveryRepository devRepo;

	@Autowired
	ClickRepository clickRepo;

	@Autowired
	InstallRepository installRepo;

	@Autowired
	private NextSequenceService nextSequenceService;

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public ResponseEntity<Delivery> saveDev(Delivery dev) {
		dev.setId(nextSequenceService.getNextSequenceDevId(Devseq.COLLECTION_NAME));
		this.devRepo.save(dev);
		return ResponseEntity.ok().build();

	}

	@Override
	public ResponseEntity<Click> saveClick(Click dev) {
		dev.setId(nextSequenceService.getNextSequenceClickId(Clickseq.COLLECTION_NAME));
		this.clickRepo.save(dev);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<Install> saveInstall(Install dev) {
		dev.setId(nextSequenceService.getNextSequenceInstallId(Installseq.COLLECTION_NAME));
		this.installRepo.save(dev);
		return ResponseEntity.ok().build();
	}

	@Override
	public Long getDeliveryDateRange(List<String> dates) throws ParseException {

		Query query = getRange(dates);

		return this.mongoOperations.count(query, Delivery.class);

	}

	@Override
	public Long getClicksDateRange(List<String> dates) throws ParseException {

		Query query = getRange(dates);

		return this.mongoOperations.count(query, Click.class);
	}

	@Override
	public Long getInstallDateRange(List<String> dates) throws ParseException {

		Query query = getRange(dates);

		return this.mongoOperations.count(query, Install.class);
	}

	@Override
	public Iterable<DBObject> getCategories(List<String> dates, String browser) throws ParseException {

		MongoClient mongo = new MongoClient();
		DB db = mongo.getDB("adtech");

		DBCollection coll = db.getCollection("delivery");

		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date fdate = outputFormat.parse(dates.get(0));
		Date fdate2 = outputFormat.parse(dates.get(1));
		String start = output.format(fdate);
		String end = output.format(fdate2);
		
		DBObject match = new BasicDBObject("$match",
				new BasicDBObject("time", new BasicDBObject("$gte", start).append("$lt", end)));

		DBObject clickLookup = (DBObject) new BasicDBObject("$lookup", new BasicDBObject("from", "click")
				.append("localField", "deliveryId").append("foreignField", "deliveryId").append("as", "clicks_count"));

		DBObject installLookup = (DBObject) new BasicDBObject("$lookup",
				new BasicDBObject("from", "install").append("localField", "clicks_count.clickId")
						.append("foreignField", "clickId").append("as", "install_count"));

//		DBObject unwind = new BasicDBObject("$unwind", "$clicks_count");

//		DBObject unwind1 = new BasicDBObject("$unwind", "$install_count");

		// build the $group operations
		DBObject groupFields = new BasicDBObject("_id", "$browser");
		groupFields.put("deliveries", new BasicDBObject("$sum", 1));
		groupFields.put("clicks", new BasicDBObject("$sum", new BasicDBObject("$size", "$clicks_count")));
		groupFields.put("installs", new BasicDBObject("$sum", new BasicDBObject("$size", "$install_count")));

		DBObject group = new BasicDBObject("$group", groupFields);
		List<DBObject> pipeline = Arrays.asList(match, installLookup, clickLookup, group);

		AggregationOutput outputvalue = coll.aggregate(pipeline);

		return outputvalue.results();
	}

	@Override
	public Iterable<DBObject> getAllCategories(List<String> dates, String browser, String os) throws ParseException {

		MongoClient mongo = new MongoClient();
		DB db = mongo.getDB("adtech");

		DBCollection coll = db.getCollection("delivery");

		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		Date fdate = outputFormat.parse(dates.get(0));
		Date fdate2 = outputFormat.parse(dates.get(1));
		String start = output.format(fdate);
		String end = output.format(fdate2);

		DBObject match = new BasicDBObject("$match",
				new BasicDBObject("time", new BasicDBObject("$gte", start).append("$lt", end)));

		DBObject clickLookup = (DBObject) new BasicDBObject("$lookup", new BasicDBObject("from", "click")
				.append("localField", "deliveryId").append("foreignField", "deliveryId").append("as", "clicks_count"));

		DBObject installLookup = (DBObject) new BasicDBObject("$lookup",
				new BasicDBObject("from", "install").append("localField", "clicks_count.clickId")
						.append("foreignField", "clickId").append("as", "install_count"));

//		DBObject unwind = new BasicDBObject("$unwind", "$clicks_count");

		DBObject groupFields = new BasicDBObject("_id", new BasicDBObject("browser", "$browser").append("os", "$os"));
		groupFields.put("deliveries", new BasicDBObject("$sum", 1));
		groupFields.put("clicks", new BasicDBObject("$sum", new BasicDBObject("$size", "$clicks_count")));
		groupFields.put("installs", new BasicDBObject("$sum", new BasicDBObject("$size", "$install_count")));

		DBObject group = new BasicDBObject("$group", groupFields);
		List<DBObject> pipeline = Arrays.asList(clickLookup, installLookup, match, group);

		AggregationOutput outputvalue = coll.aggregate(pipeline);

		return outputvalue.results();
	}

	public Query getRange(List<String> dates) throws ParseException {

		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		// TimeZone tz = TimeZone.getTimeZone("UTC");
		// outputFormat.setTimeZone(tz);

		Date fdate = outputFormat.parse(dates.get(0));
		Date fdate2 = outputFormat.parse(dates.get(1));
		String startDate = output.format(fdate);
		String endDate = output.format(fdate2);

		Query query = new Query();
		return query.addCriteria(Criteria.where("time").gte(startDate).lt(endDate));
	}

}
