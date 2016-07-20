package edu.lecture.android.practice.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;

import edu.lecture.android.practice.R;

public class DialogUtils {

	public interface BirthdayPickerListener {
		public void onBirthdaySet(int year, int month, int day);
	}

	public static Dialog showWaitingDialog(Context context) {
		ProgressDialog progressDlg = new ProgressDialog(context);
		progressDlg.setMessage(ResourceUtils.getString(R.string.waiting_message));
		progressDlg.setCancelable(false);
		progressDlg.show();
		return progressDlg;
	}

	public static void showBirthdayPicker(Context context, final BirthdayPickerListener listener) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View view = inflater.inflate(R.layout.dialog_birthday_picker_layout, null, false);

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(view);
		final Dialog dialog = builder.show();

		Button buttonCancel = (Button) view.findViewById(R.id.button_birthday_picker_confirm);
		buttonCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialog.dismiss();
				DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_birthday_picker_date);
				listener.onBirthdaySet(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
			}

		});
	}
}
