package com.st.joke;

import java.util.List;

import model.Joke;
import util.MemoryCommunicator;
import util.ParseObjectJokeUtil;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

@SuppressLint({ "ResourceAsColor", "ResourceAsColor" })
public class JokeActivity extends SherlockActivity {
	
	private TextView text;
	private TextView likesView;
	private TextView dislikesView;
	private ImageView like;
    private ImageView dislike;
	private TextView author;

	
	private TextView about;
	private Button add_comment;
	private EditText comment;
	private Joke joke;
	private LinearLayout tempLayout;
	public static final String TAG = "JokeActivity";
	 
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case android.R.id.home:
	        // app icon in action bar clicked; go home
	        finish();
	        break;
	    }
	    return true;
	}
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.jokesactivity_layout);
		initViews();
		getSupportActionBar().setDisplayShowTitleEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
	    getSupportActionBar().setTitle(R.string.joke);
		Intent intent = getIntent();
		joke = (Joke)intent.getSerializableExtra("joke");
	    final ParseObject jokeObject = ParseObjectJokeUtil.getParseObjectFromJoke(joke);
	    text.setText(jokeObject.getString("text"));
	    author.setText(jokeObject.getString("Author"));
	    likesView.setText(String.valueOf(jokeObject.getInt("Likes")));
	    dislikesView.setText(String.valueOf(jokeObject.getInt("Dislikes")));
	    
	    final LinearLayout commentsLayout = (LinearLayout)findViewById(R.id.commentsLayout);
	    tempLayout = commentsLayout;
	    ParseQuery query = new ParseQuery("Comment");
		Log.d(TAG, "joke_id:  " + joke.getId());
	    query.whereEqualTo("joke_id", joke.getId());
	    query.findInBackground(new FindCallback() {
	        @SuppressLint({ "ResourceAsColor", "ResourceAsColor", "ResourceAsColor" })
			public void done(List<ParseObject> scoreList, ParseException e) {
	            if (e == null)  {
	            	for(ParseObject p : scoreList) {
	            		Log.d(TAG, "scoreList item: " + p.getString(("text")));
	            		TextView v = new TextView(JokeActivity.this);
	            		v.setText(p.getString("author") + ": " + p.getString(("text")));
	            		v.setTextColor(R.color.black);
	            		commentsLayout.addView(v);
	            	}
	            } else {
	                Log.d("score", "Error: " + e.getMessage());
	            }
	        }
	    });
	    
	    
	    like.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        
	        			
			        	new Thread(new Runnable() {
			        		public void run() {
			        			
			        			jokeObject.increment("Likes");
			        			jokeObject.saveEventually();
			        		}
			        	}).start();
			        	
			        	
			        	int i = Integer.valueOf((String) ((TextView)((View)v.getParent()).findViewById(R.id.likes)).getText())  + 1;
			        	((TextView)((View)v.getParent()).findViewById(R.id.likes)).setText(String.valueOf(i));
	        }
	     
        });
        
       dislike.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	new Thread(new Runnable() {
	        		public void run() {
	        			
	        			jokeObject.increment("Dislikes", 1);
	        			jokeObject.saveEventually();
	        		}
	        	}).start();
	        	
	        	int i = Integer.valueOf((String) ((TextView)((View)v.getParent()).findViewById(R.id.dislikes)).getText()) + 1;
	        	((TextView)((View)v.getParent()).findViewById(R.id.dislikes)).setText(String.valueOf(i));
	        		
	        	
	        }
        });
	    
       
       ParseQuery query1 = new ParseQuery("Author");
      // Log.d("JokeActivity", "username: " + author.getText());
       query1.whereEqualTo("name", author.getText());
	   query1.findInBackground(new FindCallback() {
	        public void done(List<ParseObject> scoreList, ParseException e) {
	            if (e == null)  {
	            	Log.d("JokeActivity", "scorelit.length: " + scoreList.size());
	            	if (!scoreList.isEmpty()) {
		            	
		            	about.setText(scoreList.get(0).getString("about"));
	            	}
	            } else {
	                Log.d("score", "Error: " + e.getMessage());
	            }
	        }
	    });
	}
	
	private void initViews() {
	     text = (TextView)findViewById(R.id.txtTitle);
         likesView = (TextView)findViewById(R.id.likes);
         dislikesView = (TextView)findViewById(R.id.dislikes);
         like = (ImageView)findViewById(R.id.like);
         dislike = (ImageView)findViewById(R.id.dislike);
         author = (TextView)findViewById(R.id.author);
         about = (TextView)findViewById(R.id.about);
         add_comment = (Button)findViewById(R.id.add_comment);
         comment = (EditText)findViewById(R.id.comment);
         
         add_comment.setOnClickListener(new OnClickListener() {
			
			@SuppressLint({ "ResourceAsColor", "ResourceAsColor", "ResourceAsColor" })
			@Override
			public void onClick(View v) {
				final String commentText = comment.getText().toString();
				if (!commentText.equals("")) {
					
					final String author =  MemoryCommunicator.get("name");
					if (!author.equals("")) {
						new Thread(new Runnable() {
			        		public void run() {
			        			Log.d(TAG, "add_comment");
			        			ParseObject obj = new ParseObject("Comment");
								obj.put("text", commentText);
								obj.put("author", author);
								obj.put("joke_id", joke.getId());
								obj.saveInBackground();
			        	
						
								ParseQuery query1 = new ParseQuery("Joke");
							      // Log.d("JokeActivity", "username: " + author.getText());
							    query1.whereEqualTo("objectId", joke.getId());
								query1.findInBackground(new FindCallback() {
								        public void done(List<ParseObject> scoreList, ParseException e) {
								            if (e == null)  {
								            			if (!scoreList.isEmpty()) {
									            			ParseObject jokeObject = scoreList.get(0);
										        			jokeObject.increment("comments_count", 1);
										        			jokeObject.saveEventually();
								            			}
								            } else {
								                Log.d("score", "Error: " + e.getMessage());
								            }
								        }
								    });

			        		}
			        		
			        		
			        	}).start();
			        	
						
						comment.setText("");
						TextView v1 = new TextView(JokeActivity.this);
	            		v1.setText(author + ": " + commentText);
	            		v1.setTextColor(R.color.black);
	            		tempLayout.addView(v1);
            		
					} else {
						AlertDialog.Builder builder = new AlertDialog.Builder(JokeActivity.this);

						
						
						builder.setMessage(R.string.dialog_no_profile_name)
						       .setTitle(R.string.dialog_no_profile_name_title);
						builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
					           public void onClick(DialogInterface dialog, int id) {
					               dialog.dismiss();
					           }
					       });
						
						AlertDialog dialog = builder.create();
						dialog.show();
					}
					
				}
				
			}
		});
	}

	
	
}
