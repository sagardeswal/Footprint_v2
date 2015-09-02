package com.sacri.footprint_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        etUsername = (EditText) findViewById(R.id.usernameEditText);
        etPassword = (EditText) findViewById(R.id.passwordEditText);
    }

    public void signUpButtonClicked(View view){
        Intent i = new Intent(this, SignUpActivity.class);
        startActivity(i);


    }

    public void loginButtonClicked(View view){
        logInUser();
    }

    private void logInUser(){
        ParseUser.logInInBackground(etUsername.getText().toString(), etPassword.getText().toString(), new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),
                            "Successfully Logged in",
                            Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    Toast.makeText(
                            getApplicationContext(),
                            "No such user exist, please signup",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
