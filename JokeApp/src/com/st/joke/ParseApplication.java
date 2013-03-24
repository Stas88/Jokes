package com.st.joke;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application {
	
	public static ParseApplication instance;
	
	public static ParseApplication getInstance() {
        return instance;
    }

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		// Add your initialization code here
		Parse.initialize(this, "NdkbMDpRu7UuWzkYVaFh7by6UHezbGUHEQIp8b3K" , "xel4du4CaHv7pdOwf0uLziJ7ZvIGKleig0gLxGfe");


		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
		
		
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
		
		
	}
	
	
}
