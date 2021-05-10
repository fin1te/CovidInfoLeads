package com.finite.covidinfoleads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class CovidData extends AppCompatActivity {

    FrameLayout f1,f2,f3,f4,f5,f6;
    Timer t1,t2,t3,t4,t5,t6;

    private String version;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private String appUrl;
    private TextView tv_confirmed, tv_confirmed_new, tv_active, tv_active_new, tv_recovered, tv_recovered_new, tv_death,
            tv_death_new, tv_tests, tv_tests_new, tv_date, tv_time;

    private SwipeRefreshLayout swipeRefreshLayout;

    private PieChart pieChart;

    private LinearLayout lin_state_data, lin_world_data;

    private String str_confirmed, str_confirmed_new, str_active, str_active_new, str_recovered, str_recovered_new,
            str_death, str_death_new, str_tests, str_tests_new, str_last_update_time;
    private int int_active_new;
    private ProgressDialog progressDialog;
    private boolean doubleBackToExitPressedOnce = false;
    private Toast backPressToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_data);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_vac, "Vaccines").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_res, "Resources").setActiveColor("#8832E0"))
                .addItem(new BottomNavigationItem(R.drawable.ic_covid, "Covid Data").setActiveColor("#1C1C2E"))
                .addItem(new BottomNavigationItem(R.drawable.ic_home, "Dashboard").setActiveColor("#8832E0"))
                .setFirstSelectedPosition(2)
                .initialise();

        bottomNavigationBar
                .setMode(BottomNavigationBar.MODE_SHIFTING);

        f1 = findViewById(R.id.d1);
        f2 = findViewById(R.id.d2);
        f3 = findViewById(R.id.d3);
        f4 = findViewById(R.id.d4);
        f5 = findViewById(R.id.d5);
        f6 = findViewById(R.id.d6);

        timer();




        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                if(position==0) {
                    Intent intent = new Intent(CovidData.this, Vaccine.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==1) {
                    Intent intent = new Intent(CovidData.this, Resources.class);
                    startActivity(intent);
                    finish();
                }
                else if(position==3) {
                    Intent intent = new Intent(CovidData.this, Dashboard.class);
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


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Initialise
        Init();

        //Fetch data from API
        FetchData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FetchData();
                timer();
                swipeRefreshLayout.setRefreshing(false);
                //Toast.makeText(MainActivity.this, "Data refreshed!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Init() {
        tv_confirmed = findViewById(R.id.activity_main_confirmed_textview);
        tv_confirmed_new = findViewById(R.id.activity_main_confirmed_new_textview);
        tv_active = findViewById(R.id.activity_main_active_textview);
        tv_active_new = findViewById(R.id.activity_main_active_new_textview);
        tv_recovered = findViewById(R.id.activity_main_recovered_textview);
        tv_recovered_new = findViewById(R.id.activity_main_recovered_new_textview);
        tv_death = findViewById(R.id.activity_main_death_textview);
        tv_death_new = findViewById(R.id.activity_main_death_new_textview);
        tv_tests = findViewById(R.id.activity_main_samples_textview);
        tv_tests_new = findViewById(R.id.activity_main_samples_new_textview);
        tv_date = findViewById(R.id.activity_main_date_textview);
        tv_time = findViewById(R.id.activity_main_time_textview);
        pieChart = findViewById(R.id.activity_main_piechart);
        swipeRefreshLayout = findViewById(R.id.activity_main_swipe_refresh_layout);
    }

    public void ShowDialog(Context context) {
        //setting up progress dialog
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public void DismissDialog() {
        progressDialog.dismiss();
    }

    public String FormatDate(String date, int testCase) {
        Date mDate = null;
        String dateFormat;
        try {
            mDate = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.US).parse(date);
            if (testCase == 0) {
                dateFormat = new SimpleDateFormat("dd MMM yyyy, hh:mm a").format(mDate);
                return dateFormat;
            } else if (testCase == 1) {
                dateFormat = new SimpleDateFormat("dd MMM yyyy").format(mDate);
                return dateFormat;
            } else if (testCase == 2) {
                dateFormat = new SimpleDateFormat("hh:mm a").format(mDate);
                return dateFormat;
            } else {
                Log.d("error", "Wrong input! Choose from 0 to 2");
                return "Error";
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return date;
        }
    }

    private void FetchData() {

        //show progress dialog
        ShowDialog(this);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String apiUrl = "https://api.covid19india.org/data.json";

        pieChart.clearChart();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                apiUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //As the data of the json are in a nested array, so we need to define the array from which we want to fetch the data.
                        JSONArray all_state_jsonArray = null;
                        JSONArray testData_jsonArray = null;
                        JSONArray delta_jsonArray = null;

                        try {
                            all_state_jsonArray = response.getJSONArray("statewise");
                            testData_jsonArray = response.getJSONArray("tested");

                            delta_jsonArray = response.getJSONArray("cases_time_series");

                            JSONObject data_india = all_state_jsonArray.getJSONObject(0);
                            JSONObject test_data_india = testData_jsonArray.getJSONObject(testData_jsonArray.length()-1);

                            JSONObject delta_data_india = delta_jsonArray.getJSONObject(delta_jsonArray.length()-1);


                            //Fetching data for India and storing it in String
                            str_confirmed = data_india.getString("confirmed");   //Confirmed cases in India


                            str_active = data_india.getString("active");    //Active cases in India

                            str_recovered = data_india.getString("recovered");  //Total recovered cased in India


                            str_death = data_india.getString("deaths");     //Total deaths in India


                            str_last_update_time = data_india.getString("lastupdatedtime"); //Last update date and time

                            str_tests = test_data_india.getString("totalrtpcrsamplescollectedicmrapplication"); //Total samples tested in India



                            // --------------------------------------------------
                            str_confirmed_new = delta_data_india.getString("dailyconfirmed");   //New Confirmed cases from last update time
                            str_recovered_new = delta_data_india.getString("dailyrecovered"); //New recovered cases from last update time
                            str_death_new = delta_data_india.getString("dailydeceased");    //New death cases from last update time
                            str_tests_new = test_data_india.getString("dailyrtpcrsamplescollectedicmrapplication");   //New samples tested today

                            Handler delayToshowProgess = new Handler();

                            delayToshowProgess.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //Setting text in the textview
                                    //tv_confirmed.setText(NumberFormat.getInstance().format(Integer.parseInt(str_confirmed)));
                                    tv_confirmed.setText(new DecimalFormat("##,##,##0").format(Integer.parseInt(str_confirmed)));
                                    tv_confirmed_new.setText("+" + new DecimalFormat("##,##,##0").format(Integer.parseInt(str_confirmed_new)));


                                    tv_active.setText(new DecimalFormat("##,##,##0").format(Integer.parseInt(str_active)));

                                    int_active_new = Integer.parseInt(str_confirmed_new)
                                            - (Integer.parseInt(str_recovered_new) + Integer.parseInt(str_death_new));
                                    tv_active_new.setText("+"+new DecimalFormat("##,##,##0").format(int_active_new));

                                    tv_recovered.setText(new DecimalFormat("##,##,##0").format(Integer.parseInt(str_recovered)));
                                    tv_recovered_new.setText("+"+new DecimalFormat("##,##,##0").format(Integer.parseInt(str_recovered_new)));

                                    tv_death.setText(new DecimalFormat("##,##,##0").format(Integer.parseInt(str_death)));
                                    tv_death_new.setText("+"+new DecimalFormat("##,##,##0").format(Integer.parseInt(str_death_new)));

                                    tv_tests.setText(new DecimalFormat("##,##,##0").format(Integer.parseInt(str_tests)));
                                    tv_tests_new.setText("+"+new DecimalFormat("##,##,##0").format(Integer.parseInt(str_tests_new)));

                                    tv_date.setText(FormatDate(str_last_update_time, 1));
                                    tv_time.setText(FormatDate(str_last_update_time, 2));

                                    pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(str_active), Color.parseColor("#007BFF")));
                                    pieChart.addPieSlice(new PieModel("Recovered", Integer.parseInt(str_recovered), Color.parseColor("#FF2FFF5C")));
                                    pieChart.addPieSlice(new PieModel("Deceased", Integer.parseInt(str_death), Color.parseColor("#F6404F")));

                                    pieChart.startAnimation();

                                    DismissDialog();

                                }
                            }, 1000);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    public void clickConfirmed(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Total Confirmed COVID Cases in India", Toast.LENGTH_SHORT).show();
    }

    public void clickActive(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Total Active COVID Cases in India", Toast.LENGTH_SHORT).show();
    }

    public void clickRecovered(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Total Recovered Patients in India", Toast.LENGTH_SHORT).show();
    }

    public void clickDeaths(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Total Deaths due to COVID in India", Toast.LENGTH_SHORT).show();
    }

    public void clickTested(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Number of COVID Testings Done", Toast.LENGTH_SHORT).show();
    }

    public void clickLastUpdated(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "The above data was last updated on this time", Toast.LENGTH_SHORT).show();
    }

    public void clickGraph(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "A Graph Visualising the below data", Toast.LENGTH_SHORT).show();
    }

    private void timer() {
        t1 = new Timer();
        f1.animate().alpha(0f).setDuration(1);
        t2 = new Timer();
        f2.animate().alpha(0f).setDuration(1);
        t3 = new Timer();
        f3.animate().alpha(0f).setDuration(1);
        t4 = new Timer();
        f4.animate().alpha(0f).setDuration(1);
        t5 = new Timer();
        f5.animate().alpha(0f).setDuration(1);
        t6 = new Timer();
        f6.animate().alpha(0f).setDuration(1);

        t1.schedule(new TimerTask() {
            @Override
            public void run() {
                f1.animate().alpha(1f).setDuration(500);
            }
        }, 1500);

        t2.schedule(new TimerTask() {
            @Override
            public void run() {
                f2.animate().alpha(1f).setDuration(500);
            }
        }, 2000);

        t3.schedule(new TimerTask() {
            @Override
            public void run() {
                f3.animate().alpha(1f).setDuration(500);
            }
        }, 2500);

        t4.schedule(new TimerTask() {
            @Override
            public void run() {
                f4.animate().alpha(1f).setDuration(500);
            }
        }, 3000);

        t5.schedule(new TimerTask() {
            @Override
            public void run() {
                f5.animate().alpha(1f).setDuration(500);
            }
        },3500);

        t6.schedule(new TimerTask() {
            @Override
            public void run() {
                f6.animate().alpha(1f).setDuration(500);
            }
        }, 4000);
    }

}