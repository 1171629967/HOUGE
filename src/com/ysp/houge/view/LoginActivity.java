package com.ysp.houge.view;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ysp.houge.R;
import com.ysp.houge.entity.EditChooseViewDescriptor;
import com.ysp.houge.utility.MatcherUtil;
import com.ysp.houge.utility.NetConnectUtil;
import com.ysp.houge.utility.Toaster;
import com.ysp.houge.widget.MyActionBar;

/**
 * @描述:
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月9日下午8:20:01
 * @version 1.0
 */
public class LoginActivity extends BaseFragmentActivity implements
		OnClickListener {

	private static final String MOBILE = "mobile";
	private static final String PASSWORD = "password";
	/** 用户填写的手机号码 */
	private String mobile;
	/** 用户填写的密码 */
	private String password;

	/** 手机号码编辑框 */
	private EditChooseView mTelephoneView;
	/** 密码编辑框 */
	private EditChooseView mPasswordView;
	/** 登录按钮 */
	private Button mLoginBtn;
	/** 忘记密码按钮 */
	private TextView mForgotPassword;

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mobile = mTelephoneView.getText();
		password = mPasswordView.getText();
		outState.putString(MOBILE, mobile);
		outState.putString(PASSWORD, password);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState != null) {
			if (savedInstanceState.containsKey(MOBILE)) {
				mobile = savedInstanceState.getString(MOBILE);
			}
			if (savedInstanceState.containsKey(PASSWORD)) {
				password = savedInstanceState.getString(PASSWORD);
			}
		}
		if (!TextUtils.isEmpty(mobile)) {
			mTelephoneView.setText(mobile);
		}
		if (!TextUtils.isEmpty(password)) {
			mPasswordView.setText(password);
		}
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_login);
	}

	@Override
	protected void initActionbar() {
		MyActionBar actionBar = new MyActionBar(this);
		actionBar.setTitle("登录");
		actionBar.setLeftEnable(false);
		RelativeLayout actionbar = (RelativeLayout) findViewById(R.id.rl_actionbar);
		actionbar.addView(actionBar);
	}
	
	@Override
	protected void initializeViews() {
		mTelephoneView = (EditChooseView) findViewById(R.id.mTelephoneView);
		mPasswordView = (EditChooseView) findViewById(R.id.mPasswordView);
		mLoginBtn = (Button) findViewById(R.id.mLoginBtn);
		mForgotPassword = (TextView) findViewById(R.id.mForgotPassword);
		mLoginBtn.setOnClickListener(this);
		mForgotPassword.setOnClickListener(this);
	}

	@Override
	protected void initializeData() {
		initTopTitleView();
		initTelephoneView();
		initPasswordView();

		// mobile = PreferenceUtils.getInstance(this).getLoginMobile();

		mTelephoneView.setHint("请输入您的手机号码");
		mTelephoneView.setInputType(InputType.TYPE_CLASS_PHONE);
		mTelephoneView.setMaxLength(11);

		mPasswordView.setHint("请输入密码");
		mPasswordView.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);

		if (!TextUtils.isEmpty(mobile)) {
			mTelephoneView.setText(mobile);
		}

		mTelephoneView.mClearEditText.requestFocus();
	}

	private void initTopTitleView() {

	}

	/**
	 * 
	 */
	private void initTelephoneView() {
		EditChooseViewDescriptor descriptor = new EditChooseViewDescriptor(
				getResources().getString(R.string.please_input_telephone));
		mTelephoneView.initData(descriptor);
		mTelephoneView.setMaxLength(11);
		mTelephoneView.setInputType(InputType.TYPE_CLASS_PHONE);
		mTelephoneView.notifyDataSetChanged();
	}

	/**
	 * 
	 */
	private void initPasswordView() {
		EditChooseViewDescriptor descriptor = new EditChooseViewDescriptor(
				getResources().getString(R.string.please_input_password));
		mPasswordView.initData(descriptor);
		mPasswordView.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		mPasswordView.notifyDataSetChanged();
	}

	/**
	 * 网络请求登陆
	 */
	protected void httpLoginRequest() {
		mobile = mTelephoneView.getText();
		password = mPasswordView.getText();
		RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
		JsonObjectRequest req = new JsonObjectRequest("", null,
				new Response.Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						try {
							VolleyLog.v("Response:%n %s", response.toString(4));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.e("Error: ", error.getMessage());
					}
				});
		queue.add(req);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.mForgotPassword:
			break;
		case R.id.mLoginBtn:
			if (!NetConnectUtil.isConnectingToInternet(LoginActivity.this)) {
				Toaster.getInstance().displayToast(
						getString(R.string.no_net_connected), -1);
				return;
			}
			if (TextUtils.isEmpty(mTelephoneView.getText())) {
				Toaster.getInstance().displayToast("请输入手机号码", -1);
				return;
			}
			if (TextUtils.isEmpty(mPasswordView.getText())) {
				Toaster.getInstance().displayToast("请输入密码", -1);
				return;
			}
			if (!MatcherUtil
					.checkTelephoneNumberVilid(mTelephoneView.getText())) {
				Toaster.getInstance().displayToast("您输入的手机号码格式有误，请检查后输入", -1);
				return;
			}
			// httpLoginRequest();
			break;
		default:
			break;
		}
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	

}
