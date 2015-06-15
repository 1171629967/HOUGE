package com.ysp.houge.utility;

/**
 * log日志打印类
 * */
import android.util.Log;

import com.ysp.houge.BuildConfig;

public class LogUtil {

	/**
	 * 设置有关log.i的日志打印
	 * */
	public static void setLogWithI(String key, String value) {
		if (BuildConfig.DEBUG) {
			Log.i(key, value);
		}
	}

	/**
	 * 设置有关log.e的日志打印
	 * */
	public static void setLogWithE(String key, String value) {
		if (BuildConfig.DEBUG) {
			Log.e(key, value);
		}
	}

	/**
	 * 设置有关log.w的日志打印
	 * */
	public static void setLogWithW(String key, String value) {
		if (BuildConfig.DEBUG) {
			Log.w(key, value);
		}
	}

	/**
	 * 设置有关log.v的日志打印
	 * */
	public static void setLogWithV(String key, String value) {
		if (BuildConfig.DEBUG) {
			Log.v(key, value);
		}
	}
}
