package com.example.android.ccv_drag_test;

import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager)findViewById(R.id.container);

        // setup the pager
        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "Fragment1");
        adapter.addFragment(new Fragment2(), "Fragment2");

        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber)
    {
        mViewPager.setCurrentItem(fragmentNumber);
    }
    public int getCurrentViewPager()
    {
        return mViewPager.getCurrentItem();
    }

}