package edu.lecture.android.practice;

import java.util.Calendar;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import edu.lecture.android.practice.dialogs.PictureOptionsDialog;
import edu.lecture.android.practice.utils.DialogUtils;
import edu.lecture.android.practice.utils.ResourceUtils;
import edu.lecture.android.practice.utils.ToastUtils;

public class EditProfileActivity extends AppCompatActivity implements PictureOptionsDialog.OnPictureOptionClickListener, DialogUtils.BirthdayPickerListener {
    private static final int GENDER_MALE = 1;
    private static final int GENDER_FEMALE = 2;
    private static final int REQUEST_GET_IMAGE = 0;

    private String imagePath;
    private String name;
    private long bday;
    private int gender;

    private Dialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
    }

    public void getImage(View view) {
        PictureOptionsDialog dialog = new PictureOptionsDialog();
        dialog.setOnPictureOptionClickListener(this);
        dialog.show(getSupportFragmentManager(), "pictureOptions");
    }

    @Override
    public void onPictureOptionClick(int position) {
        Intent intent;

        if (position == 0) {
            intent = new Intent(Intent.ACTION_PICK, Media.EXTERNAL_CONTENT_URI);
        } else {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        }

        PackageManager manager = getPackageManager();
        List<ResolveInfo> activities = manager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

        if (isIntentSafe)
            startActivityForResult(intent, REQUEST_GET_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GET_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri image = data.getData();
            String[] projection = { Media.DATA };

            Cursor cursor = getContentResolver().query(image, projection, null, null, null);
            cursor.moveToFirst();

            int column = cursor.getColumnIndex(Media.DATA);
            imagePath = cursor.getString(column);

            cursor.close();

            setImage();
        }
    }

    private void setImage() {
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageView imageImage = (ImageView) findViewById(R.id.image_edit_profile_image);
            ResourceUtils.getImageFile(imageImage, imagePath, 300, 300);
        }
    }

    public void chooseBday(View view) {
        DialogUtils.showBirthdayPicker(this, this);
    }

    @Override
    public void onBirthdaySet(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        bday = c.getTimeInMillis();
        setBday();
    }

    private void setBday() {
        if (bday != 0) {
            TextView textBday = (TextView) findViewById(R.id.text_edit_profile_bday);
            textBday.setText(DateUtils.getRelativeTimeSpanString(bday));
        }
    }

    public void chooseGender(View view) {
        switch (view.getId()) {
            case R.id.image_edit_profile_male:
                gender = GENDER_MALE;
                break;
            case R.id.image_edit_profile_female:
                gender = GENDER_FEMALE;
                break;
        }
        setGender();
    }

    private void setGender() {
        ImageView imageMale = (ImageView) findViewById(R.id.image_edit_profile_male);
        ImageView imageFemale = (ImageView) findViewById(R.id.image_edit_profile_female);

        imageMale.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_selected_not));
        imageFemale.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_selected_not));

        if (gender == GENDER_MALE)
            imageMale.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_selected));
        else if (gender == GENDER_FEMALE)
            imageFemale.setImageDrawable(ResourceUtils.getDrawable(R.drawable.ic_selected));
    }

    public void confirmProfile(View view) {
        EditText editName = (EditText) findViewById(R.id.edit_edit_profile_nickname);
        name = editName.getEditableText().toString();

        // validation

		if (imagePath == null || imagePath.isEmpty()) {
			ToastUtils.show(R.string.input_info_guide);
			return;
		}

        if (name == null || name.isEmpty()) {
            ToastUtils.show(R.string.input_info_guide);
            return;
        }

        if (bday == 0) {
            ToastUtils.show(R.string.input_info_guide);
            return;
        }

        if (gender == 0) {
            ToastUtils.show(R.string.input_info_guide);
            return;
        }

        progress = DialogUtils.showWaitingDialog(this);

//        sendProfileWithImage();
    }

//    private void sendProfileWithImage() {
//        NetworkInter.uploadImage(new ResponseHandler<String>() {
//
//            @Override
//            protected void onResponse(String imageKey) {
//                imagePath = imageKey;
//                sendProfile();
//            }
//
//        }, imagePath);
//    }

//    private void sendProfile() {
//        User user = LocalUser.getUser();
//        user.setImageKey(imagePath);
//        user.setName(name);
//        user.setBday(bday);
//        user.setGender(gender);
//
//        NetworkInter.updateUser(new ResponseHandler<User>(DialogUtils.showWaitingDialog(this)) {
//
//            @Override
//            protected void onResponse(User res) {
//                if (res != null)
//                    LocalUser.setUser(res);
//
//                if (fromEdit)
//                    finish();
//                else
//                    goToMain();
//            }
//
//        }, user);
//    }

//    private void goToMain() {
//        if (progress != null && progress.isShowing())
//            progress.dismiss();
//
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//    }

}