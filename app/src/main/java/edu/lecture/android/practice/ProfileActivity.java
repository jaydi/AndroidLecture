package edu.lecture.android.practice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ProfileActivity extends AppCompatActivity {
    private String name;
    private int gender;
    private String birthday;
    private boolean agreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = getIntent().getStringExtra("name");
        gender = getIntent().getIntExtra("gender", 10);
        birthday = getIntent().getStringExtra("birthday");
        agreement = getIntent().getBooleanExtra("agreement", false);
        
        showParameters();
    }

    private void showParameters() {
        // TODO
    }

}
