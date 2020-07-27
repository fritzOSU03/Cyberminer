package com.cyberminer.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.cyberminer.url.Url;

/**
 * UrlConnectionService.java
 * 
 * @author James
 * Date: July 27th, 2020
 */
public class UrlConnectionService implements UrlConnectionDao {
	
	private static final String DB_NAME = "cmrepo";
	
	
	/**
	 * @param urlId		This is an int representing the urlId of the Url to be deleted.
	 * @param wordPos	This is an int representing the starting index within the String
	 * 					description for the word being added.
	 * @param word		This is a String representing the word value being added.
	 * @return			Returns an int containing 1 on successful addition.
	 */
	@Override
	public int addIndex(int urlId, int wordPos, String word) {
		ArrayList<Object> results = null;
		int indexId = 0;
		
		try(	Connection con = UrlConnectionManager.getConnection();
				PreparedStatement pStmt = con.prepareStatement("call " + DB_NAME + ".AddIndex(?,?,?)")) {
			pStmt.setInt(1, urlId);
			pStmt.setInt(2, wordPos);
			pStmt.setString(3, word);
			results = UrlConnectionManager.executeStoredProcedure(con, pStmt);
			try {indexId = ((Long) results.get(0)).intValue();}
			catch(Exception e) {System.out.println(results.get(1) + ": " + results.get(0));}
		}
		catch(NullPointerException e)	{e.printStackTrace();}
		catch(Exception e)				{e.printStackTrace();}
		return indexId;
	}
	
	
	/**
	 * @param url	This is the Url object being added to the database.
	 * @return		Returns an int containing the urlId of the newly added Url.
	 */
	@Override
	public int addUrl(Url url) {
		ArrayList<Object> results = null;
		int urlId = 0;
		
		try(	Connection con = UrlConnectionManager.getConnection();
				PreparedStatement pStmt = con.prepareStatement("call " + DB_NAME + ".AddUrl(?, ?, ?, ?)")) {
			pStmt.setString(1, url.getUrl());
			pStmt.setString(2, url.getDesc());
			pStmt.setInt(	3, url.getHits());
			pStmt.setInt(	4, url.getPaid() ? 1 : 0);
			results = UrlConnectionManager.executeStoredProcedure(con, pStmt);
			try {urlId = (int) ((BigInteger) results.get(0)).intValue();}  
			catch(Exception e) {System.out.println(results.get(1) + ": " + results.get(0));}
		}
		catch(NullPointerException e)	{e.printStackTrace();}
		catch(Exception e)				{e.printStackTrace();}
		return urlId;
	}
	
	
	/**
	 * @param urlId	This is an int containing the urlId of the Url to be deleted.
	 * @return		Returns a boolean that is true on success, else false.
	 */
	@Override
	public boolean deleteUrl(int urlId) {
		try(	Connection con = UrlConnectionManager.getConnection();
				PreparedStatement pStmt = con.prepareStatement("call " + DB_NAME + ".DeleteUrl(?)")) {
			pStmt.setInt(1, urlId);
			UrlConnectionManager.executeStoredProcedure(con, pStmt);
		}
		catch(NullPointerException e)	{e.printStackTrace(); return false;}
		catch(Exception e)				{e.printStackTrace(); return false;}
		return true;
	}
	
	
	/**
	 * @param search	This is a String containing a comma-separated list of
	 * 					terms to be included in the search.
	 * @return			Returns an ArrayList<Url> containing all of the Url objects
	 * 					matching any of the provided search criteria.
	 */
	@Override
	public ArrayList<Url> getResults(String search) {
		ArrayList<Url> results = null;
		
		try(	Connection con = UrlConnectionManager.getConnection();
				PreparedStatement pStmt = con.prepareStatement("call " + DB_NAME + ".GetResults(?)")) {
			pStmt.setString(1, search);
			results = UrlConnectionManager.executeUrlStoredProcedure(con, pStmt);
		}
		catch(NullPointerException e)	{e.printStackTrace();}
		catch(Exception e)				{e.printStackTrace();}
		return results;
	}
	
	
	/**
	 * @param term1	This is a String containing the first word that must be found
	 * 				for a Url to be a successful match.
	 * @param term2 This is a String containing the second word that must be found
	 * 				for a Url to be a successful match.
	 * @return		Returns an ArrayList<Url> containing all of the Url objects matching
	 * 				both of the provided search terms.
	 */
	@Override
	public ArrayList<Url> getAndResults(String term1, String term2) {
		ArrayList<Url> results = null;
		
		try(	Connection con = UrlConnectionManager.getConnection();
				PreparedStatement pStmt = con.prepareStatement("call " + DB_NAME + ".GetAndResults(?,?)")) {
			pStmt.setString(1, term1);
			pStmt.setString(2, term2);
			results = UrlConnectionManager.executeUrlStoredProcedure(con, pStmt);
		}
		catch(NullPointerException e)	{e.printStackTrace();}
		catch(Exception e)				{e.printStackTrace();}
		return results;
	}
	
	
	/**
	 * @param term		This is a String containing a word that must be found for a
	 * 					Url to be a successful match.
	 * @param notTerm	This is a String containing a word that must not be found
	 * 					for a Url to be a successful match.
	 * @return			Returns an ArrayList<Url> containing all of the Url objects containing
	 * 					the first term but not the second term.
	 */
	@Override
	public ArrayList<Url> getNotResults(String term, String notTerm) {
		ArrayList<Url> results = null;
		
		try(	Connection con = UrlConnectionManager.getConnection();
				PreparedStatement pStmt = con.prepareStatement("call " + DB_NAME + ".GetNotResults(?,?)")) {
			pStmt.setString(1, term);
			pStmt.setString(2, notTerm);
			results = UrlConnectionManager.executeUrlStoredProcedure(con, pStmt);
		}
		catch(NullPointerException e)	{e.printStackTrace();}
		catch(Exception e)				{e.printStackTrace();}
		return results;
	}
}
