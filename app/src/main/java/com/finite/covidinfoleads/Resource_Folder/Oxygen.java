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

public class Oxygen extends AppCompatActivity {

    RecyclerView oxyrecview;
    myadapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oxygen);


        FloatingSearchView floatingSearchView = findViewById(R.id.oxysearch);
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String s) {
                searchprocess(s);
            }
        });



        oxyrecview=(RecyclerView)findViewById(R.id.oxyrecview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
        oxyrecview.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_baroxy);

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
                    Intent intent = new Intent(Oxygen.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==1)
                    oxyrecview.scrollToPosition(position-1);
                else if(position==2) {
                    Intent intent = new Intent(Oxygen.this, CovidData.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==3) {
                    Intent intent = new Intent(Oxygen.this, Dashboard.class);
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
                    oxyrecview.scrollToPosition(position-1);
            }
        });

        FirebaseRecyclerOptions<model> options =
                new FirebaseRecyclerOptions.Builder<model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Oxygen").orderByChild("reversetoken"), model.class)
                        .build();
        adapter = new myadapter(options);
        oxyrecview.setAdapter(adapter);
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
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Oxygen").orderByChild("cityName").startAt(s.toString()).endAt(s.toString()+"\uf8ff"), model.class)
                        .build();
        adapter = new myadapter(options);
        adapter.startListening();
        oxyrecview.setAdapter(adapter);
    }
}