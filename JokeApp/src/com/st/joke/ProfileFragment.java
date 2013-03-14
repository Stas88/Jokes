package com.st.joke;

import java.util.List;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import util.MemoryCommunicator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
	
	private View view;
	private boolean isAuthorExists = false;

	  public static final String TAG = "ProfileFragment";
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG,"onCreateView");
		view = (ViewGroup) inflater.inflate(R.layout.profilefragment_layout, container, false);
        return view; 
    }
	
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button ok = (Button)view.findViewById(R.id.ok);
        Button cancel = (Button)view.findViewById(R.id.cancel);
        
        final EditText name = (EditText)view.findViewById(R.id.name);
        final EditText password = (EditText)view.findViewById(R.id.password);
        final EditText about = (EditText)view.findViewById(R.id.about);
        
        name.setText(MemoryCommunicator.get("name"));
        password.setText(MemoryCommunicator.get("who"));
		about.setText(MemoryCommunicator.get("about"));
       
        cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				name.setText(MemoryCommunicator.get("name"));
				password.setText(MemoryCommunicator.get("who"));
				about.setText(MemoryCommunicator.get("about"));
			}
		});
        
        ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					String nameText = name.getText().toString();
					String passwordText  = password.getText().toString();
				    if(name.equals("") || password.equals("")) {
				    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

						
						builder.setMessage(R.string.dialog_no_profile_name)
						       .setTitle(R.string.dialog_no_profile_name_title);
						builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               dialog.dismiss();
					           }
					       });
						
						AlertDialog dialog = builder.create();
						dialog.show();
				    } else {
						ParseQuery query = new ParseQuery("Author");
					    query.whereEqualTo("name", name.getText().toString());
					    query.findInBackground(findAuthorCallback);
						MemoryCommunicator.set("name", name.getText().toString());
						MemoryCommunicator.set("password", password.getText().toString());
						MemoryCommunicator.set("about", about.getText().toString());
				    }
			}
		});

    }
	
	FindCallback findAuthorCallback  = new FindCallback() {
		
        public void done(List<ParseObject> scoreList, ParseException e) {
        	
            if (e == null)  {
            	EditText password = (EditText)view.findViewById(R.id.password);
        	    EditText about = (EditText)view.findViewById(R.id.about);
        	    EditText name = (EditText)view.findViewById(R.id.name);
        	    Log.d(TAG, "scoreList.size() =" + scoreList.size());
            	if (!scoreList.isEmpty()) {
            		ParseObject obj = scoreList.get(0);
					//obj.put("password", password.getText().toString());
					if(!obj.getString("password").equals(password.getText().toString())) {
						AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
						builder.setMessage(R.string.dialog_no_profile_name)
						       .setTitle(R.string.wrong_password);
						builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               dialog.dismiss();
					           }
					       });
						AlertDialog dialog = builder.create();
						dialog.show();
					} else {
						obj.put("about", about.getText().toString());
						obj.saveInBackground();
					}
            	} else {
            		ParseObject obj = new ParseObject("Author");
					obj.put("name", name.getText().toString());
					obj.put("password", password.getText().toString());
					obj.put("about", about.getText().toString());
					obj.saveInBackground();
            	}
            } else {
                Log.d("score", "FindAuthorCallback Error: " + e.getMessage());
            }
        }
        
    };
}
