package edu.lecture.android.practice.connection;

import android.graphics.Bitmap;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import edu.lecture.android.practice.R;
import edu.lecture.android.practice.application.GlobalApplication;
import edu.lecture.android.practice.models.ImageDrawable;
import edu.lecture.android.practice.utils.BitmapUtils;

public class DrawableResponseHandler extends ResponseHandler<ImageDrawable> {
	private ImageView imageView;
	private boolean isCircular;

	public DrawableResponseHandler(ImageView imageView, boolean isCircular) {
		super();
		this.imageView = imageView;
		this.isCircular = isCircular;
	}

	@Override
	protected void onResponse(ImageDrawable res) {
		if (imageView == null || res == null)
			return;

		if (res.isFadeIn()) {
			Animation fadeIn = AnimationUtils.loadAnimation(GlobalApplication.getInstance(), R.anim.fade_in);
			imageView.startAnimation(fadeIn);
		}

		if (isCircular) {
			Bitmap b = res.getDrawable().getBitmap();
			imageView.setImageBitmap(BitmapUtils.cropCircularView(b));
		} else
			imageView.setImageDrawable(res.getDrawable());
	}
}
