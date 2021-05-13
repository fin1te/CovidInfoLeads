package com.finite.covidinfoleads.Dashboard_Folder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.finite.covidinfoleads.Adapter.ch_adapter;
import com.finite.covidinfoleads.CovidData;
import com.finite.covidinfoleads.Model.chmodel;
import com.finite.covidinfoleads.R;
import com.finite.covidinfoleads.Resources;
import com.finite.covidinfoleads.Vaccine;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DB_Covid_Helplines extends AppCompatActivity {

    RecyclerView chrecview;
    ch_adapter chadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_b__covid__helplines);

        chrecview=(RecyclerView)findViewById(R.id.chrecview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
        chrecview.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_barch);

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
                    Intent intent = new Intent(DB_Covid_Helplines.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==1) {
                    Intent intent = new Intent(DB_Covid_Helplines.this, Resources.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2) {
                    Intent intent = new Intent(DB_Covid_Helplines.this, CovidData.class);
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
                    chrecview.scrollToPosition(position-1);
            }
        });

        FirebaseRecyclerOptions<chmodel> options =
                new FirebaseRecyclerOptions.Builder<chmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("CovidHelpline").orderByChild("chorder"), chmodel.class)
                        .build();
        chadapter = new ch_adapter(options);
        chrecview.setAdapter(chadapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        chadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        chadapter.stopListening();
    }

}