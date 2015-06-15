package com.syw.avatar;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ysp.houge.R;
import com.ysp.houge.utility.MyApplication;
import com.ysp.houge.utility.SizeUtils;

public class PhotoPickerAdapter extends BaseAdapter {
	private int imageSizePx;
	private Context mContext;
	private List<PhotoInfo> dataList;

	public PhotoPickerAdapter(Context context, List<PhotoInfo> dataList) {
		imageSizePx = (SizeUtils.getScreenWidth((Activity) context) - SizeUtils
				.dip2px(context, 7)) / 4;
		mContext = context;
		this.dataList = dataList;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 存放列表项控件句柄
	 */
	private class ViewHolder {
		public ImageView imageView;
		public ToggleButton tgButton;

		public ViewHolder(View convertView) {
			imageView = (ImageView) convertView.findViewById(R.id.image_view);
			tgButton = (ToggleButton) convertView
					.findViewById(R.id.toggle_button);
			ViewGroup.LayoutParams params = imageView.getLayoutParams();
			params.width = imageSizePx;
			params.height = imageSizePx;
			imageView.setLayoutParams(params);
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.grid_item_img, parent, false);
			viewHolder = new ViewHolder(convertView);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		PhotoInfo item = null;
		if (dataList != null && dataList.size() > position) {
			item = dataList.get(position);
		}
		if (item == null) {
			viewHolder.imageView.setImageResource(R.drawable.defaultpic);
		} else {
			ImageLoader.getInstance().displayImage(item.getPathFile(),
					viewHolder.imageView, MyApplication.mImageOptions);

		}
		viewHolder.tgButton.setVisibility(View.GONE);
		convertView.setTag(viewHolder);

		return convertView;
	}

}
