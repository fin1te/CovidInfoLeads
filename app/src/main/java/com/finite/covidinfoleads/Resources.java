package com.finite.covidinfoleads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Resources extends AppCompatActivity {


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_vac, "Vaccines").setActiveColor("#ff0066"))
                .addItem(new BottomNavigationItem(R.drawable.ic_res, "Resources").setActiveColor("#ff0066"))
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "Dashboard").setActiveColor("#ff0066"))
                .setFirstSelectedPosition(1)
                .initialise();

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_SHIFTING);




        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if(position==0) {
                    Intent intent = new Intent(Resources.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2) {
                    Intent intent = new Intent(Resources.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onTabUnselected(int position) {
            }
            @Override
            public void onTabReselected(int position) {
            }
        });

    }

    public void clickBeds(View view) {
        Intent intent = new Intent(Resources.this, Beds.class);
        startActivity(intent);
    }

    public void clickOxy(View view) {
        Intent intent = new Intent(Resources.this, Oxygen.class);
        startActivity(intent);
    }

    public void clickMeds(View view) {
        Intent intent = new Intent(Resources.this, Medicines.class);
        startActivity(intent);
    }

    public void clickPlasma(View view) {
        Intent intent = new Intent(Resources.this, Plasma.class);
        startActivity(intent);
    }

    public void clickOther(View view) {
        Intent intent = new Intent(Resources.this, Other.class);
        startActivity(intent);
    }

    public void clickAll(View view) {
        Intent intent = new Intent(Resources.this, All.class);
        startActivity(intent);
    }
}