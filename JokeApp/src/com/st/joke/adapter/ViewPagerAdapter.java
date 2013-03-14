
package com.st.joke.adapter;

import util.Categories;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.st.joke.JokesListFragment;
import com.st.joke.ProfileFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter
{

    private String[] locations;
    private static final String TAG = "ViewPagerAdapter";

    public ViewPagerAdapter(FragmentManager fm, String[] locations) {
        super(fm);
        this.locations = locations;
        
    }

    public int getCount() {
        return locations.length;
    }

    public Fragment getItem(int position) {
    	Log.d(TAG, "position = " + position);
    	Fragment fragment = null;
    	if (position == 0) {
    	 fragment = new JokesListFragment(Categories.NEW_JOKES);
    		
    	} else if (position == 1) {
   		 fragment = new JokesListFragment(Categories.BEST_JOKES);
 		
    	} else if (position == 2) {
  		 fragment = new ProfileFragment();
     	}
       
        /*
        Bundle bundle = new Bundle();
        bundle.putString("label", locations[position]);
        fragment.setArguments(bundle);
        */
        return fragment;
    }

}
