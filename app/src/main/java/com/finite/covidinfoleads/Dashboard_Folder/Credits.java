package com.finite.covidinfoleads.Dashboard_Folder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Toast;

import com.finite.covidinfoleads.R;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }


    private void goToUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void clickRishabh(View view) {

        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Contact the App's Developer on any of these Platforms", Toast.LENGTH_LONG).show();
        goToUrl("https://www.linktr.ee/Fin1te");

    }
}