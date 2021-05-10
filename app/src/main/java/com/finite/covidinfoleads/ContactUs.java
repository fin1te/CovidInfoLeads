package com.finite.covidinfoleads;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.HapticFeedbackConstants;
import android.view.View;
import android.widget.Toast;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }

    public void clickTwitter(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "@ us and Tweet, or send us a DM.", Toast.LENGTH_LONG).show();
        goToUrl("https://www.twitter.com/covidinfoleads/");
    }
    private void goToUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    public void clickEmail(View view) {
        view.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
        Toast.makeText(this, "Write us a Mail, we'll get back to you shortly!", Toast.LENGTH_LONG).show();
        goToUrl("mailto:covidinfoleads1@gmail.com?subject=");
    }
}