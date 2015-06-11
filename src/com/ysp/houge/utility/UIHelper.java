package com.ysp.houge.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ysp.houge.view.LoginActivity;

/**
 * @描述:
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月8日下午6:15:34
 * @version 1.0
 */
public class UIHelper {
	public static void jumpToLogin(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
		((Activity) context).finish();
	}
}
