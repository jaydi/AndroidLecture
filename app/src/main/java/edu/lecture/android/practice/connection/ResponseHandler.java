package edu.lecture.android.practice.connection;

import android.os.Handler;
import android.os.Message;

public abstract class ResponseHandler<T> extends Handler {
	public ResponseHandler() {
		super();
	}

	@Override
	public void handleMessage(Message m) {
		onResponse((T) m.obj);
	}

	protected abstract void onResponse(T res);
}
