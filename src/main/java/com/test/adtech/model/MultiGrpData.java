package com.test.adtech.model;

import java.util.List;

public class MultiGrpData {
	
	private Interval interval;
	
	private List<FullListData> data;

	
	/**
	 * @return the interval
	 */
	public Interval getInterval() {
		return interval;
	}

	/**
	 * @param interval the interval to set
	 */
	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	/**
	 * @return the data
	 */
	public List<FullListData> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List<FullListData> data) {
		this.data = data;
	}
	
	

}
