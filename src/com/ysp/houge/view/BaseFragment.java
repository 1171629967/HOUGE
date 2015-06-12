package com.ysp.houge.view;

import com.ysp.houge.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		return inflater.inflate(getContentView(), null);
	}
	
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		initActionbar(view);
//		getMyFirstPageInfo();
//		
//		imageLoader = ImageLoader.getInstance(getActivity());
//		DoctorHeadPhotoManager.registerListener(this);
//		initController();
//		meAuth();
 	}
	
	
	protected abstract void initActionbar(View view);
	protected abstract int getContentView();

}
