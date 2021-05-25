package com.finite.covidinfoleads;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Vaccine extends AppCompatActivity {

    EditText vac_pincode;
    TextView vacdatepicker;
    int year,month,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine);

        vacdatepicker = findViewById(R.id.vacdatepicker);
        Calendar calendar = Calendar.getInstance();

        vacdatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                date = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Vaccine.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        vacdatepicker.setText(dayOfMonth+"-"+month+"-"+year);
                        SharedPreferences sh = getSharedPreferences("shCurrent", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sh.edit();
                        editor.putString("date",(dayOfMonth+"-"+month+"-"+year));
                        editor.apply();

                    }
                }, year,month,date);
                datePickerDialog.show();
            }
        });


        vac_pincode = findViewById(R.id.vac_pincode);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_vac, "Vaccines").setActiveColor("#1C1C2E"))
                .addItem(new BottomNavigationItem(R.drawable.ic_res, "Resources").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_covid, "Covid Data").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "Dashboard").setActiveColor("#8832E0"))
                .setFirstSelectedPosition(0)
                .initialise();

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_SHIFTING);




        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if(position==1) {
                    Intent intent = new Intent(Vaccine.this, Resources.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==2) {
                    Intent intent = new Intent(Vaccine.this, CovidData.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==3) {
                    Intent intent = new Intent(Vaccine.this, Dashboard.class);
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

    public void clickvaccheck(View view) {
        String pincode = vac_pincode.getText().toString();


        if(pincode.length()!= 6) {
            Toast.makeText(this, "Please enter Correct Pincode!", Toast.LENGTH_SHORT).show();
        }
        else if((vacdatepicker.getText().toString()).isEmpty()) {
            Toast.makeText(this, "Please Select a Date", Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences sh = getSharedPreferences("shCurrent", MODE_PRIVATE);
            SharedPreferences.Editor editor = sh.edit();
            editor.putString("pincode",pincode);
            editor.apply();

            Intent intent = new Intent(Vaccine.this, VacHome.class);
            startActivity(intent);
        }
    }

}