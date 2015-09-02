package com.sacri.footprint_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

public class ProfileActivity extends AppCompatActivity {

    private TextView tvFullname;
    private TextView tvUsername;
    private TextView tvPassword;
    private TextView tvEmail;
    private TextView tvMobile;
    private TextView tvNationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvFullname = (TextView) findViewById(R.id.displayNameTextView);
        tvUsername = (TextView) findViewById(R.id.displayUsernameTextView);
        tvPassword = (TextView) findViewById(R.id.displayPasswordTextView);
        tvEmail = (TextView) findViewById(R.id.displayEmailTextView);
        tvMobile = (TextView) findViewById(R.id.displayMobileTextView);
        tvNationality = (TextView) findViewById(R.id.displayNationalityTextView);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            tvFullname.setText(currentUser.get("fullname").toString());
            tvUsername.setText(currentUser.getUsername());
            tvPassword.setText("**********");
            tvEmail.setText(currentUser.getEmail());
            tvMobile.setText(currentUser.get("mobile").toString());
            tvNationality.setText(currentUser.get("nationality").toString());
        } else {
            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }

    public void signOutButtonClicked(View view){
        Log.i("com.sacri.footprint_v2","signOutButtonClicked()");
        ParseUser.logOut();
        Log.i("com.sacri.footprint_v2", "logOut()");
        ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
        if(currentUser.getUsername()==null){
            Toast.makeText(ProfileActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(i);
        }
        else{
            Log.i("com.sacri.footprint_v2", "currentUser: " + currentUser.getUsername());
        }
    }

}
