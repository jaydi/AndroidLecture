package edu.lecture.android.practice;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {
    private String name;
    private int gender;
    private String birthday;
    private boolean agreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

//    public void showDatePicker(View view) {
//        DatePickerFragment datePicker = new DatePickerFragment();
//        datePicker.setOnDateSetListener(this);
//        datePicker.show(getSupportFragmentManager(), "datePicker");
//    }
//
//    @Override
//    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//        birthday = year + "/" + monthOfYear + "/" + dayOfMonth;
//        ((TextView) findViewById(R.id.text_input_birthday)).setText(birthday);
//    }

    public void handleParameters(View view) {
        EditText editName = (EditText) findViewById(R.id.edit_input_name);
        name = editName.getEditableText().toString();

//        RadioButton radioMale = (RadioButton) findViewById(R.id.radio_input_male);
//        gender = radioMale.isChecked() ? 10 : 20;
//
//        CheckBox checkAgreement = (CheckBox) findViewById(R.id.check_input_agreement);
//        agreement = checkAgreement.isChecked();

        Log.d("inputs", name);

//        goToProfile();
    }

//    private void goToProfile() {
//        Intent intent = new Intent(this, ProfileActivity.class);
//        intent.putExtra("name", name);
//        intent.putExtra("gender", gender);
//        intent.putExtra("birtyday", birthday);
//        intent.putExtra("agreement", agreement);
//        startActivity(intent);
//    }
}
