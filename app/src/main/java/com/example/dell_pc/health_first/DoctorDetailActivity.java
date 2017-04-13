package com.example.dell_pc.health_first;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ashish pc on 10-Apr-17.
 */

public class DoctorDetailActivity extends MainActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private DoctorPagerAdapter adapterViewPager;
    ArrayList<Doctor> mDoctors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        //super.onCreateDrawer();
        ButterKnife.bind(this);

        mDoctors = Parcels.unwrap(getIntent().getParcelableExtra("doctors"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        adapterViewPager = new DoctorPagerAdapter(getSupportFragmentManager(), mDoctors);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
