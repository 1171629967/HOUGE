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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ysp.houge.R;
import com.ysp.houge.entity.EditChooseViewDescriptor;
import com.ysp.houge.utility.NetConnectUtil;
import com.ysp.houge.utility.Toaster;
import com.ysp.houge.widget.EditChooseView;
import com.ysp.houge.widget.MyActionBar;

/**
 * @描述:
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月10日下午2:46:47
 * @version 1.0
 */
public class WriteNewPasswordActivity extends BaseFragmentActivity implements
		OnClickListener {
	private String newPassword;
	private String intputPasswordDouble;

	/** 旧密码编辑框 */
	private EditChooseView mNewPasswordView;
	/** 新密码编辑框 */
	private EditChooseView mInputPasswordDoubleView;
	/** 登录按钮 */
	private Button mFinishBtn;

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_write_new_password);
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mNewPasswordView = (EditChooseView) findViewById(R.id.mNewPasswordView);
		mInputPasswordDoubleView = (EditChooseView) findViewById(R.id.mInputPasswordDoubleView);
		mFinishBtn = (Button) findViewById(R.id.mFinishBtn);
		mFinishBtn.setOnClickListener(this);
	}

	@Override
	protected void initializeData() {
		initNewPasswordView();
		initInputPasswordDoubleView();
	}

	/**
	 * 
	 */
	private void initNewPasswordView() {
		EditChooseViewDescriptor descriptor = new EditChooseViewDescriptor(
				getString(R.string.password_hint), "设新密码");
		mNewPasswordView.initData(descriptor);
		mNewPasswordView.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		mNewPasswordView.notifyDataSetChanged();
	}

	/**
	 * 
	 */
	private void initInputPasswordDoubleView() {
		EditChooseViewDescriptor descriptor = new EditChooseViewDescriptor(
				"请保持一致", "重新输入");
		mInputPasswordDoubleView.initData(descriptor);
		mInputPasswordDoubleView.setInputType(InputType.TYPE_CLASS_TEXT
				| InputType.TYPE_TEXT_VARIATION_PASSWORD);
		mInputPasswordDoubleView.notifyDataSetChanged();
	}

	/**
	 * 网络请求
	 */
	protected void httpRevisePasswordRequest() {
		newPassword = mNewPasswordView.getText();
		intputPasswordDouble = mInputPasswordDoubleView.getText();
		RequestQueue queue = Volley
				.newRequestQueue(WriteNewPasswordActivity.this);
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
		case R.id.mFinishBtn:
			if (!NetConnectUtil
					.isConnectingToInternet(WriteNewPasswordActivity.this)) {
				Toaster.getInstance().displayToast(
						getString(R.string.no_net_connected), -1);
				return;
			}
			if (TextUtils.isEmpty(mNewPasswordView.getText())) {
				Toaster.getInstance().displayToast("请输入新密码", -1);
				return;
			}
			if (TextUtils.isEmpty(mInputPasswordDoubleView.getText())) {
				Toaster.getInstance().displayToast("请重新输入新密码", -1);
				return;
			}
			// httpRevisePasswordRequest();
			break;
		default:
			break;
		}
	}

	@Override
	protected void initActionbar() {
		MyActionBar actionBar = new MyActionBar(this);
		actionBar.setTitle("设新密码");
		actionBar.setLeftEnable(true);
		actionBar.setLeftText("返回");
		RelativeLayout actionbar = (RelativeLayout) findViewById(R.id.rl_actionbar);
		actionbar.addView(actionBar);
	}

}
