package com.finite.covidinfoleads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DB_Hospital_Contacts extends AppCompatActivity {

    RecyclerView hosprecview;
    hosp_adapter hospadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_b__hospital__contacts);

        hosprecview=(RecyclerView)findViewById(R.id.hosprecview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
        hosprecview.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_barhosp);

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
                    Intent intent = new Intent(DB_Hospital_Contacts.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==1) {
                    Intent intent = new Intent(DB_Hospital_Contacts.this, Resources.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2) {
                    Intent intent = new Intent(DB_Hospital_Contacts.this, CovidData.class);
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
                    hosprecview.scrollToPosition(position-1);
            }
        });

        FirebaseRecyclerOptions<hospmodel> options =
                new FirebaseRecyclerOptions.Builder<hospmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("hospitalContacts").orderByChild("hospcity"), hospmodel.class)
                        .build();
        hospadapter = new hosp_adapter(options);
        hosprecview.setAdapter(hospadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        hospadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        hospadapter.stopListening();
    }
}