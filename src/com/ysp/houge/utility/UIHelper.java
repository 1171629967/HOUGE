package com.ysp.houge.utility;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.ysp.houge.entity.PartTimeJobEntity;
import com.ysp.houge.view.ChooseLoginOrRegisterActivity;
import com.ysp.houge.view.LoginActivity;
import com.ysp.houge.view.PartTimeJobListActivity;
import com.ysp.houge.view.WriteInfoToRegisterActivity;
import com.ysp.houge.view.WriteMobileToGetPwdActivity;
import com.ysp.houge.view.WriteNewPasswordActivity;

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
	/**
	 * @描述:跳转到登录页面
	 * @方法名: jumpToLogin
	 * @param context
	 * @返回类型 void
	 * @创建人 tyn
	 * @创建时间 2015年6月13日下午8:44:17
	 * @修改人 tyn
	 * @修改时间 2015年6月13日下午8:44:17
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static void jumpToLogin(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
	}

	/**
	 * @描述:跳转到主页面
	 * @方法名: jumpToMainTabs
	 * @param context
	 * @返回类型 void
	 * @创建人 tyn
	 * @创建时间 2015年6月13日下午2:56:59
	 * @修改人 tyn
	 * @修改时间 2015年6月13日下午2:56:59
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static void jumpToMainTabs(Context context) {
		Intent intent = new Intent(context, LoginActivity.class);
		context.startActivity(intent);
		((Activity) context).finish();
	}

	/**
	 * @描述:跳转到选择登录或者注册页面
	 * @方法名: jumpToChooseLoginOrRegister
	 * @param context
	 * @返回类型 void
	 * @创建人 tyn
	 * @创建时间 2015年6月13日下午2:57:49
	 * @修改人 tyn
	 * @修改时间 2015年6月13日下午2:57:49
	 * @修改备注
	 * @since
	 * @throws
	 */
	public static void jumpToChooseLoginOrRegister(Context context) {
		Intent intent = new Intent(context, ChooseLoginOrRegisterActivity.class);
		context.startActivity(intent);
		((Activity) context).finish();
	}

	public static void jumpToPartTimeJobList(Context context) {
		Intent intent = new Intent(context, PartTimeJobListActivity.class);
		context.startActivity(intent);
	}

	public static void jumpToWriteInfoToRegister(Context context,
			ArrayList<PartTimeJobEntity> partTimeJobEntities) {
		Intent intent = new Intent(context, WriteInfoToRegisterActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(WriteInfoToRegisterActivity.PART_TIME_JOB_LIST,
				partTimeJobEntities);
		context.startActivity(intent);
	}

	public static void jumpToWriteMobileToGetPwd(Context context) {
		Intent intent = new Intent(context, WriteMobileToGetPwdActivity.class);
		context.startActivity(intent);
	}

	public static void jumpToWriteNewPassword(Context context) {
		Intent intent = new Intent(context, WriteNewPasswordActivity.class);
		context.startActivity(intent);
	}
}
