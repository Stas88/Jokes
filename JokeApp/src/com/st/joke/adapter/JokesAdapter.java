package com.st.joke.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.st.joke.R;


public class JokesAdapter extends ArrayAdapter<ParseObject>  {

	
	    Context context; 
	    int layoutResourceId;    
	    List<ParseObject> data = null;
	    public static final String TAG = "JokesAdapter";
	   
	    
	    public JokesAdapter(Context context, int layoutResourceId, List<ParseObject> data) {
	        super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        this.data = data;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        ParceHolder holder = null;
	        
	        if(row == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	            holder = new ParceHolder();
	           
	            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
	            holder.likesView = (TextView)row.findViewById(R.id.likes);
	            holder.dislikesView = (TextView)row.findViewById(R.id.dislikes);
	            holder.like = (ImageView)row.findViewById(R.id.like);
	            holder.dislike = (ImageView)row.findViewById(R.id.dislike);
	            holder.author = (TextView)row.findViewById(R.id.author);
	            holder.comments_count = (TextView)row.findViewById(R.id.comments_count);
	         
	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (ParceHolder)row.getTag();
	        }
	        
	        final ParseObject joke = data.get(position);
	        String text = joke.getString("text");
	        int comments_count = joke.getInt("comments_count");
	        Log.d(TAG, "comments_count = " +comments_count );
	        int likes = joke.getInt("Likes");
	        int dislikes = joke.getInt("Dislikes");
	        holder.author.setText(joke.getString("joke_author"));
	        holder.txtTitle.setText(text);
	       
	        if (likes == 0) {
	        	  holder.likesView.setText("0");
	        } else {
	        	  holder.likesView.setText(String.valueOf(likes));
	        }
	        if (dislikes == 0) {
	        	  holder.dislikesView.setText("0");
	        } else {
	        	  holder.dislikesView.setText(String.valueOf(dislikes));
	        }
	        if (comments_count == 0) {
	        	  holder.comments_count.setText("0");
	        } else {
	        	  holder.comments_count.setText(String.valueOf(comments_count));
	        }
	      
	        
	        
	        
	        
	        holder.like.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v) {
		        
		        			
				        	new Thread(new Runnable() {
				        		public void run() {
				        			
				        			joke.increment("Likes");
						        	joke.saveEventually();
				        		}
				        	}).start();
				        	
				        	
				        	int i = Integer.valueOf((String) ((TextView)((View)v.getParent()).findViewById(R.id.likes)).getText())  + 1;
				        	((TextView)((View)v.getParent()).findViewById(R.id.likes)).setText(String.valueOf(i));
		        }
		     
	        });
	        
	        holder.dislike.setOnClickListener(new View.OnClickListener() {
		        public void onClick(View v) {
		        	new Thread(new Runnable() {
		        		public void run() {
		        			
		        			joke.increment("Dislikes", 1);
				        	joke.saveEventually();
		        		}
		        	}).start();
		        	
		        	int i = Integer.valueOf((String) ((TextView)((View)v.getParent()).findViewById(R.id.dislikes)).getText()) + 1;
		        	((TextView)((View)v.getParent()).findViewById(R.id.dislikes)).setText(String.valueOf(i));
		        		
		        	
		        }
	        });
	        return row;
	    }

	    
	    static class ParceHolder
	    {
	    	
	    	TextView dislikesView;
	    	TextView likesView;
	        TextView txtTitle;
	        TextView author;
	        TextView comments_count;
	        ImageView like;
	        ImageView dislike;
	    }
	    
	   
		
}
