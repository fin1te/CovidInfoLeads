package com.finite.covidinfoleads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Timer t0,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    ImageView s1,s2,s3,s4,s5,s6,s7,s8,s9;
    //private static int SPLASH_TIME_OUT = 4000;

    private String version;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String appUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        CheckForUpdate();

        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s3 = findViewById(R.id.s3);
        s4 = findViewById(R.id.s4);
        s5 = findViewById(R.id.s5);
        s6 = findViewById(R.id.s6);
        s7 = findViewById(R.id.s7);
        s8 = findViewById(R.id.s8);
        s9 = findViewById(R.id.s9);

        timer();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, Dashboard.class);
//                startActivity(intent);
//                finish();
//            }
//        },SPLASH_TIME_OUT);

        //Intent intent = new Intent(MainActivity.this, Dashboard.class);
        //startActivity(intent);
        //finish();
//        t0 = new Timer();
//        t0.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(MainActivity.this, Dashboard.class);
//                startActivity(intent);
//                finish();
//            }
//        },4000);
    }

    private void timer() {
        t1 = new Timer();
        s1.animate().alpha(0f).setDuration(1);
        t2 = new Timer();
        s2.animate().alpha(0f).setDuration(1);
        t3 = new Timer();
        s3.animate().alpha(0f).setDuration(1);
        t4 = new Timer();
        s4.animate().alpha(0f).setDuration(1);
        t5 = new Timer();
        s5.animate().alpha(0f).setDuration(1);
        t6 = new Timer();
        s6.animate().alpha(0f).setDuration(1);
        t7 = new Timer();
        s7.animate().alpha(0f).setDuration(1);
        t8 = new Timer();
        s8.animate().alpha(0f).setDuration(1);
        t9 = new Timer();
        s9.animate().alpha(0f).setDuration(1);

        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                s1.animate().alpha(1f).setDuration(400);
            }
        }, 800);

        t2.schedule(new TimerTask() {
            @Override
            public void run() {
                s2.animate().alpha(1f).setDuration(200);
            }
        }, 1000);

        t3.schedule(new TimerTask() {
            @Override
            public void run() {
                s3.animate().alpha(1f).setDuration(200);
            }
        }, 1200);

        t4.schedule(new TimerTask() {
            @Override
            public void run() {
                s4.animate().alpha(1f).setDuration(200);
            }
        }, 1400);

        t5.schedule(new TimerTask() {
            @Override
            public void run() {
                s5.animate().alpha(1f).setDuration(200);
            }
        },1600);

        t6.schedule(new TimerTask() {
            @Override
            public void run() {
                s6.animate().alpha(1f).setDuration(200);
            }
        }, 1800);

        t7.schedule(new TimerTask() {
            @Override
            public void run() {
                s7.animate().alpha(1f).setDuration(200);
            }
        }, 2000);

        t8.schedule(new TimerTask() {
            @Override
            public void run() {
                s8.animate().alpha(1f).setDuration(200);
            }
        }, 2200);

        t9.schedule(new TimerTask() {
            @Override
            public void run() {
                s9.animate().alpha(1f).setDuration(200);
            }
        }, 2400);
    }

    private void CheckForUpdate() {
        try{
            version = this.getPackageManager().getPackageInfo(getPackageName(), 0).versionName;

            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("Version").child("versionNumber");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String versionName = (String) dataSnapshot.getValue();

                    if(!versionName.equals(version)){
                        //Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("New Version Available!")
                                .setMessage("Please update our app to the latest version for continuous use.")
                                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("Version").child("appUrl");
                                        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                appUrl = dataSnapshot.getValue().toString();
                                                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl)));
                                                finish();
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                })
                                .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                })
                                .create();

                        alertDialog.setCancelable(false);
                        alertDialog.setCanceledOnTouchOutside(false);

                        alertDialog.show();
                    }
                    else{
                        t0 = new Timer();
                        t0.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                                startActivity(intent);
                                finish();
                            }
                        },4000);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}