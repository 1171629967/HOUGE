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
package com.ysp.houge.utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;

/**
 * @描述:
 * @Copyright Copyright (c) 2015
 * @Company .
 * 
 * @author tyn
 * @date 2015年6月9日下午8:05:25
 * @version 1.0
 */
public class ImageUtil {
	/** 选择图片请求码 */
	public static final int IMAGE_REQUEST_CODE = 0;
	/** 选择拍照请求码 */
	public static final int CAMERA_REQUEST_CODE = 1;

	/**
	 * 把特别大的图片进行压缩显示，此方法的出处印象笔记里有记载，记得查看
	 * 
	 * @throws IOException
	 * */
	public static Drawable ResImgToDrawable(Context context, int drawableId)
			throws IOException {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		// 设置背景图
		InputStream is = context.getResources().openRawResource(drawableId);
		Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);
		is.close();
		Drawable drawable = new BitmapDrawable(context.getResources(), bitmap);
		return drawable;
	}

	public static byte[] setBitmapToBytes(Bitmap bmp) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
		byte[] bitmapByte = baos.toByteArray();
		return bitmapByte;
	}

	/** 从图库中选择图片 */
	public static void choosePictureFromStore(Context context) {
		Intent picture;
		if (Build.VERSION.SDK_INT < 19) {
			picture = new Intent(Intent.ACTION_GET_CONTENT);
			picture.setType("image/*");
		} else {
			picture = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		}
		((Activity) context)
				.startActivityForResult(picture, IMAGE_REQUEST_CODE);
	}

	/** 从拍照中选择图片 */
	public static void choosePictureFromCamera(Context context) {
		Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 判断存储卡是否可以用，可用进行存储
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri
					.fromFile(new File(Environment
							.getExternalStorageDirectory(), "cameraimg")));
		}
		((Activity) context).startActivityForResult(intentFromCapture,
				CAMERA_REQUEST_CODE);
	}
	
	
	/** 获取圆形图片 */
	public static Bitmap getRoundBitmap(Bitmap scaleBitmapImage) {
		int targetWidth = 1000;
		int targetHeight = 1000;
		Bitmap targetBitmap = Bitmap.createBitmap(targetWidth, targetHeight,
				Bitmap.Config.ARGB_8888);

		Canvas canvas = new Canvas(targetBitmap);
		Path path = new Path();
		path.addCircle(((float) targetWidth - 1) / 2,
				((float) targetHeight - 1) / 2,
				(Math.min(((float) targetWidth), ((float) targetHeight)) / 2),
				Path.Direction.CCW);

		canvas.clipPath(path);
		Bitmap sourceBitmap = scaleBitmapImage;
		canvas.drawBitmap(sourceBitmap, new Rect(0, 0, sourceBitmap.getWidth(),
				sourceBitmap.getHeight()), new Rect(0, 0, targetWidth,
				targetHeight), null);
		return targetBitmap;
	}
	/** 获取圆形图片 */
	public static Drawable getRoundDrawable(Drawable d){
		Bitmap b=getRoundBitmap(((BitmapDrawable) d).getBitmap());
		return new BitmapDrawable(b);
	}
	

}
