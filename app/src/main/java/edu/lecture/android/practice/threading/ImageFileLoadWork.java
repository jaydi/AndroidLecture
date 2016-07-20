package edu.lecture.android.practice.threading;

import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import edu.lecture.android.practice.application.GlobalApplication;
import edu.lecture.android.practice.models.ImageDrawable;
import edu.lecture.android.practice.utils.BitmapUtils;

public class ImageFileLoadWork extends Work<ImageDrawable> {
	private String path;
	private int width;
	private int height;

	public ImageFileLoadWork(String path, int width, int height) {
		super();
		this.path = path;
		this.width = width;
		this.height = height;
	}

	@Override
	public ImageDrawable work() throws IOException {
		Bitmap bitmap = BitmapUtils.decodeFileScaledDown(path, width, height);
		bitmap = BitmapUtils.fixOrientation(path, bitmap);
		BitmapDrawable drawable = new BitmapDrawable(GlobalApplication.getInstance().getResources(), bitmap);
		return new ImageDrawable(drawable, true);
	}
}
