package com.ysp.houge.view;

import com.ysp.houge.R;
import com.ysp.houge.utility.ImageUtil;
import com.ysp.houge.widget.ColoredRatingBar;
import com.ysp.houge.widget.ColoredRatingBar.OnRatingBarChangeListener;
import com.ysp.houge.widget.MyActionBar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;

public class MeFragment extends BaseFragment {
	private ImageView iv_face;
	private ColoredRatingBar ratingBar;

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	protected void initActionbar(View view) {
		MyActionBar actionBar = new MyActionBar(getActivity());
		actionBar.setTitle("我");
		actionBar.setLeftEnable(false);
		actionBar.setRightText("系统");
		RelativeLayout actionbar = (RelativeLayout) view
				.findViewById(R.id.rl_actionbar);
		actionbar.addView(actionBar);
		actionBar.setRightClickListenner(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}

	@Override
	protected int getContentView() {
		return R.layout.fragment_me;
	}

	@Override
	protected void initializeViews(View view, Bundle savedInstanceState) {
		iv_face = (ImageView) view.findViewById(R.id.iv_face);
		ratingBar = (ColoredRatingBar)view.findViewById(R.id.coloredRatingBar);
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {			
			@Override
			public void onRatingChanged(ColoredRatingBar ratingBar, float rating,
					boolean fromUser) {
				
			}
		});
	}

}
