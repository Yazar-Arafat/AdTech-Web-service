package com.test.adtech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.DBObject;
import com.test.adtech.entity.Click;
import com.test.adtech.entity.Delivery;
import com.test.adtech.entity.Install;
import com.test.adtech.model.Allfields;
import com.test.adtech.model.Fields;
import com.test.adtech.model.FullListData;
import com.test.adtech.model.Interval;
import com.test.adtech.model.ListData;
import com.test.adtech.model.MultiGrpData;
import com.test.adtech.model.Range;
import com.test.adtech.model.SingleGrpData;
import com.test.adtech.model.Stats;
import com.test.adtech.service.AdtechService;

@RestController
@RequestMapping("/ads")
public class AdtechController {

	@Autowired
	private AdtechService adtechService;

	@RequestMapping(value = "/delivery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Delivery> addDelivery(@RequestBody Delivery delivery) {
		return this.adtechService.saveDev(delivery);
	}

	@RequestMapping(value = "/click", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Click> addClicks(@RequestBody Click click) {
		return this.adtechService.saveClick(click);
	}

	@RequestMapping(value = "/install", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Install> addInstalls(@RequestBody Install install) {
		return this.adtechService.saveInstall(install);
	}

	// GET Method
	@RequestMapping(value = "/statistics", params = { "start", "end"}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Range> getIntervalHits(@RequestParam("start") String startDate,
			@RequestParam("end") String endDate) throws ParseException {
		List<String> dates = new ArrayList<String>();
		Range range = new Range();
		dates.add(startDate);
		dates.add(endDate);

		Interval inter = new Interval();
		inter.setStart(startDate);
		inter.setEnd(endDate);

		Stats stats = new Stats();
		stats.setDeliveries(adtechService.getDeliveryDateRange(dates));
		stats.setClicks(adtechService.getClicksDateRange(dates));
		stats.setInstalls(adtechService.getInstallDateRange(dates));

		range.setInterval(inter);
		range.setStats(stats);

		return new ResponseEntity<Range>(range, HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/statistics", params = { "start", "end","group_by"}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	private SingleGrpData geCategories(@RequestParam("start") String startDate, @RequestParam("end") String endDate , @RequestParam("group_by") String browser) throws ParseException {
		List<String> dates = new ArrayList<String>();
		dates.add(startDate);
		dates.add(endDate);
		
		Interval inter = new Interval();
		inter.setStart(startDate);
		inter.setEnd(endDate);
		
		List<ListData> lidata = new ArrayList<>();
		SingleGrpData grpdata = new SingleGrpData();;
		Iterable<DBObject>  dev = adtechService.getCategories(dates, browser);
		
		for(DBObject output : dev) {
			Stats stats = new Stats();
			Fields fields =  new Fields();
			ListData data = new ListData();
			
			fields.setBrowser(output.get("_id").toString());
			stats.setDeliveries(Long.parseLong(output.get("deliveries").toString()));
			stats.setClicks(Long.parseLong(output.get("clicks").toString()));
    		stats.setInstalls(Long.parseLong(output.get("installs").toString()));
		
    		data.setFields(fields);
    		data.setStats(stats);
    		lidata.add(data);
				
		}
		
		grpdata.setData(lidata);
		grpdata.setInterval(inter);
		
		return grpdata;
		
	}
	
	@RequestMapping(value = "/statisticss",params = { "start", "end","group_by"}, method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	private MultiGrpData getFullDetails(@RequestParam("start") String startDate, @RequestParam("end") String endDate , @RequestParam("group_by") String browser, @RequestParam("group_by") String os) throws ParseException {
		
//		String browser = params.get(0);
//		String os = params.get(1);
		
		List<String> dates = new ArrayList<String>();
		dates.add(startDate);
		dates.add(endDate);
		
		Interval inter = new Interval();
		inter.setStart(startDate);
		inter.setEnd(endDate);
		
		List<FullListData> lidata = new ArrayList<>();
		MultiGrpData grpdata = new MultiGrpData();
		Iterable<DBObject>  dev = adtechService.getAllCategories(dates, browser, os);
		
		for(DBObject output : dev) {
			Stats stats = new Stats();
			Allfields fields =  new Allfields();
			FullListData data = new FullListData();
			
			Object obj = output.get("_id");
			if(obj instanceof  Map) {
				Map mape = (Map)obj; 
				String br = (String)mape.get("browser");
				String osv = (String)mape.get("os");
				fields.setBrowser(br);
				fields.setOs(osv);		
			}
			
			stats.setDeliveries(Long.parseLong(output.get("deliveries").toString()));
			stats.setClicks(Long.parseLong(output.get("clicks").toString()));
    		stats.setInstalls(Long.parseLong(output.get("installs").toString()));
		
    		data.setFields(fields);
    		data.setStats(stats);
    		lidata.add(data);
				
		}
		
		grpdata.setData(lidata);
		grpdata.setInterval(inter);
		
		return grpdata;
		
	}
	

}
