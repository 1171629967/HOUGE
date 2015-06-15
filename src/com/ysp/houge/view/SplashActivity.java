package com.ysp.houge.view;

import java.io.IOException;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;

import com.ysp.houge.R;
import com.ysp.houge.utility.ImageUtil;
import com.ysp.houge.utility.MyApplication;
import com.ysp.houge.utility.UIHelper;

public class SplashActivity extends BaseFragmentActivity {

	private LinearLayout mRootLayout;

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_splash);
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mRootLayout = (LinearLayout) findViewById(R.id.mRootLayout);
	}

	@Override
	protected void initializeData() {
		try {
			// 显示页面背景图
			Drawable drawable = ImageUtil.ResImgToDrawable(SplashActivity.this,
					R.drawable.splash);
			mRootLayout.setBackgroundDrawable(drawable);
		} catch (IOException e) {
			mRootLayout.setBackgroundResource(R.drawable.splash);
		}

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if (MyApplication.getInstance().getCurrentUid() != 0) {
					UIHelper.jumpToMainTabs(SplashActivity.this);
				} else {
					UIHelper.jumpToChooseLoginOrRegister(SplashActivity.this);
				}
			}
		}, 2000);
	}

	@Override
	protected void initActionbar() {
		// TODO Auto-generated method stub

	}

}
