package com.cyberminer.kwic;

import java.util.ArrayList;

import com.cyberminer.dao.UrlConnectionService;
import com.cyberminer.url.Url;

/**
 * Kwic.java
 * 
 * @author James
 * Date: July 27th, 2020
 */
public class Kwic {
	
	protected static IndexStorage indexStorage;
	protected static CircularShift cirShift;
	protected static AlphaShift alphaShift;
	
	
	//KWIC default constructor.
	public Kwic() {
		//Create the IndexStorage object.
		indexStorage	= new IndexStorage();
		
		//Create the CircularShift object.
		cirShift		= new CircularShift();
		
		//Create the AlphaShift object.
		alphaShift		= new AlphaShift();
	}
	
	
	/**
	 * @param url			This is a String object representing the url hyperlink address.
	 * @param description	This is a String object representing the url description.
	 * @param isPaid		This is a Boolean object used to identify the url as paid.
     * @return              Returns a boolean that is true on success, else false.
	 */
	public boolean addUrl(String url, String description, boolean isPaid) {
		try {
			if(!url.equals("") && !description.equals("")) {
				//Create the url object.
				Url u = new Url(url, description.trim(), isPaid);
				
				//Call circular shift on the url object.
				return cirShift.shift(u, indexStorage);
			}
		}
		catch(Exception e) {e.printStackTrace();}
        return false;
	}
	
	
	/**
	 * @param updated	This is a Url object containing the original urlId and hitRate
	 * 					but it has an updated url, description, paid status, or any
	 * 					combination of these values. 
	 * @return			Returns a boolean that is true on success, else false.
	 */
	public static boolean editUrl(Url updated) {
		//Remove the existing Url object from the database.
		new UrlConnectionService().deleteUrl(updated.getId());
		
		////Call circular shift on the updated url object.
		return cirShift.shift(updated, indexStorage);
	}
	
	
	/**
	 * @param urlId	This is an int representing the urlId of the Url to be deleted.
	 * @return		Returns a boolean that is true on success, else false.
	 */
	public static boolean deleteUrl(int urlId) {
		return new UrlConnectionService().deleteUrl(urlId);
	}
	
	
	/**
	 * @param searchTerms	This is a String containing a comma-separated list of
	 * 						terms to be included in the search.
	 * @return				Returns an ArrayList<Url> containing all of the Url objects
	 * 						matching any of the provided search criteria.
	 */
	public static ArrayList<Url> doSearch(String searchTerms) {
            return new UrlConnectionService().getResults(searchTerms);
	}
	
	
	/**
	 * @param term1	This is a String containing the first word that must be found
	 * 				for a Url to be a successful match.
	 * @param term2 This is a String containing the second word that must be found
	 * 				for a Url to be a successful match.
	 * @return		Returns an ArrayList<Url> containing all of the Url objects matching
	 * 				both of the provided search terms.
	 */
	public static ArrayList<Url> doAndSearch(String term1, String term2) {
		return new UrlConnectionService().getAndResults(term1, term2);
	}
	
	
	/**
	 * @param term		This is a String containing a word that must be found for a
	 * 					Url to be a successful match.
	 * @param notTerm	This is a String containing a word that must not be found
	 * 					for a Url to be a successful match.
	 * @return			Returns an ArrayList<Url> containing all of the Url objects containing
	 * 					the first term but not the second term.
	 */
	public static ArrayList<Url> doNotSearch(String term, String notTerm) {
		return new UrlConnectionService().getNotResults(term, notTerm);
	}
}
