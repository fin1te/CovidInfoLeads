package com.finite.covidinfoleads.Dashboard_Folder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.finite.covidinfoleads.Adapter.doc_adapter;
import com.finite.covidinfoleads.Adapter.myadapter;
import com.finite.covidinfoleads.CovidData;
import com.finite.covidinfoleads.Model.docmodel;
import com.finite.covidinfoleads.Model.model;
import com.finite.covidinfoleads.R;
import com.finite.covidinfoleads.Vaccine;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DB_DoctorConsult extends AppCompatActivity {

    RecyclerView docrecview;
    doc_adapter docadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_b__doctor_consult);

        FloatingSearchView floatingSearchView = findViewById(R.id.docsearch);
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String s) {
                searchprocess(s);
            }
        });

        docrecview=(RecyclerView)findViewById(R.id.docrecview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
        docrecview.setLayoutManager(new LinearLayoutManager(this));

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bardoc);

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
                    Intent intent = new Intent(DB_DoctorConsult.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==1) {
                    Intent intent = new Intent(DB_DoctorConsult.this, Resources.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2) {
                    Intent intent = new Intent(DB_DoctorConsult.this, CovidData.class);
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
                    docrecview.scrollToPosition(position-1);
            }
        });

        FirebaseRecyclerOptions<docmodel> options =
                new FirebaseRecyclerOptions.Builder<docmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doctorConsultation").orderByChild("docName"), docmodel.class)
                        .build();
        docadapter = new doc_adapter(options);
        docrecview.setAdapter(docadapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        docadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        docadapter.stopListening();
    }
    private void searchprocess(String s) {
        if(!s.isEmpty()) {
            s = s.substring(0, 1).toUpperCase() + s.substring(1);
        }
        FirebaseRecyclerOptions<docmodel> options =
                new FirebaseRecyclerOptions.Builder<docmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("doctorConsultation").orderByChild("docName").startAt(s.toString()).endAt(s.toString()+"\uf8ff"), docmodel.class)
                        .build();
        docadapter = new doc_adapter(options);
        docrecview.setAdapter(docadapter);
        docadapter.startListening();
    }
}