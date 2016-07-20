package edu.lecture.android.practice.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import edu.lecture.android.practice.application.GlobalApplication;
import edu.lecture.android.practice.connection.DrawableResponseHandler;
import edu.lecture.android.practice.models.ImageDrawable;
import edu.lecture.android.practice.threading.ImageFileLoadWork;
import edu.lecture.android.practice.threading.ThreadManager;
import edu.lecture.android.practice.threading.Work;

public class ResourceUtils {
	public static String getString(int resId) {
		return GlobalApplication.getInstance().getResources().getString(resId);
	}

	public static Drawable getDrawable(int resId) {
		return GlobalApplication.getInstance().getResources().getDrawable(resId);
	}

	public static void getImageFile(ImageView imageView, String url, int width, int height) {
		if (url == null || url.isEmpty())
			return;

		Work<ImageDrawable> work = new ImageFileLoadWork(url, width, height);
		ThreadManager.execute(work, new DrawableResponseHandler(imageView, true));
	}
}
