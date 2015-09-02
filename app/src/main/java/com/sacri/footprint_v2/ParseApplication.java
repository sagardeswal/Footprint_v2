package com.sacri.footprint_v2;

import com.parse.Parse;
import com.parse.ParseACL;

import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

    static final String APPLICATION_ID = "Ga4GuaEk3qRCc0DoaEyBhRL3l1MBSTLAFBbDA9Y7";
    static final String CLIENT_KEY = "UC7ZRUMIvdl7vv0iVjAGKjGYmKlKmuEjdacS3rrc";

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}