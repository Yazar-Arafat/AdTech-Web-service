package com.test.adtech.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Click.COLLECTION_NAME)
public class Click implements Serializable {

	public static final String COLLECTION_NAME = "click";

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String deliveryId;
	private String clickId;
	
//	@DateTimeFormat(iso=ISO.DATE_TIME)
	private String time;

	public Click() {
	}

	public Click(int id, String deliveryId, String clickId, String time) {

		this.id = id;
		this.deliveryId = deliveryId;
		this.clickId = clickId;
		this.time = time;

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the deliveryId
	 */
	public String getDeliveryId() {
		return deliveryId;
	}

	/**
	 * @param deliveryId
	 *            the deliveryId to set
	 */
	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	/**
	 * @return the clickId
	 */
	public String getClickId() {
		return clickId;
	}

	/**
	 * @param clickId
	 *            the clickId to set
	 */
	public void setClickId(String clickId) {
		this.clickId = clickId;
	}

	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	
}
