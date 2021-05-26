package com.finite.covidinfoleads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.load.model.Model;
import com.finite.covidinfoleads.Adapter.doc_adapter;
import com.finite.covidinfoleads.Adapter.vac_adapter;
import com.finite.covidinfoleads.Model.vacmodel;

import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class VacHome extends AppCompatActivity {

    private RecyclerView vacreview;
    private ArrayList<vacmodel> arrayList;
    private ProgressDialog progressDialog;
    vac_adapter vacadapter;
    Timer t1;

    private ArrayList<vacmodel> centerList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vac_home);

        vacreview = findViewById(R.id.vacrecview);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        vacreview.setLayoutManager(new LinearLayoutManager(this));

        arrayList = new ArrayList<>();


        FetchData();

        //set
        //vac_adapter vac_adapter = new vac_adapter(arrayList);
        //vacreview.setAdapter(vac_adapter);



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
        //ShowDialog(this);

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        SharedPreferences sh = getSharedPreferences("shCurrent", MODE_PRIVATE);
        String pincode = sh.getString("pincode","");
        String date = sh.getString("date","");

        String apiUrl = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode="+pincode+"&date="+date;


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

                        JSONArray centers_jsonArray = null;

                        try {
                            //all_state_jsonArray = response.getJSONArray("statewise");
                            //testData_jsonArray = response.getJSONArray("tested");
                            //delta_jsonArray = response.getJSONArray("cases_time_series");
                            centers_jsonArray = response.getJSONArray("centers");

                            if (centers_jsonArray.length() == 0) {

                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);

                                //inflate view
                                View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
                                ((TextView) custom_view.findViewById(R.id.message)).setText("No Centers Available!");
                                ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_close);
                                ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.red_pie));

                                toast.setView(custom_view);
                                toast.show();


                                //Toast.makeText(VacHome.this, "No Centers Available!", Toast.LENGTH_LONG).show();
                                finish();
                            }

                            else {

                                Toast toast = new Toast(getApplicationContext());
                                toast.setDuration(Toast.LENGTH_LONG);

                                //inflate view
                                View custom_view = getLayoutInflater().inflate(R.layout.toast_icon_text, null);
                                ((TextView) custom_view.findViewById(R.id.message)).setText("All Open Centers");
                                ((ImageView) custom_view.findViewById(R.id.icon)).setImageResource(R.drawable.ic_done);
                                ((CardView) custom_view.findViewById(R.id.parent_view)).setCardBackgroundColor(getResources().getColor(R.color.neon_green));

                                toast.setView(custom_view);
                                toast.show();


                                for(int i=0; i< centers_jsonArray.length(); i++) {
                                    //vacmodel centerModel = new vacmodel();
                                    JSONObject centerObj = centers_jsonArray.getJSONObject(i);

                                    String centerName = centerObj.getString("name");
                                    String centerAddress = centerObj.getString("address");
                                    String centerFromTime = centerObj.getString("from");
                                    String centerToTime = centerObj.getString("to");
                                    String feeType = centerObj.getString("fee_type");


                                    JSONObject sessionObj = centerObj.getJSONArray("sessions").getJSONObject(0);
                                    int dose1 = sessionObj.getInt("available_capacity_dose1");
                                    int dose2 = sessionObj.getInt("available_capacity_dose2");
                                    int ageLimit = sessionObj.getInt("min_age_limit");
                                    String vaccineName = sessionObj.getString("vaccine");
                                    //int availableCapacity = sessionObj.getInt("available_capacity");

                                    vacmodel center = new vacmodel(
                                            centerName,
                                            centerAddress,
                                            centerFromTime,
                                            centerToTime,
                                            feeType,
                                            ageLimit,
                                            vaccineName,
                                            dose1,
                                            dose2
                                    );
                                    //Toast.makeText(VacHome.this, centerName, Toast.LENGTH_SHORT).show();
                                    //arrayList.add(center);
                                    arrayList.add(center);
                                }

                                vacadapter = new vac_adapter(arrayList);
                                vacreview.setAdapter(vacadapter);



                            }


//                            //JSONObject data_india = all_state_jsonArray.getJSONObject(0);
//                            //JSONObject test_data_india = testData_jsonArray.getJSONObject(testData_jsonArray.length()-1);
//
//                            //JSONObject delta_data_india = delta_jsonArray.getJSONObject(delta_jsonArray.length()-1);
//
//
//                            //Fetching data for India and storing it in String
//                            str_confirmed = data_india.getString("confirmed");   //Confirmed cases in India
//
//
//                            str_active = data_india.getString("active");    //Active cases in India
//
//                            str_recovered = data_india.getString("recovered");  //Total recovered cased in India
//
//
//                            str_death = data_india.getString("deaths");     //Total deaths in India
//
//
//                            str_last_update_time = data_india.getString("lastupdatedtime"); //Last update date and time
//
//                            str_tests = test_data_india.getString("totalrtpcrsamplescollectedicmrapplication"); //Total samples tested in India
//
//
//
//                            // --------------------------------------------------
//                            str_confirmed_new = delta_data_india.getString("dailyconfirmed");   //New Confirmed cases from last update time
//                            str_recovered_new = delta_data_india.getString("dailyrecovered"); //New recovered cases from last update time
//                            str_death_new = delta_data_india.getString("dailydeceased");    //New death cases from last update time
//                            str_tests_new = test_data_india.getString("dailyrtpcrsamplescollectedicmrapplication");   //New samples tested today

//                            Handler delayToshowProgess = new Handler();
//
//                            delayToshowProgess.postDelayed(new Runnable() {
//                                @Override
//                                public void run() {
//
//
//                                    DismissDialog();
//
//                                }
//                            }, 1000);





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

    public void clickvacclose(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        finish();
    }
}