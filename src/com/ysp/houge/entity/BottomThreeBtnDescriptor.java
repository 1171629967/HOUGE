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
 * This class is used for ҳ��ײ���ť�Ի��������ʵ����
 * 
 * @author tyn
 * @version 1.0, 2014-9-3 ����3:07:17
 */

public class BottomThreeBtnDescriptor {
	public enum ClickType {
		ClickOne, ClickTwo, Cancel
	}

	/** ��һ����ť��ʾ���� */
	public String labelOne;
	/** �ڶ�����ť��ʾ���� */
	public String labelTwo;
	/** ȡ��ť��ʾ���� */
	public String labelCancel;
	/** ��һ����ť�ı�����ɫ */
	public int btnOneColorId;
	/** �ڶ�����ť�ı�����ɫ */
	public int btnTwoColorId;

	/**
	 * @param labelOne
	 * @param labelTwo
	 */
	public BottomThreeBtnDescriptor(String labelOne, String labelTwo) {
		super();
		this.labelOne = labelOne;
		this.labelTwo = labelTwo;
	}

	/**
	 * @param labelOne
	 * @param labelTwo
	 * @param labelCancel
	 */
	public BottomThreeBtnDescriptor(String labelOne, String labelTwo,
			String labelCancel) {
		super();
		this.labelOne = labelOne;
		this.labelTwo = labelTwo;
		this.labelCancel = labelCancel;
	}

	public BottomThreeBtnDescriptor(String labelOne, String labelTwo,
			int btnOneColorId, int btnTwoColorId) {
		super();
		this.labelOne = labelOne;
		this.labelTwo = labelTwo;
		this.btnOneColorId = btnOneColorId;
		this.btnTwoColorId = btnTwoColorId;
	}

}
