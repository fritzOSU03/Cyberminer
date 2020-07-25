package com.cyberminer.dao;

import java.util.ArrayList;

import com.cyberminer.url.Url;

/**
 * UrlConnectionDao.java
 * 
 * @author James
 * Date: July 24th, 2020
 */
public interface UrlConnectionDao {
	
	/**
	 * @param urlId		This is an int representing the urlId of the Url to be deleted.
	 * @param wordPos	This is an int representing the starting index within the String
	 * 					description for the word being added.
	 * @param word		This is a String representing the word value being added.
	 * @return			Returns an int containing 1 on successful addition.
	 */
	public int addIndex(int urlId, int wordPos, String word);
	
	/**
	 * @param url	This is the Url object being added to the database.
	 * @return		Returns an int containing the urlId of the newly added Url.
	 */
	public int addUrl(Url url);
	
	/**
	 * @param urlId	This is an int containing the urlId of the Url to be deleted.
	 * @return		Returns a boolean that is true on success, else false.
	 */
	public boolean deleteUrl(int urlId);
	
	/**
	 * @return	Returns an ArrayList<Url> containing all of the Url objects
	 * 			contained within the database. Use of this function is for testing
	 * 			purposes only and is not recommended.
	 */
	public ArrayList<Url> getAllUrls();
	
	/**
	 * @param search	This is a String containing a comma-separated list of
	 * 					terms to be included in the search.
	 * @return			Returns an ArrayList<Url> containing all of the Url objects
	 * 					matching any of the provided search criteria.
	 */
	public ArrayList<Url> getResults(String search);
	
	/**
	 * @param term1	This is a String containing the first word that must be found
	 * 				for a Url to be a successful match.
	 * @param term2 This is a String containing the second word that must be found
	 * 				for a Url to be a successful match.
	 * @return		Returns an ArrayList<Url> containing all of the Url objects matching
	 * 				both of the provided search terms.
	 */
	public ArrayList<Url> getAndResults(String term1, String term2);
	
	/**
	 * @param term		This is a String containing a word that must be found for a
	 * 					Url to be a successful match.
	 * @param notTerm	This is a String containing a word that must not be found
	 * 					for a Url to be a successful match.
	 * @return			Returns an ArrayList<Url> containing all of the Url objects containing
	 * 					the first term but not the second term.
	 */
	public ArrayList<Url> getNotResults(String term, String notTerm);
}
