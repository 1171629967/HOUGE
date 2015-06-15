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
package com.ysp.imageloader;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

/**
 * This class is used for 显示圆型图片的ImageLoader使用的显示器
 * 
 * @author tyn
 * @version 1.0, 2015-3-16 上午11:21:21
 */
public class CircleBitmapDisplayer implements BitmapDisplayer {

	protected final int margin;

	public CircleBitmapDisplayer() {
		this(0);
	}

	public CircleBitmapDisplayer(int margin) {
		this.margin = margin;
	}

	@Override
	public void display(Bitmap bitmap, ImageAware imageAware,
			LoadedFrom loadedFrom) {
		if (!(imageAware instanceof ImageViewAware)) {
			throw new IllegalArgumentException(
					"ImageAware should wrap ImageView. ImageViewAware is expected.");
		}
		imageAware.setImageDrawable(new CircleDrawable(bitmap, margin));
	}

}