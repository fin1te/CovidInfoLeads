package com.finite.covidinfoleads;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.finite.covidinfoleads.Resource_Folder.All;
import com.finite.covidinfoleads.Resource_Folder.Beds;
import com.finite.covidinfoleads.Resource_Folder.Medicines;
import com.finite.covidinfoleads.Resource_Folder.Other;
import com.finite.covidinfoleads.Resource_Folder.Oxygen;
import com.finite.covidinfoleads.Resource_Folder.Plasma;

import java.util.Timer;
import java.util.TimerTask;

public class Resources extends AppCompatActivity {


    Timer t1,t2,t3,t4,t5,t6;
    FrameLayout f1,f2,f3,f4,f5,f6;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_vac, "Vaccines").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_res, "Resources").setActiveColor("#1C1C2E"))
                .addItem(new BottomNavigationItem(R.drawable.ic_covid, "Covid Data").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "Dashboard").setActiveColor("#8832E0"))
                .setFirstSelectedPosition(1)
                .initialise();

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_SHIFTING);

        f1 = findViewById(R.id.f1);
        f2 = findViewById(R.id.f2);
        f3 = findViewById(R.id.f3);
        f4 = findViewById(R.id.f4);
        f5 = findViewById(R.id.f5);
        f6 = findViewById(R.id.f6);

//        timer();


        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if(position==0) {
                    Intent intent = new Intent(Resources.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2) {
                    Intent intent = new Intent(Resources.this, CovidData.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==3) {
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
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Resources.this, Beds.class);
        startActivity(intent);
    }

    public void clickOxy(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Resources.this, Oxygen.class);
        startActivity(intent);
    }

    public void clickMeds(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Resources.this, Medicines.class);
        startActivity(intent);
    }

    public void clickPlasma(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Resources.this, Plasma.class);
        startActivity(intent);
    }

    public void clickOther(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Resources.this, Other.class);
        startActivity(intent);
    }

    public void clickAll(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Resources.this, All.class);
        startActivity(intent);
    }


//    private void timer() {
//        t1 = new Timer();
//        f1.animate().alpha(0f).setDuration(1);
//        t2 = new Timer();
//        f2.animate().alpha(0f).setDuration(1);
//        t3 = new Timer();
//        f3.animate().alpha(0f).setDuration(1);
//        t4 = new Timer();
//        f4.animate().alpha(0f).setDuration(1);
//        t5 = new Timer();
//        f5.animate().alpha(0f).setDuration(1);
//        t6 = new Timer();
//        f6.animate().alpha(0f).setDuration(1);
//
//        t1.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                f1.animate().alpha(1f).setDuration(300);
//            }
//        }, 300);
//
//        t2.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                f2.animate().alpha(1f).setDuration(300);
//            }
//        }, 300);
//
//        t3.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                f3.animate().alpha(1f).setDuration(300);
//            }
//        }, 600);
//
//        t4.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                f4.animate().alpha(1f).setDuration(300);
//            }
//        }, 600);
//
//        t5.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                f5.animate().alpha(1f).setDuration(300);
//            }
//        },900);
//
//        t6.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                f6.animate().alpha(1f).setDuration(300);
//            }
//        }, 900);
//    }

}