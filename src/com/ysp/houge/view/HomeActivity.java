package com.ysp.houge.view;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.ysp.houge.R;
import com.ysp.houge.utility.Toaster;


public class HomeActivity extends BaseFragmentActivity {
	private static final String TAG = "HomeActivity";

	public static final int TAB_INDEX_RECOMMEND = 0;
	public static final int TAB_INDEX_NEARBY = 1;
	public static final int TAB_INDEX_MESSAGE = 2;
	public static final int TAB_INDEX_ME = 3;

	private static final int REQ_CODE_LOGIN = 1;
	private static final String KEY_CURRENT_FRAGMENT = "currentFragment";
	private static final String KEY_DETAIL_INTENT = "detailFragment";
	private static final String KEY_CHECK_UPDATE = "checkUpdate";
	
	// 首页引导页key
	private static final String KEY_PRIMARY_GUIDE = "primary_guide";

	RadioGroup mTabGroup;
	//Fragment messageFragment;
	Fragment recommendFragment;
	Fragment nearbyFragment;
	Fragment messageFragment;
	Fragment meFragment;
	int mCurrentTab = -1;

//	@Inject
//	private IStorageManager mStorMan;
//
//	@Inject
//	private IGuahaoServerStub mStub;
//
//	@Inject
//	private IPushManager mPushStub;
//	
//	@Inject 
//	private IEChatManager mEChatManager;
//	@Inject
//	private IStorageManager mPushStorage;
	private TextView videoInviteView;
	//private VideoOrderDetailEntity videoOrderDetailEntity;
	private ImageView primaryGuideView;

	

	
	
	
	
	
	private int getVersionCode() {
		PackageManager packageManager = getPackageManager();
		PackageInfo packInfo;
		try {
			packInfo = packageManager.getPackageInfo(getPackageName(), 0);
			return packInfo.versionCode;
		} catch (NameNotFoundException e) {
			return 0;
		}
	}
	
	
	
	
	
	private static final int HIDE_VIDEO_INVITE_VIEW  = 1;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case HIDE_VIDEO_INVITE_VIEW:
				if(!HomeActivity.this.isFinishing()) {
					videoInviteView.setVisibility(View.GONE);
				}
				break;
			}
		}
	};

	

//	@Override
//	protected void onNewIntent(Intent intent) {
//		super.onNewIntent(intent);
//		setIntent(intent);
//		if (!mStub.isLogined()) {
//			startActivity(LoginActivity.createIntent());
//			this.finish();
//			return;
//		}
//		disposeNotificationClick();
//	}
//
//	public static Intent creatIntent(Context context) {
//		return new Intent(context, HomeActivity.class);
//	}


	private void initController() {
		mTabGroup = (RadioGroup) findViewById(R.id.tabContainer);
		mTabGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int position = getTabPositionById(checkedId);
				if (position >= 0) {
					startFragment(position);
				}
			}
		});
	}

	private void initFragment(Bundle savedInstanceState) {
		int position = TAB_INDEX_RECOMMEND;
		if (null != savedInstanceState) {
			position = savedInstanceState.getInt(KEY_CURRENT_FRAGMENT);
		}
		checkTab(position);
	}

	private void checkTab(int position) {
		final int id = getTabIdByPosition(position);
		View v = findViewById(id);
		if (v instanceof RadioButton) {
			((RadioButton) v).setChecked(true);
		}
	}

	private int getTabIdByPosition(int position) {
		int id = -1;
		switch (position) {
		case TAB_INDEX_RECOMMEND:
			id = R.id.tabRecommend;
			break;
		case TAB_INDEX_NEARBY:
			id = R.id.tabNearby;
			break;
		case TAB_INDEX_MESSAGE:
			id = R.id.tabMessage;
			break;
		case TAB_INDEX_ME:
			id = R.id.tabMe;
			break;
		}
		return id;
	}

	private int getTabPositionById(int id) {
		int position = -1;
		switch (id) {
		case R.id.tabRecommend:
			position = TAB_INDEX_RECOMMEND;
			break;
		case R.id.tabNearby:
			position = TAB_INDEX_NEARBY;
			break;
		case R.id.tabMessage:
			position = TAB_INDEX_MESSAGE;
			break;
		case R.id.tabMe:
			position = TAB_INDEX_ME;
			break;
		}
		return position;
	}

	private void startFragment(int position) {
		changeFragment(position);
	}

	private void changeFragment(int position) {
		Fragment oldfragment = getFragment(mCurrentTab);
		Fragment newfragment = getFragment(position);
		if (newfragment.isAdded()) {
			getSupportFragmentManager().beginTransaction().hide(oldfragment)
					.show(newfragment).commit();
		} else {
			if (null == oldfragment || !oldfragment.isAdded()) {
				getSupportFragmentManager()
						.beginTransaction()
						.add(R.id.fragmentContainer, newfragment,
								TAG + position).commit();
			} else {
				getSupportFragmentManager()
						.beginTransaction()
						.hide(oldfragment)
						.add(R.id.fragmentContainer, newfragment,
								TAG + position).commit();
			}

		}
		mCurrentTab = position;
	}

	private Fragment getFragment(int position) {
		Fragment f = null;

		switch (position) {
		case TAB_INDEX_RECOMMEND:
			// 推荐
			if (null == recommendFragment) {
				recommendFragment = new RecommendFragment();
			}
			f = recommendFragment;
			break;

		case TAB_INDEX_NEARBY:
			// 附近
			if (null == nearbyFragment) {
				nearbyFragment = new NearbyFragment();
			}
			f = nearbyFragment;
			break;

		case TAB_INDEX_MESSAGE:
			// 消息
			if (null == messageFragment) {
				messageFragment = new MessageFragment();
			}
			f = messageFragment;
			break;
		case TAB_INDEX_ME:
			// 我
			if (null == meFragment) {
				meFragment = new MeFragment();
			}
			f = meFragment;
			break;
		}

		return f;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			switch (requestCode) {
			case REQ_CODE_LOGIN:
				changeFragment(mCurrentTab);
				break;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(KEY_CURRENT_FRAGMENT, mCurrentTab);
	}

	protected void disposeNotificationClick() {
		Intent intent = getIntent();
		if (intent == null) {
			return;
		}

		int CNT = 4;
		int tabIdx = intent
				.getIntExtra(KEY_CURRENT_FRAGMENT, TAB_INDEX_RECOMMEND);
		if (tabIdx < 0 || tabIdx > CNT) {
			tabIdx = 0;
		}
		checkTab(tabIdx);

		Intent detailIntent = getIntent().getParcelableExtra(KEY_DETAIL_INTENT);
		if (detailIntent != null) {
			startActivity(detailIntent);
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isExit == false) {
				isExit = true;
				Toaster.getInstance().displayToast("再按一次退出");				
				if (!hasTask) {
					tExit.schedule(task, 2000);
				}
			} else {
				finish();
			}
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	private Boolean isExit = false;
	private Boolean hasTask = false;
	Timer tExit = new Timer();
	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			isExit = false;
			hasTask = true;
		}
	};

//	public boolean onPushMessage(InstantMessage im) {
//		refreshTab();
//		return super.onPushMessage(im);
//	}
//
//	private void refreshTab() {
//		new queryPushMessageTask(HomeActivity.this, false, false).execute();
//	}

	

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_home);
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		initController();
		initFragment(savedInstanceState);
		
	}

	@Override
	protected void initializeData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initActionbar() {
		// TODO Auto-generated method stub
		
	}
}
