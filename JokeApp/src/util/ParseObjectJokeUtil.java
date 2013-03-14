package util;

import model.Joke;
import android.util.Log;

import com.parse.ParseObject;

public class ParseObjectJokeUtil {
	
	public static Joke getJokeFromParseObject(ParseObject praseObject) {
		Joke joke = new Joke();
		Log.d("ParseObjectJokeUtil", "praseObject.objectId "  + praseObject.getObjectId());
		joke.setId(praseObject.getObjectId());
		Log.d("ParseObjectJokeUtil", "praseObject.text "  + praseObject.getString("text"));
		joke.setText(praseObject.getString("text"));
		Log.d("ParseObjectJokeUtil", "praseObject.Likes "  + praseObject.getInt("Likes"));
		joke.setLikes(praseObject.getInt("Likes"));
		Log.d("ParseObjectJokeUtil", "praseObject.Comment_count "  + praseObject.getInt("comments_count"));
		joke.setComments_count(praseObject.getInt("comments_count"));
		Log.d("ParseObjectJokeUtil", "praseObject.DisLikes "  + praseObject.getInt("Dislikes"));
		joke.setDislikes(praseObject.getInt("Dislikes"));
		Log.d("ParseObjectJokeUtil", "praseObject.author = "  + praseObject.getString("joke_author"));
		joke.setAuthor(praseObject.getString("joke_author"));
		return joke;
	}

	public static ParseObject getParseObjectFromJoke(Joke joke) {
		ParseObject obj = new ParseObject("Joke");
		Log.d("ParseObjectJokeUtil", "joke.getId() = "  + joke.getId());
		obj.put("objectId", joke.getId());
		Log.d("ParseObjectJokeUtil", "joke.getText() = "  + joke.getText());
		obj.put("text", joke.getText());
		Log.d("ParseObjectJokeUtil", "joke.getLikes() = "  + joke.getLikes());
		obj.put("Likes", joke.getLikes());
		Log.d("ParseObjectJokeUtil", "joke.getDisLikes() = "  + joke.getDislikes());
		obj.put("Dislikes", joke.getDislikes());
		Log.d("ParseObjectJokeUtil", "joke.getComments_count() = "  + joke.getComments_count());
		obj.put("comments_count", joke.getComments_count());
		Log.d("ParseObjectJokeUtil", "joke.getAuthor() = "  + joke.getAuthor());
		obj.put("Author", joke.getAuthor());
		return obj;
	}
}
