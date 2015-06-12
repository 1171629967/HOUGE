package com.ysp.houge.view;

import com.ysp.houge.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class MessageFragment extends BaseFragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_message, null);
	}  

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

}
