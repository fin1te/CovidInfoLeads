package com.finite.covidinfoleads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.finite.covidinfoleads.Dashboard_Folder.AboutUs;
import com.finite.covidinfoleads.Dashboard_Folder.Announcements;
import com.finite.covidinfoleads.Dashboard_Folder.Blogs;
import com.finite.covidinfoleads.Dashboard_Folder.ContactUs;
import com.finite.covidinfoleads.Dashboard_Folder.Credits;
import com.finite.covidinfoleads.Dashboard_Folder.DB_Covid_Helplines;
import com.finite.covidinfoleads.Dashboard_Folder.DB_DoctorConsult;
import com.finite.covidinfoleads.Dashboard_Folder.DB_HomeSampleRTPCR;
import com.finite.covidinfoleads.Dashboard_Folder.DB_Hospital_Contacts;
import com.finite.covidinfoleads.Dashboard_Folder.Disclaimer;

import java.util.Timer;
import java.util.TimerTask;

public class Dashboard extends AppCompatActivity {

    GridLayout db_grid_1,db_grid_3,db_grid_4,db_grid_5;
    FrameLayout db_grid_2;
    Timer t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        db_grid_1 = findViewById(R.id.db_grid_1);
        db_grid_2 = findViewById(R.id.db_grid_2);
        db_grid_3 = findViewById(R.id.db_grid_3);
        db_grid_4 = findViewById(R.id.db_grid_4);
        db_grid_5 = findViewById(R.id.db_grid_5);

        db_grid_1.animate().alpha(0f);

        timer();

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

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
                    Intent intent = new Intent(Dashboard.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==1) {
                    Intent intent = new Intent(Dashboard.this, Resources.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2) {
                    Intent intent = new Intent(Dashboard.this, CovidData.class);
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

    public void clickDoctorcons(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, DB_DoctorConsult.class);
        startActivity(intent);
    }

    public void clickHospitalContacts(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, DB_Hospital_Contacts.class);
        startActivity(intent);
    }

    public void clickHomeSample(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, DB_HomeSampleRTPCR.class);
        startActivity(intent);
    }

    public void clickCovidHelplines(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, DB_Covid_Helplines.class);
        startActivity(intent);
    }

    public void clickTwitter(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Welcome to our Twitter Handle! ^_^", Toast.LENGTH_LONG).show();
        goToUrl("https://www.twitter.com/covidinfoleads/");
    }

    public void clickPlasma(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Donate Plasma, Save a Life! <3 \n Please Fill this Form", Toast.LENGTH_LONG).show();
        goToUrl("https://docs.google.com/forms/d/e/1FAIpQLSfpj_DfBwbKLGj3BPb6R38mSAC-cg8-wWufXZt55TylQW_GQQ/viewform?usp=sf_link");
    }

    public void clickVolunteer(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Thanks for showing Interest, Please Fill this Form", Toast.LENGTH_LONG).show();
        goToUrl("https://docs.google.com/forms/d/e/1FAIpQLSePDj7jfFZfxZaQBR31PgYnlOB39MwObFeBvpQm5DHwpvCntQ/viewform?usp=sf_link");
    }


    private void goToUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void clickAnnouncements(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, Announcements.class);
        startActivity(intent);
    }

    public void clickBlogs(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, Blogs.class);
        startActivity(intent);
    }

    public void clickAbout(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, AboutUs.class);
        startActivity(intent);
    }

    public void clickCredits(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, Credits.class);
        startActivity(intent);
    }

    public void clickContactUs(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, ContactUs.class);
        startActivity(intent);
    }

    public void clickDisclaimer(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Intent intent = new Intent(Dashboard.this, Disclaimer.class);
        startActivity(intent);
    }


    private void timer() {
        t1 = new Timer();
        db_grid_1.animate().alpha(0f).setDuration(1);
        t2 = new Timer();
        db_grid_2.animate().alpha(0f).setDuration(1);
        t3 = new Timer();
        db_grid_3.animate().alpha(0f).setDuration(1);
        t4 = new Timer();
        db_grid_4.animate().alpha(0f).setDuration(1);
        t5 = new Timer();
        db_grid_5.animate().alpha(0f).setDuration(1);

        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                db_grid_1.animate().alpha(1f).setDuration(500);
            }
        }, 500);

        t2.schedule(new TimerTask() {
            @Override
            public void run() {
                db_grid_2.animate().alpha(1f).setDuration(500);
            }
        }, 1000);

        t3.schedule(new TimerTask() {
            @Override
            public void run() {
                db_grid_3.animate().alpha(1f).setDuration(500);
            }
        }, 1500);

        t4.schedule(new TimerTask() {
            @Override
            public void run() {
                db_grid_4.animate().alpha(1f).setDuration(500);
            }
        }, 2000);

        t5.schedule(new TimerTask() {
            @Override
            public void run() {
                db_grid_5.animate().alpha(1f).setDuration(500);
            }
        }, 2500);
    }

}