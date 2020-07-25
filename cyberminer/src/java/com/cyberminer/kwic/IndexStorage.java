package com.cyberminer.kwic;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * IndexStorage.java
 * 
 * @author James
 * Date: July 24th, 2020
 */
public class IndexStorage extends Observable {
	List<Index> indexes = new ArrayList<Index>();
	
	/**
	 * @param urlId		This is an int representing the database index of the Url.
	 * @param wordPos	This is an int representing the position of the word in the url description.
	 * @param word		This is a String that contains the keyword to be stored in the Index.
	 */protected void add(int urlId, int pos, String word) {
		indexes.add(new Index(urlId, pos, word));
	}
	
	/**
	 * @return			The int quantity of stored Indexes in the IndexStorage.
	 */
	public int getSize() {
		return indexes.size();
	}
	
	/**
	 * @param pos		This is an int representing the position of the Index object being retrieved. 
	 * @return			The Index object at the given index in IndexStorage.
	 */
	public Index get(int pos) {
		return indexes.get(pos);
	}
	
	/**
	 * @param pos		This is an int representing the position of the Index object being retrieved. 
	 * @return			A String representing the keyword for this Index.
	 */
	public String getKeyword(int pos) {
		return indexes.get(pos).getKeyword();
	}
	
	/**
	 * @param pos		This is an int representing the position in IndexStorage to insert the given Index.
	 * @param index		This is the Index object being inserted into IndexStorage at the given position.
	 */
	protected void insert(int pos, Index index) {
		indexes.add(pos, index);
	}
	
	/**
	 * @param pos		This is an int representing the position in IndexStorage to delete an Index.
	 */
	protected void delete(int pos) {
		indexes.remove(pos);
	}
	
	protected void reset() {
		indexes.clear();
	}
}
