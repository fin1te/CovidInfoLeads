package com.finite.covidinfoleads.Dashboard_Folder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.finite.covidinfoleads.Adapter.sample_adapter;
import com.finite.covidinfoleads.CovidData;
import com.finite.covidinfoleads.Model.samplemodel;
import com.finite.covidinfoleads.R;
import com.finite.covidinfoleads.Resources;
import com.finite.covidinfoleads.Vaccine;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DB_HomeSampleRTPCR extends AppCompatActivity {

    RecyclerView samplerecview;
    sample_adapter sampleadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_b__home_sample_r_t_p_c_r);

        FloatingSearchView floatingSearchView = findViewById(R.id.rtpcrsearch);
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String s) {
                searchprocess(s);
            }
        });

        samplerecview=(RecyclerView)findViewById(R.id.samplerecview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
        samplerecview.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_barsample);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_vac, "Vaccines").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_res, "Resources").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_covid, "Covid Data").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "Dashboard").setActiveColor("#1C1C2E"))
                .setFirstSelectedPosition(3)
                .initialise();

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_SHIFTING);

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if(position==0) {
                    Intent intent = new Intent(DB_HomeSampleRTPCR.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==1) {
                    Intent intent = new Intent(DB_HomeSampleRTPCR.this, Resources.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2) {
                    Intent intent = new Intent(DB_HomeSampleRTPCR.this, CovidData.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
                if(position==1)
                    samplerecview.scrollToPosition(position-1);
            }
        });

        FirebaseRecyclerOptions<samplemodel> options =
                new FirebaseRecyclerOptions.Builder<samplemodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("homeSample").orderByChild("samplecity"), samplemodel.class)
                        .build();
        sampleadapter = new sample_adapter(options);
        samplerecview.setAdapter(sampleadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sampleadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sampleadapter.stopListening();
    }

    private void searchprocess(String s) {
        if(!s.isEmpty()) {
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        FirebaseRecyclerOptions<samplemodel> options =
                new FirebaseRecyclerOptions.Builder<samplemodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("homeSample").orderByChild("samplecity").startAt(s.toString()).endAt(s.toString()+"\uf8ff"), samplemodel.class)
                        .build();
        sampleadapter = new sample_adapter(options);
        samplerecview.setAdapter(sampleadapter);
        sampleadapter.startListening();
    }

}