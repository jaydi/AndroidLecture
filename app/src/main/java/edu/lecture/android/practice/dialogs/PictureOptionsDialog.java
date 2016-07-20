package edu.lecture.android.practice.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import edu.lecture.android.practice.R;

public class PictureOptionsDialog extends DialogFragment {
	private OnPictureOptionClickListener listener;

	public interface OnPictureOptionClickListener {
		public void onPictureOptionClick(int position);
	}

	public void setOnPictureOptionClickListener(OnPictureOptionClickListener listener) {
		this.listener = listener;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setItems(R.array.picture_options, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				listener.onPictureOptionClick(which);
			}

		});

		return builder.create();
	}
}
