package com.ysp.houge.view;

import com.ysp.houge.R;
import com.ysp.houge.widget.MyActionBar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;



public class MessageFragment extends BaseFragment{
	
	

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
//		getMyFirstPageInfo();
//		
//		imageLoader = ImageLoader.getInstance(getActivity());
//		DoctorHeadPhotoManager.registerListener(this);
//		initController();
//		meAuth();
 	}

	@Override
	protected void initActionbar(View view) {
		MyActionBar actionBar = new MyActionBar(getActivity());
		actionBar.setTitle("消息");
		actionBar.setLeftEnable(false);
		RelativeLayout actionbar = (RelativeLayout)view.findViewById(R.id.rl_actionbar);
		actionbar.addView(actionBar);
	}

	@Override
	protected int getContentView() {
		return R.layout.fragment_message;
	}

	@Override
	protected void initializeViews(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

}
