package edu.lecture.android.practice;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PagerActivity extends AppCompatActivity {
    ViewPager pagerGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        pagerGuide = (ViewPager) findViewById(R.id.pager_pager_guide);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPager();
    }

    private void initPager() {
        pagerGuide.setAdapter(new PagerAdapter() {
            LayoutInflater inflater = LayoutInflater.from(PagerActivity.this);

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = inflater.inflate(R.layout.layout_pager_content, null);
                TextView textPosition = (TextView) view.findViewById(R.id.text_pager_content_position);

                switch(position) {
                    case 0:
                        textPosition.setText("First");
                        break;
                    case 1:
                        textPosition.setText("Second");
                        break;
                    case 2:
                        textPosition.setText("Third");
                        break;
                }

                container.addView(view, 0);

                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });
    }

}
