package com.ysp.houge.view;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.ysp.houge.R;
import com.ysp.houge.entity.EditChooseViewDescriptor;
import com.ysp.houge.utility.NetConnectUtil;
import com.ysp.houge.utility.Toaster;
import com.ysp.houge.utility.UIHelper;
import com.ysp.houge.widget.EditChooseView;
import com.ysp.houge.widget.MyActionBar;

public class WriteMobileToGetPwdActivity extends BaseFragmentActivity implements
		OnClickListener {

	private EditChooseView mCheckCodeView;
	private Button mFinishBtn;
	private Button mResendBtn;
	private EditText mMobileEdit;

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_write_mobile_to_get_pwd);
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mCheckCodeView = (EditChooseView) findViewById(R.id.mCheckCodeView);
		mMobileEdit = (EditText) findViewById(R.id.mMobileEdit);
		mResendBtn = (Button) findViewById(R.id.mResendBtn);
		mFinishBtn = (Button) findViewById(R.id.mFinishBtn);
		mFinishBtn.setOnClickListener(this);
		mResendBtn.setOnClickListener(this);
	}

	@Override
	protected void initializeData() {
		initCheckCodeView();
	}

	/**
	 * 
	 */
	private void initCheckCodeView() {
		EditChooseViewDescriptor descriptor = new EditChooseViewDescriptor(
				"输入发送给您的验证码", "验证码");
		// EditChooseViewDescriptor descriptor = new EditChooseViewDescriptor(
		// "注册时的手机号码", "手机号码");
		mCheckCodeView.initData(descriptor);
		mCheckCodeView.setInputType(InputType.TYPE_CLASS_NUMBER);
		mCheckCodeView.notifyDataSetChanged();
	}

	@Override
	protected void initActionbar() {
		MyActionBar actionBar = new MyActionBar(this);
		actionBar.setTitle("找回密码");
		actionBar.setLeftEnable(true);
		actionBar.setLeftText("返回");
		RelativeLayout actionbar = (RelativeLayout) findViewById(R.id.rl_actionbar);
		actionbar.addView(actionBar);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.mFinishBtn:
			if (!NetConnectUtil
					.isConnectingToInternet(WriteMobileToGetPwdActivity.this)) {
				Toaster.getInstance().displayToast(
						getString(R.string.no_net_connected), -1);
				return;
			}
			if (TextUtils.isEmpty(mCheckCodeView.getText())) {
				Toaster.getInstance().displayToast("请输入验证码", -1);
				return;
			}
			UIHelper.jumpToWriteNewPassword(WriteMobileToGetPwdActivity.this);
			break;
		case R.id.mResendBtn:
			break;
		default:
			break;
		}
	}
}
