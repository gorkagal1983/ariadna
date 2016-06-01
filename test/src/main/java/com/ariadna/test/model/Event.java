package com.ariadna.test.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Event implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2033828966266084398L;
	
	@Id
	private Long event_id;
	private Source source;
	private Date timestamp;
	private Long value;

	public Event() {

	}

	public Event(Long event_id, Source source, Date timestamp, Long value) {
		this.event_id = event_id;
		this.source = source;
		this.timestamp = timestamp;
		this.value = value;
	}

	/**
	 * @return the event_id
	 */
	public Long getEvent_id() {
		return event_id;
	}

	/**
	 * @param event_id
	 *            the event_id to set
	 */
	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}

	/**
	 * @return the source
	 */
	public Source getSource() {
		return source;
	}

	/**
	 * @param source
	 *            the source to set
	 */
	public void setSource(Source source) {
		this.source = source;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the value
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Long value) {
		this.value = value;
	}

}
