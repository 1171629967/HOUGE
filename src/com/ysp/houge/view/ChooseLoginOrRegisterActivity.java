package com.ysp.houge.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.ysp.houge.R;
import com.ysp.houge.utility.UIHelper;

public class ChooseLoginOrRegisterActivity extends BaseFragmentActivity
		implements OnClickListener {
	/** 软件介绍切换 */
	private ViewPager mMainPager;
	private Button mLoginBtn;
	private Button mRegisterBtn;
	private TextView mForgotPassword;

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_choose_login_or_register);
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mMainPager = (ViewPager) findViewById(R.id.mMainPager);
		mLoginBtn = (Button) findViewById(R.id.mLoginBtn);
		mRegisterBtn = (Button) findViewById(R.id.mRegisterBtn);
		mForgotPassword = (TextView) findViewById(R.id.mForgotPassword);
		mLoginBtn.setOnClickListener(this);
		mRegisterBtn.setOnClickListener(this);
		mForgotPassword.setOnClickListener(this);
	}

	@Override
	protected void initializeData() {

	}

	@Override
	protected void initActionbar() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mLoginBtn:
			UIHelper.jumpToLogin(ChooseLoginOrRegisterActivity.this);
			break;
		case R.id.mRegisterBtn:
			UIHelper.jumpToPartTimeJobList(ChooseLoginOrRegisterActivity.this);
			break;
		case R.id.mForgotPassword:
			UIHelper.jumpToWriteMobileToGetPwd(ChooseLoginOrRegisterActivity.this);
			break;

		default:
			break;
		}
	}

}
