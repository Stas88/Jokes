
package com.st.joke;

import java.util.List;

import model.Joke;
import util.Categories;
import util.ParseObjectJokeUtil;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.st.joke.adapter.JokesAdapter;

@SuppressLint({ "NewApi", "ValidFragment" }) 
public class JokesListFragment
    extends ListFragment
{
	
	ListView listView;
	ViewGroup root;
    private TextView labelText;
    public static final String TAG = "JokesListFragment";
    private Categories category;
  
    
   
    public JokesListFragment(Categories category) {
    	super();
    	this.category = category;
    	Log.d(TAG, "constructur category = " + category.name());
    }
    
    public JokesListFragment() {
    	super();
    	
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	root = (ViewGroup) inflater.inflate(R.layout.main, container, false);
    	Log.d(TAG,"onCreateView");
        return root; 

    }
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d(TAG,"onActivityCreated");
		listView = (ListView) root.findViewById(android.R.id.list);
		 populateListView();
    }
    
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
       
    }

   
    
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
    
    private void populateListView() {
    	ParseQuery query = new ParseQuery("Joke");
		
		if(category == category.BEST_JOKES) {
			query.addDescendingOrder("Likes");
		} else if (category == category.NEW_JOKES) {
			query.addDescendingOrder("createdAt");
		} else {
			query.addDescendingOrder("createdAt");
		}
		
	    query.findInBackground(new FindCallback() {
	        public void done(List<ParseObject> scoreList, ParseException e) {
	            if (e == null)  {
	            	
	            	JokesAdapter adapter = new JokesAdapter(getActivity(),
	            		       R.layout.jokeslist_item, scoreList);
	            	listView.setAdapter(adapter);
	                Log.d("score", "Retrieved " + scoreList.size() + " scores");
	                listView.setOnItemClickListener(new OnItemClickListener() {

	        			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
	        					long arg3) {
	        				ParseObject obj = (ParseObject) listView.getAdapter().getItem(position);
	        		        Joke joke = ParseObjectJokeUtil.getJokeFromParseObject(obj);
	        		        Intent intent = new Intent(getActivity(), JokeActivity.class);
	        		        intent.putExtra("joke", joke);
	        		        startActivity(intent);
	        				
	        			}
	        		}); 
	            } else {
	                Log.d("score", "Error: " + e.getMessage());
	            }
	        }
	    });
    }
}
