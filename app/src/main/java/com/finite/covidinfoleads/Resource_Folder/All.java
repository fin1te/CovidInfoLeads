package com.finite.covidinfoleads.Resource_Folder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.finite.covidinfoleads.Adapter.myadapter;
import com.finite.covidinfoleads.CovidData;
import com.finite.covidinfoleads.Dashboard;
import com.finite.covidinfoleads.Model.model;
import com.finite.covidinfoleads.R;
import com.finite.covidinfoleads.Vaccine;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class All extends AppCompatActivity {

    RecyclerView allrecview;
    myadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        FloatingSearchView floatingSearchView = findViewById(R.id.allsearch);
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String s) {
                searchprocess(s);
            }
        });

        allrecview=(RecyclerView)findViewById(R.id.allrecview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
        allrecview.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_barall);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_vac, "Vaccines").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_res, "Resources").setActiveColor("#1C1C2E"))
                .addItem(new BottomNavigationItem(R.drawable.ic_covid, "Covid Data").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "Dashboard").setActiveColor("#8832E0"))
                .setFirstSelectedPosition(1)
                .initialise();

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_SHIFTING);

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if(position==0) {
                    Intent intent = new Intent(All.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==1)
                    allrecview.scrollToPosition(position-1);
                else if(position==2) {
                    Intent intent = new Intent(All.this, CovidData.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==3) {
                    Intent intent = new Intent(All.this, Dashboard.class);
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
                    allrecview.scrollToPosition(position-1);
            }
        });

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("posts").orderByChild("reversetoken"), model.class)
                        .build();
        adapter = new myadapter(options);
        allrecview.setAdapter(adapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
    private void searchprocess(String s) {
        if(!s.isEmpty()) {
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("posts").orderByChild("cityName").startAt(s.toString()).endAt(s.toString()+"\uf8ff"), model.class)
                        .build();
        adapter = new myadapter(options);
        allrecview.setAdapter(adapter);
        adapter.startListening();
    }
}