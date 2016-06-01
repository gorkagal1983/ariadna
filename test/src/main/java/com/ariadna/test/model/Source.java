package com.ariadna.test.model;

import org.springframework.data.annotation.Id;

public class Source implements java.io.Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8121614405831894564L;
	
	@Id
	private Long source_id;
	private String name;

	public Source() {

	}
	
	public Source(Long source_id) {
		this.source_id = source_id;
	}

	public Source(Long source_id, String name) {
		this.source_id = source_id;
		this.name = name;
	}

	/**
	 * @return the source_id
	 */
	public Long getSource_id() {
		return source_id;
	}

	/**
	 * @param source_id
	 *            the source_id to set
	 */
	public void setSource_id(Long source_id) {
		this.source_id = source_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
