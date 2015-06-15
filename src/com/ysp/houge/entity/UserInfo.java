package com.ysp.houge.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @描述:
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月13日下午7:45:55
 * @version 1.0
 */
public class UserInfo {
	/**
	 * @字段：uid
	 * @功能描述：
	 * @创建人：tyn
	 * @创建时间：2015年6月14日下午2:47:37
	 */
	@SerializedName(value = "uid")
	private int uid;
	/**
	 * @字段：name
	 * @功能描述：
	 * @创建人：tyn
	 * @创建时间：2015年6月14日下午2:47:44
	 */
	@SerializedName(value = "name")
	private String name;
	/**
	 * @字段：image_url
	 * @功能描述：
	 * @创建人：tyn
	 * @创建时间：2015年6月14日下午2:47:47
	 */
	@SerializedName(value = "image_url")
	private String image_url;
}
