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
package com.ysp.houge.widget;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ysp.houge.R;
import com.ysp.houge.entity.EditChooseViewDescriptor;

/**
 * This class is used for ...
 * 
 * @author tyn
 * @version 1.0, 2015-1-6 下午2:10:04
 */

public class EditChooseView extends RelativeLayout implements
		android.view.View.OnClickListener, TextWatcher {
	public interface OnTextChangedListener {
		void onTextChanged(String keyword);
	}

	private ImageView mClearImg;
	public EditText mClearEditText;

	private EditChooseViewDescriptor descriptor;
	private OnTextChangedListener onTextChangedListener;
	private Context context;
	private TextView mLeftLabel;

	public OnTextChangedListener getOnTextChangedListener() {
		return onTextChangedListener;
	}

	public void setOnTextChangedListener(
			OnTextChangedListener onTextChangedListener) {
		this.onTextChangedListener = onTextChangedListener;
	}

	public EditChooseView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initViews(context);
	}

	public EditChooseView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initViews(context);
	}

	public EditChooseView(Context context) {
		super(context);
		initViews(context);
	}

	/**
	 * @param context
	 */
	private void initViews(Context context) {
		this.context = context;
		LayoutInflater.from(context).inflate(R.layout.view_edit_choose_search,
				this);
		mClearImg = (ImageView) findViewById(R.id.mClearImg);
		mClearEditText = (EditText) findViewById(R.id.mClearEditText);
		mLeftLabel = (TextView) findViewById(R.id.mLeftLabel);
		mClearImg.setOnClickListener(this);
		mClearEditText.addTextChangedListener(this);
	}

	public void initData(EditChooseViewDescriptor descriptor) {
		this.descriptor = descriptor;
	}

	public boolean notifyDataSetChanged() {
		if (descriptor == null) {
			setVisibility(View.GONE);
			return false;
		}
		if (!TextUtils.isEmpty(descriptor.getHintText())) {
			mClearEditText.setHint(descriptor.getHintText());
		} else {
			mClearEditText.setHint("");
		}
		if (!TextUtils.isEmpty(descriptor.getLeftText())) {
			mLeftLabel.setHint(descriptor.getLeftText());
			mLeftLabel.setVisibility(View.VISIBLE);
		} else {
			mLeftLabel.setVisibility(View.GONE);
		}
		return true;
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.mClearImg:
			mClearEditText.setText("");
			mClearEditText.requestFocus();
			mClearImg.setVisibility(View.GONE);
			break;

		default:
			break;
		}
	}

	public String getText() {
		return mClearEditText.getText().toString().trim();
	}

	public void requestEditFocus() {
		mClearEditText.requestFocus();
	}

	public void setText(String content) {
		mClearEditText.setText(content);
	}

	public void setHint(String content) {
		mClearEditText.setHint(content);
	}

	public void setInputType(int inputType) {
		mClearEditText.setInputType(inputType);
	}

	public void setMaxLength(int count) {
		mClearEditText
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
						count) });
	}

	@Override
	public void afterTextChanged(Editable arg0) {
		if (TextUtils.isEmpty(mClearEditText.getText().toString())) {
			mClearImg.setVisibility(View.INVISIBLE);
		} else {
			mClearImg.setVisibility(View.VISIBLE);
		}
		if (onTextChangedListener != null) {
			onTextChangedListener.onTextChanged(mClearEditText.getText()
					.toString());
		}
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {

	}

	@Override
	public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

	}
}
