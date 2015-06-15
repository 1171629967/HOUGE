package com.ysp.houge.view;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ysp.houge.R;
import com.ysp.houge.entity.PartTimeJobEntity;
import com.ysp.houge.utility.UIHelper;
import com.ysp.houge.widget.MyActionBar;

/**
 * @描述:兼职列表页面
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月14日下午1:02:35
 * @version 1.0
 */
public class PartTimeJobListActivity extends
		BaseListOrGridFragmentActivity<PartTimeJobEntity> implements
		OnClickListener {

	private GridView mGridView;
	private Button mNextStepBtn;
	private ArrayList<PartTimeJobEntity> partTimeJobChoosed;

	@Override
	protected void setContentView() {
		setContentView(R.layout.activity_part_time_job_list);
	}

	@Override
	protected void initializeViews(Bundle savedInstanceState) {
		mGridView = (GridView) findViewById(R.id.mGridView);
		mNextStepBtn = (Button) findViewById(R.id.mNextStepBtn);
		mNextStepBtn.setOnClickListener(this);
	}

	@Override
	protected void initializeData() {
		getListData();
		mAdapter = new ListAdapter(mDatas);
		mGridView.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mNextStepBtn:
			UIHelper.jumpToWriteInfoToRegister(PartTimeJobListActivity.this,
					partTimeJobChoosed);
			break;

		default:
			break;
		}
	}

	private void getListData() {
		// 获取所有的投资阶段
		String[] items = getResources().getStringArray(
				R.array.part_time_job_list);
		if (null != items) {
			int titleLength = items.length;
			PartTimeJobEntity partTimeJobEntity = null;
			for (int index = 0; index < titleLength; index++) {
				partTimeJobEntity = new PartTimeJobEntity();
				partTimeJobEntity.setPartTimeJobName(items[index]);
				partTimeJobEntity.setPartTimeJobId(index);
				mDatas.add(partTimeJobEntity);
			}
		}
	}

	@Override
	protected void initActionbar() {
		MyActionBar actionBar = new MyActionBar(this);
		actionBar.setTitle("你能做点什么？");
		actionBar.setLeftEnable(false);
		RelativeLayout actionbar = (RelativeLayout) findViewById(R.id.rl_actionbar);
		actionbar.addView(actionBar);
	}

	@Override
	public View setDataAtPositon(int position, View convertView,
			ViewGroup viewGroup) {
		final Holder holder;
		final PartTimeJobEntity partTimeJobEntity = mDatas.get(position);
		if (convertView == null) {
			convertView = (View) LayoutInflater.from(
					PartTimeJobListActivity.this).inflate(
					R.layout.griditem_part_time_job, null);
			holder = new Holder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		if (holder != null) {
			holder.mItemText.setText(partTimeJobEntity.getPartTimeJobName());
			if (partTimeJobEntity.isChecked()) {
				holder.mItemIcon.setVisibility(View.VISIBLE);
				holder.mItemText.setTextColor(getResources().getColor(
						R.color.white));
				holder.mItemLayout
						.setBackgroundResource(R.drawable.rectangle_border_actionbar_bg_actionbar);
			} else {
				holder.mItemIcon.setVisibility(View.INVISIBLE);
				holder.mItemText.setTextColor(getResources().getColor(
						R.color.color_actionbar_bg));
				holder.mItemLayout
						.setBackgroundResource(R.drawable.rectangle_border_actionbar_bg_write);
			}
			holder.mItemLayout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					if (partTimeJobEntity.isChecked()) {
						partTimeJobEntity.setChecked(false);
						holder.mItemIcon.setVisibility(View.INVISIBLE);
						holder.mItemText.setTextColor(getResources().getColor(
								R.color.color_actionbar_bg));
						holder.mItemLayout
								.setBackgroundResource(R.drawable.rectangle_border_actionbar_bg_write);
					} else {
						partTimeJobEntity.setChecked(true);
						holder.mItemIcon.setVisibility(View.VISIBLE);
						holder.mItemText.setTextColor(getResources().getColor(
								R.color.white));
						holder.mItemLayout
								.setBackgroundResource(R.drawable.rectangle_border_dp5_actionbar_bg_actionbar);
					}
				}
			});
		}
		return convertView;
	}

	class Holder {
		private ImageView mItemIcon;
		private TextView mItemText;
		private RelativeLayout mItemLayout;

		public Holder(View convertView) {
			mItemIcon = (ImageView) convertView.findViewById(R.id.mItemIcon);
			mItemText = (TextView) convertView.findViewById(R.id.mItemText);
			mItemLayout = (RelativeLayout) convertView
					.findViewById(R.id.mItemLayout);
		}

	}

}
