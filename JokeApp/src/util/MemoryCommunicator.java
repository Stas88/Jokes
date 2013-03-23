package util;

import android.content.SharedPreferences;

import com.st.joke.ParseApplication;

public class MemoryCommunicator {

	

	    public static final String PREFS_NAME = "JokesPrefs";
	    private static ParseApplication instance;
	    
	   
	
	    static SharedPreferences settings = ParseApplication.instance.getSharedPreferences(PREFS_NAME, 0);
	
	    public static String get(String key) {
		     
		     return settings.getString(key, "");
	  
	    }

	    
	

		
	    public static  void set(String key, String value ){
	      SharedPreferences.Editor editor = settings.edit();
	      editor.putString(key, value);
	      // Commit the edits!
	      editor.commit();
	    }





		private static Object getApplicationContext() {
			// TODO Auto-generated method stub
			return null;
		}
	
}
