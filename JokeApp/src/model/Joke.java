package model;

import java.io.Serializable;

public class Joke implements Serializable {
	
	private String id;
	private String text;
	private String author;
	private int likes;
	private int dislikes;
	private int comments_count;
	
	public Joke() {
	
		// TODO Auto-generated constructor stub
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}
	
	public int getDislikes() {
		return dislikes;
	}

	public void setDislikes(int dislikes) {
		this.dislikes = dislikes;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setComments_count(int comments_count) {
		this.comments_count = comments_count;
	}
	
	public int getComments_count() {
		return comments_count;
	}
	

}
