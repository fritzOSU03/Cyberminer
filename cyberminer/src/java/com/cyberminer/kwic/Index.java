package com.cyberminer.kwic;

import com.cyberminer.dao.UrlConnectionService;

/**
 * Index.java
 * 
 * @author James
 * Date: July 24th, 2020
 */
public class Index {
	
	private int urlId;
	private int wordPos;
	private String word;
	
	/**
	 * This is the constructor for the Index object.
	 * 
	 * @param word		This is a String that contains the keyword to be stored in the Index.
	 * @param urlId		This is an int representing the database index of the Url.
	 * @param wordPos	This is an int representing the position of the word in the url description.
	 */
	public Index(int urlId, int wordPos, String word) {
		super();
		setUrlId(urlId);
		setWordPosition(wordPos);
		setKeyword(word);
	}
	
	protected int addIndex() {
		try {return new UrlConnectionService().addIndex(getUrlId(), getWordPosition(), getKeyword());}
		catch(Exception e) {e.printStackTrace();}
        return 0;
	}
	
	/**
	 * @return			A String representing the keyword for this Index.
	 */
	public String getKeyword() {
		return word;
	}
	
	/**
	 * @return			An int representing the position of the line in LineStorage.
	 */
	private int getUrlId() {
		return urlId;
	}
	
	/**
	 * @return			An int representing the position of the word in the line in LineStorage.
	 */
	private int getWordPosition() {
		return wordPos;
	}
	
	/**
	 * @param word		This is a String that contains the keyword to be stored in the Index.
	 */
	private void setKeyword(String word) {
		this.word = word;
	}
	
	/**
	 * @param urlId		This is an int representing the index of the url in the database.
	 */
	private void setUrlId(int urlId) {
		this.urlId = urlId;
	}
	
	/**
	 * @param wordPos	This is an int representing the position of the word in the line in LineStorage.
	 */
	private void setWordPosition(int wordPos) {
		this.wordPos = wordPos;
	}
}
