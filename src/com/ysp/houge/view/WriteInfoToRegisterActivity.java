package com.ysp.houge.view;

import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.syw.avatar.CropperActivity;
import com.syw.avatar.util.ImageUtil;
import com.syw.avatar.util.SLog;
import com.ysp.houge.R;
import com.ysp.houge.entity.BottomThreeBtnDescriptor;
import com.ysp.houge.entity.BottomThreeBtnDescriptor.ClickType;
import com.ysp.houge.entity.CharacteristicEntity;
import com.ysp.houge.entity.PartTimeJobEntity;
import com.ysp.houge.itf.OnThreeBtnClickListener;
import com.ysp.houge.utility.CreateDialogUtil;
import com.ysp.houge.utility.DoubleClickUtil;
import com.ysp.houge.utility.FileUtil;
import com.ysp.houge.utility.LogUtil;
import com.ysp.houge.utility.NetConnectUtil;
import com.ysp.houge.utility.Toaster;
import com.ysp.houge.widget.MyActionBar;

/**
 * @描述:
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月14日下午2:27:50
 * @version 1.0
 */
public class WriteInfoToRegisterActivity extends BaseFragmentActivity implements
		OnClickListener {
	private static final String MOBILE = "mobile";
	private static final String PASSWORD = "password";
	private static final String USER_NAME = "userName";
	public static final String PART_TIME_JOB_LIST = "partTimeJobList";
	public static final String CHARACTERISTICES = "characteristices";
	private static final String CROP_AVATAR_PATH = "cropAvatarPath";
	/** 是否已经设置图片 */
	private boolean isAvatarSet = false;
	/** 用户设置的密码 */
	private String password;
	/** 用户设置的手机号 */
	private String mobile;
	/** 用户设置的姓名 */
	private String userName;
	private EditText mNameEdit;
	private EditText mMobileEdit;
	private EditText mPasswordEdit;
	private ImageView mAvatar;
	private Button mFinishBtn;
	/** 用户设置的头像 */
	private Bitmap photo;
	/** 截图的头像地址 */
	private String cropAvatarPath;

	private ArrayList<PartTimeJobEntity> partTimeJobEntities;
	private ArrayList<CharacteristicEntity> characteristicEntities;

	@SuppressWarnings("unchecked")
	@Override
	protected void initializeState(Bundle savedInstanceState) {
		super.initializeState(savedInstanceState);
		if (getIntent().getExtras() != null) {
			if (getIntent().getExtras().containsKey(PART_TIME_JOB_LIST)) {
				partTimeJobEntities = (ArrayList<PartTimeJobEntity>) getIntent()
						.getExtras().getSerializable(PART_TIME_JOB_LIST);
			}
			if (getIntent().getExtras().containsKey(CHARACTERISTICES)) {
				characteristicEntities = (ArrayList<CharacteristicEntity>) getIntent()
						.getExtras().getSerializable(CHARACTERISTICES);
			}
		}
	}

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_mobile_register);
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mNameEdit = (EditText) findViewById(R.id.mNameEdit);
		mMobileEdit = (EditText) findViewById(R.id.mMobileEdit);
		mPasswordEdit = (EditText) findViewById(R.id.mPasswordEdit);
		mAvatar = (ImageView) findViewById(R.id.mAvatar);
		mFinishBtn = (Button) findViewById(R.id.mFinishBtn);
		mFinishBtn.setOnClickListener(this);
		mAvatar.setOnClickListener(this);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(MOBILE, mMobileEdit.getText().toString());
		outState.putString(PASSWORD, mPasswordEdit.getText().toString());
		outState.putString(USER_NAME, mNameEdit.getText().toString());
		outState.putString(CROP_AVATAR_PATH, cropAvatarPath);
		outState.putSerializable(PART_TIME_JOB_LIST, partTimeJobEntities);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState.containsKey(MOBILE)) {
			mobile = savedInstanceState.getString(MOBILE);
		}
		if (savedInstanceState.containsKey(PASSWORD)) {
			password = savedInstanceState.getString(PASSWORD);
		}
		if (savedInstanceState.containsKey(USER_NAME)) {
			userName = savedInstanceState.getString(USER_NAME);
		}
		if (savedInstanceState.containsKey(CROP_AVATAR_PATH)) {
			cropAvatarPath = savedInstanceState.getString(CROP_AVATAR_PATH);
		}
		if (savedInstanceState.containsKey(PART_TIME_JOB_LIST)) {
			partTimeJobEntities = (ArrayList<PartTimeJobEntity>) savedInstanceState
					.getSerializable(PART_TIME_JOB_LIST);
		}
		if (savedInstanceState.containsKey(CHARACTERISTICES)) {
			characteristicEntities = (ArrayList<CharacteristicEntity>) savedInstanceState
					.getSerializable(CHARACTERISTICES);
		}
	}

	@Override
	protected void initializeData() {

	}

	@Override
	protected void initActionbar() {
		MyActionBar actionBar = new MyActionBar(this);
		actionBar.setTitle("注册");
		actionBar.setLeftEnable(true);
		actionBar.setLeftText("返回");
		RelativeLayout actionbar = (RelativeLayout) findViewById(R.id.rl_actionbar);
		actionbar.addView(actionBar);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_CANCELED) {
			SLog.d("CropperPhotoPath", "Canceled Avatar Capture.");
			return;
		}
		// 根据上面发送过去的请求码来区别
		switch (requestCode) {
		case 10001:
		case 10002:
			cropAvatarPath = data.getStringExtra("CropperPhotoPath");
			LogUtil.setLogWithE("CropperPhotoPath", "CropperPhotoPath = "
					+ cropAvatarPath);
			if (cropAvatarPath != null && cropAvatarPath.length() > 0) {
				Bitmap bmp = ImageUtil.readBitMap(cropAvatarPath);
				if (bmp != null) {
					bmp = ImageUtil.zoomBitmap(bmp, 640, 640);
					photo = bmp;
					mAvatar.setImageBitmap(photo);
					isAvatarSet = true;
				}
			}
			break;
		default:
			break;
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mFinishBtn:
			if (!isAvatarSet) {
				Toaster.getInstance().displayToast(
						getString(R.string.set_avatar), -1);
				return;
			}
			if (TextUtils.isEmpty(mNameEdit.getText().toString().trim())) {
				Toaster.getInstance().displayToast(
						getString(R.string.set_name), -1);
				return;
			}
			if (TextUtils.isEmpty(mMobileEdit.getText().toString().trim())) {
				Toaster.getInstance().displayToast(
						getString(R.string.set_mobile), -1);
				return;
			}
			if (TextUtils.isEmpty(mPasswordEdit.getText().toString().trim())) {
				Toaster.getInstance().displayToast(
						getString(R.string.set_password), -1);
				return;
			}
			if (!NetConnectUtil
					.isConnectingToInternet(WriteInfoToRegisterActivity.this)) {
				Toaster.getInstance().displayToast(
						getString(R.string.no_net_connected), -1);
				return;
			}
			if (DoubleClickUtil.isFastClick()) {
				return;
			}
			httpRequestUploadAvatar();
			break;
		case R.id.mAvatar:
			createChoosePicDialog();
			break;

		default:
			break;
		}
	}

	private void httpRequestUploadAvatar() {

	}

	/**
	 * 创建选择图片对话框
	 */
	private void createChoosePicDialog() {
		// 弹出选择图片对话框
		BottomThreeBtnDescriptor descriptor = new BottomThreeBtnDescriptor(
				getString(R.string.from_store), getString(R.string.from_camera));
		new CreateDialogUtil().createBottomThreeBtnDialog(
				WriteInfoToRegisterActivity.this, descriptor,
				new OnThreeBtnClickListener() {

					@Override
					public void onThreeBtnClick(ClickType clickType) {
						switch (clickType) {
						// 图库选择图片
						case ClickOne:
							Intent intent = new Intent(
									WriteInfoToRegisterActivity.this,
									CropperActivity.class);
							intent.putExtra("PickWay", "PICK");
							startActivityForResult(intent, 10001);
							break;
						// 相机选择图片
						case ClickTwo:
							if (!FileUtil.isSdcardExist()) {
								Toast.makeText(
										WriteInfoToRegisterActivity.this,
										"未找到存储卡，无法存储照片！", Toast.LENGTH_LONG)
										.show();
								return;
							}
							Intent intent1 = new Intent(
									WriteInfoToRegisterActivity.this,
									CropperActivity.class);
							intent1.putExtra("PickWay", "TAKE");
							startActivityForResult(intent1, 10002);
							break;
						case Cancel:
							break;
						default:
							break;
						}
					}
				});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (photo != null) {
			photo.recycle();
			photo = null;
		}
	}

}
