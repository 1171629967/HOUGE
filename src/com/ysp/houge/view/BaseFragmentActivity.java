/*   
 * Copyright (c) 2010-2020 Founder Ltd. All Rights Reserved.   
 *   
 * This software is the confidential and proprietary information of   
 * Founder. You shall not disclose such Confidential Information   
 * and shall use it only in accordance with the terms of the agreements   
 * you entered into with Founder.   
 *   
 */
/**
 * 
 */
package com.ysp.houge.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * This class is used for 页面基础类
 * 
 * @author tyn
 * @version 1.0, 2014-9-8 上午10:44:54
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
	public static final String TITLE_LEFT_LABEL = "titleLeftLabel";
	protected String titleLeftLabel;

	protected boolean isActivityLive;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey(TITLE_LEFT_LABEL)) {
				titleLeftLabel = getIntent().getExtras().getString(
						TITLE_LEFT_LABEL);
			}
		}
		// Possible work around for market launches. See
		// http://code.google.com/p/android/issues/detail?id=2373
		// for more details. Essentially, the market launches the main activity
		// on top of other activities.
		// we never want this to happen. Instead, we check if we are the root
		// and if not, we finish.
		if (!isTaskRoot()) {
			final Intent intent = getIntent();
			final String intentAction = intent.getAction();
			if (intent.hasCategory(Intent.CATEGORY_LAUNCHER)
					&& intentAction != null
					&& intentAction.equals(Intent.ACTION_MAIN)) {
				finish();
				return;
			}
		}
		initializeState(savedInstanceState);
		setContentView();
		initActionbar();
		initializeViews(savedInstanceState);
		initializeData();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(TITLE_LEFT_LABEL, titleLeftLabel);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState.containsKey(TITLE_LEFT_LABEL)) {
			titleLeftLabel = savedInstanceState.getString(TITLE_LEFT_LABEL);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	protected void initializeState(Bundle savedInstanceState) {
	};

	protected abstract void setContentView();

	protected abstract void initializeViews(Bundle savedInstanceState);

	protected abstract void initializeData();

	protected abstract void initActionbar();

	@Override
	protected void onResume() {
		super.onResume();
		isActivityLive = true;
	}

	@Override
	protected void onStop() {
		super.onStop();
		isActivityLive = false;
	}
}
