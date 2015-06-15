/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ysp.houge.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * @描述:配置类
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月13日下午2:51:15
 * @version 1.0
 */
public class PreferenceUtils {

	/**
	 * 保存Preference的name
	 */
	private static SharedPreferences mSharedPreferences;
	private static PreferenceUtils mPreferenceUtils;
	private static SharedPreferences.Editor editor;

	/**
	 * @字段：UID
	 * @功能描述：用户的uid
	 * @创建人：tyn
	 * @创建时间：2015年6月15日下午2:51:46
	 */
	private static final String UID = "uid";
	/**
	 * @字段：LOGIN_MOBILE
	 * @功能描述：用户的登录号码
	 * @创建人：tyn
	 * @创建时间：2015年6月15日下午2:51:57
	 */
	private static final String LOGIN_MOBILE = "mobile";
	/**
	 * @字段：IS_TOKEN_INVILID
	 * @功能描述：token是否失效
	 * @创建人：tyn
	 * @创建时间：2015年6月15日下午2:52:01
	 */
	private static final String IS_TOKEN_INVILID = "isTokenInvilid";

	private PreferenceUtils(Context context) {
		mSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	/**
	 * 单例模式，获取instance实例
	 * 
	 * @param cxt
	 * @return
	 */
	public static PreferenceUtils getInstance(Context cxt) {
		if (mPreferenceUtils == null) {
			mPreferenceUtils = new PreferenceUtils(cxt);
		}
		editor = mSharedPreferences.edit();
		return mPreferenceUtils;
	}

	/**
	 * 获取当前token是否失效
	 *
	 * @param type
	 */
	public void setIsTokenInvilid(boolean type) {
		editor.putBoolean(IS_TOKEN_INVILID, type);
		editor.commit();
	}

	/**
	 * 设置当前token是否失效
	 *
	 * @return
	 */
	public boolean getTokenInvilid() {
		return mSharedPreferences.getBoolean(IS_TOKEN_INVILID, false);
	}

	/**
	 * 设置当前登录用户的uid
	 * 
	 * @param uid
	 */
	public void setUid(int uid) {
		editor.putInt(UID, uid);
		editor.commit();
	}

	/**
	 * 获取当前登录用户的uid
	 * 
	 * @return
	 */
	public int getUid() {
		return mSharedPreferences.getInt(UID, 0);
	}

	public String getLoginMobile() {
		return mSharedPreferences.getString(LOGIN_MOBILE, "");
	}

	/**
	 * 
	 * @return
	 */
	public void setLoginMobile(String mobile) {
		editor.putString(LOGIN_MOBILE, mobile);
		editor.commit();
	}

}
