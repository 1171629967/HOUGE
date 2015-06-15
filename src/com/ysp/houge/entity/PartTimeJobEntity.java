package com.ysp.houge.entity;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * @描述:
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月14日下午1:20:52
 * @version 1.0
 */
public class PartTimeJobEntity implements Serializable {
	/**
	 * @字段：serialVersionUID
	 * @功能描述：
	 * @创建人：tyn
	 * @创建时间：2015年6月14日下午6:51:29
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @字段：partTimeJobId
	 * @功能描述：兼职的ID
	 * @创建人：tyn
	 * @创建时间：2015年6月14日下午3:21:57
	 */
	@SerializedName(value = "partTimeJobId")
	private int partTimeJobId;
	/**
	 * @字段：partTimeJobName
	 * @功能描述：兼职的名称
	 * @创建人：tyn
	 * @创建时间：2015年6月14日下午3:21:59
	 */
	@SerializedName(value = "partTimeJobName")
	private String partTimeJobName;

	/**
	 * @字段：isChecked
	 * @功能描述：是否被选中
	 * @创建人：tyn
	 * @创建时间：2015年6月14日下午3:23:54
	 */
	private boolean isChecked;

	/**
	 * @return the partTimeJobId
	 */
	public int getPartTimeJobId() {
		return partTimeJobId;
	}

	/**
	 * @param partTimeJobId
	 *            the partTimeJobId to set
	 */
	public void setPartTimeJobId(int partTimeJobId) {
		this.partTimeJobId = partTimeJobId;
	}

	/**
	 * @return the partTimeJobName
	 */
	public String getPartTimeJobName() {
		return partTimeJobName;
	}

	/**
	 * @param partTimeJobName
	 *            the partTimeJobName to set
	 */
	public void setPartTimeJobName(String partTimeJobName) {
		this.partTimeJobName = partTimeJobName;
	}

	/**
	 * @return the isChecked
	 */
	public boolean isChecked() {
		return isChecked;
	}

	/**
	 * @param isChecked
	 *            the isChecked to set
	 */
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
