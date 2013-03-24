package util;

import com.st.joke.JokeActivity;
import com.st.joke.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {
	public static void makeSimpleDialog(Context context, int title, int text) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		builder.setMessage(text).setTitle(title);
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	               dialog.dismiss();
	           }
	       });
		
		AlertDialog dialog = builder.create();
		dialog.show();
	}
}
