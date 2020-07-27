package com.cyberminer.control;

//import com.cyberminer.kwic.Kwic;

/**
 * Test.java
 * 
 * @author James
 * Date: July 27th, 2020
 */
public class Test {

	public static void main(String[] args) {
		//System.out.println("Main method in Test.java.");
		
		/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*                           Adding a new url.                          */
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        //boolean success = new Kwic().addUrl("url_address", "url_description", (boolean)isPaid);
        
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*                       Editing an existing url.                       */
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // 1. Create a new url with updated info (use the existing urlId and hits).
        //Url updated = new Url(int id, String url, String desc, int hits, boolean paid);
        
        // 2. Call the static edit function in Kwic.
        //boolean success = Kwic.editUrl(Url updated);
        
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*                       Deleting an existing url.                      */
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        //boolean success = Kwic.deleteUrl(int id);
        
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*                  Performing a standard search (OR).                  */
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // The search terms should be a single string in comma-separated format.
        // E.g. "social,media,service"
        // All non-alpha characters, non-numeric characters, and non-commas are
        // removed by the stored procedure in the DB.
        //ArrayList<Url> results = Kwic.doSearch(String searchTerms);
        
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*                       Performing an AND search.                      */
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // Each search term should be a single word.
        // E.g. term1 = "social" and term2 = "service"
        // All non-alpha characters and non-numeric characters are removed by
        // the stored procedure in the DB.
        //ArrayList<Url> results = Kwic.doAndSearch(String term1, String term2);
        
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        /*                       Performing a NOT search.                       */
        /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // Each search term should be a single word.
        // E.g. term1 = "social" and term2 = "service"
        // All non-alpha characters and non-numeric characters are removed by
        // the stored procedure in the DB.
        //ArrayList<Url> results = Kwic.doNotSearch(String term, String notTerm);
	}
}
