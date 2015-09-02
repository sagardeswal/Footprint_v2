package com.sacri.footprint_v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity {

    private EditText etFullname;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etREPassword;
    private EditText etEmail;
    private EditText etMobile;
    private EditText etNationality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Get reference to EditText fields
        etFullname = (EditText) findViewById(R.id.fullnameEditText);
        etUsername = (EditText) findViewById(R.id.usernameEditText);
        etPassword = (EditText) findViewById(R.id.passwordEditText);
        etREPassword = (EditText) findViewById(R.id.reenterpasswordEditText);
        etEmail = (EditText) findViewById(R.id.emailEditText);
        etMobile = (EditText) findViewById(R.id.mobileEditText);
        etNationality = (EditText) findViewById(R.id.nationalityEditText);

    }



    public void signUpButtonClicked(View view){
        Log.i("com.sacri.footprint_v2","Sign Up Button clicked");

        if(etPassword.getText().toString().equals(etREPassword.getText().toString())){
            Log.i("com.sacri.footprint_v2","Calling storeUserData()");
            storeUserData();
        }
        else{
            Toast.makeText(SignUpActivity.this, "Passwords don't match", Toast.LENGTH_SHORT).show();
        }
    }

    private void storeUserData() {
        Log.i("com.sacri.footprint_v2","entering storeUserData()");
        ParseUser user = new ParseUser();
        user.setUsername(etUsername.getText().toString());
        user.setPassword(etPassword.getText().toString());
        user.setEmail(etEmail.getText().toString());
        user.put("fullname", etFullname.getText().toString());
        user.put("mobile", etMobile.getText().toString());
        user.put("nationality", etNationality.getText().toString());
        Log.i("com.sacri.footprint_v2", "userdata: " + user.toString());
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                Log.i("com.sacri.footprint_v2","entering done()");
                if (e == null) {
                    // Hooray! Let them use the app now.
                    Toast.makeText(SignUpActivity.this, "Successfully Signed up, please log in.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(i);
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    Toast.makeText(SignUpActivity.this,"Sign up Error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        });
    }
}
