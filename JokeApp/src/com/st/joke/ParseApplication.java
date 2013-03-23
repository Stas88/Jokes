package com.st.joke;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
		
		if(!isOnline()) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			
			
			builder.setMessage(R.string.no_connection)
			       .setTitle(R.string.no_connection_title);
			builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               dialog.dismiss();
		           }
		       });
			
			AlertDialog dialog = builder.create();
			dialog.show();
		}
	}
	
	public  boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
}
