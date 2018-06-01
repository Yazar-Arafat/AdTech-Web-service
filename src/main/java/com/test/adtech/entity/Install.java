package com.test.adtech.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Install.COLLECTION_NAME)
public class Install implements Serializable {

	public static final String COLLECTION_NAME = "install";

	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private String installId;
	private String clickId;
	
//	@DateTimeFormat(iso=ISO.DATE_TIME)
	private String time;

	public Install() {
	}

	public Install(int id, String installId, String clickId, String time) {

		this.id = id;
		this.installId = installId;
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
	 * @return the installId
	 */
	public String getInstallId() {
		return installId;
	}

	/**
	 * @param installId
	 *            the installId to set
	 */
	public void setInstallId(String installId) {
		this.installId = installId;
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
