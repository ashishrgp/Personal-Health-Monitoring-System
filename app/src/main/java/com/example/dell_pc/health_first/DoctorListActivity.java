package com.example.dell_pc.health_first;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ashish pc on 10-Apr-17.
 */

public class DoctorListActivity extends MainActivity {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.noResultsImageView)
    ImageView mNoResultsImageView;
    @Bind(R.id.noResultsTextView)
    TextView mNoResultsTextView;

    public ArrayList<Doctor> mDoctors = new ArrayList<>();
    private DoctorListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_doctor);
        // super.onCreateDrawer();
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String specialty = intent.getStringExtra("specialty");
        String city = intent.getStringExtra("city");
        String state = intent.getStringExtra("state");
        getDoctors(specialty, city, state);


    }

    private void getDoctors(String specialty, String city, String state) {
        final BetterDoctorService betterDoctorService = new BetterDoctorService();

        betterDoctorService.findDoctorsByLocationAndSpecialty(specialty, city, state, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDoctors = betterDoctorService.processResults(response);

                DoctorListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter = new DoctorListAdapter(getApplicationContext(), mDoctors);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DoctorListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);

                        if (mDoctors.isEmpty()) {
                            mNoResultsImageView.setVisibility(View.VISIBLE);
                            mNoResultsTextView.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
    }
}