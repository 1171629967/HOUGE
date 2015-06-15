/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
/**
 * 
 */
package com.ysp.houge.entity;

/**
 * @描述:
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月8日下午7:18:25
 * @version 1.0
 */
public class EditChooseViewDescriptor {
	private String leftText;
	private String hintText;

	public EditChooseViewDescriptor(String hintText) {
		super();
		this.hintText = hintText;
	}

	/**
	 * @描述
	 * @param leftText
	 * @param hintText
	 */
	public EditChooseViewDescriptor(String hintText, String leftText) {
		super();
		this.leftText = leftText;
		this.hintText = hintText;
	}

	/**
	 * @return the leftText
	 */
	public String getLeftText() {
		return leftText;
	}

	/**
	 * @param leftText
	 *            the leftText to set
	 */
	public void setLeftText(String leftText) {
		this.leftText = leftText;
	}

	/**
	 * @return the hintText
	 */
	public String getHintText() {
		return hintText;
	}

	/**
	 * @param hintText
	 *            the hintText to set
	 */
	public void setHintText(String hintText) {
		this.hintText = hintText;
	}

}
