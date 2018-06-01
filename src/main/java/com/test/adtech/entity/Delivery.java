package com.test.adtech.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Delivery.COLLECTION_NAME)
public class Delivery implements Serializable {
	
	public static final String COLLECTION_NAME = "delivery";
	
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	private int advertisementId;
	private String deliveryId;
	
//	@DateTimeFormat(iso=ISO.DATE_TIME)
	private String time;
	
	private String browser;
	private String os;
	private String site;

	public Delivery() {
	}

	public static class DeliveryBuilder {
		
		private int id;
		private int advertisementId;
		private String deliveryId;
		private String time;
		private String browser;
		private String os;
		private String site;
		
		
		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the advertisementId
		 */
		public int getAdvertisementId() {
			return advertisementId;
		}

		/**
		 * @param advertisementId the advertisementId to set
		 */
		public void setAdvertisementId(int advertisementId) {
			this.advertisementId = advertisementId;
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

		public String getDeliveryId() {
			return deliveryId;
		}

		public void setDeliveryId(String deliveryId) {
			this.deliveryId = deliveryId;
		}


		public String getBrowser() {
			return browser;
		}

		public void setBrowser(String browser) {
			this.browser = browser;
		}

		public String getOs() {
			return os;
		}

		public void setOs(String os) {
			this.os = os;
		}

		public String getSite() {
			return site;
		}

		public void setSite(String site) {
			this.site = site;
		}

		public Delivery build() {
			Delivery dev = new Delivery();
			dev.id=this.id;
			dev.deliveryId = this.deliveryId;
			dev.advertisementId = this.advertisementId;
			dev.time = this.time;
			dev.browser = this.browser;
			dev.os = this.os;
			dev.site = this.site;

			return dev;
		}

	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the advertisementId
	 */
	public int getAdvertisementId() {
		return advertisementId;
	}

	/**
	 * @param advertisementId the advertisementId to set
	 */
	public void setAdvertisementId(int advertisementId) {
		this.advertisementId = advertisementId;
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
	 * @return the browser
	 */
	public String getBrowser() {
		return browser;
	}

	/**
	 * @param browser
	 *            the browser to set
	 */
	public void setBrowser(String browser) {
		this.browser = browser;
	}

	/**
	 * @return the os
	 */
	public String getOs() {
		return os;
	}

	/**
	 * @param os
	 *            the os to set
	 */
	public void setOs(String os) {
		this.os = os;
	}

	/**
	 * @return the site
	 */
	public String getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(String site) {
		this.site = site;
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
