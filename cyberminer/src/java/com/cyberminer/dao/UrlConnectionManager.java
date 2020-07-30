package com.cyberminer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cyberminer.url.Url;


/**
 * UrlConnectionManager.java
 * 
 * @author James
 * Date: July 27th, 2020
 */
public class UrlConnectionManager {
	private static final String URL_CON = "jdbc:mysql://localhost?user=cmadmin&password=cmpw$1357&allowPublicKeyRetrieval=true&useSSL=false";
	private static final String CON_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static Connection con;
	
	/**
	 * @return Returns a Connection object for the defined MySQL database.
	 */
	protected static Connection getConnection() {
		try {
			Class.forName(CON_DRIVER);
			try {con = DriverManager.getConnection(URL_CON);}
			catch(SQLException e) {e.printStackTrace();}
		}
		catch(ClassNotFoundException ex) {System.out.println("Driver not found.");}
		return con;
	}
	
	
	/**
	 * @param con
	 * @param pStmt
	 * @return Returns an ArrayList<Object> containing the result set from calling
	 * the given PreparedStatement using the specified Connection.
	 */
	protected static ArrayList<Object> executeStoredProcedure(Connection con, PreparedStatement pStmt) {
		ArrayList<Object> results = new ArrayList<Object>();
		
		try(ResultSet rs = pStmt.executeQuery()) {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			
			while(rs.next()) {
				for(int i = 1; i <= rsMetaData.getColumnCount(); i++) {
					switch(rsMetaData.getColumnTypeName(i)) {
						case "INT":
							//results.add(rsMetaData.getColumnName(i) + ": " + Integer.toString(rs.getInt(i)));
							results.add(rs.getInt(i));
							break;
						case "DECIMAL":
							//results.add(rsMetaData.getColumnName(i) + ": " + Double.toString(rs.getDouble(i)));
							results.add(rs.getDouble(i));
							break;
						case "VARCHAR":
							//if(rs.getString(i) != null && rs.getString(i).toCharArray().length > 0 && rs.getString(i) != "") results.add(rsMetaData.getColumnName(i) + ": " + rs.getString(i));
							if(rs.getString(i) != null && rs.getString(i).toCharArray().length > 0 && rs.getString(i) != "") results.add(rs.getString(i));
							break;
						case "DATETIME":
							//if(rs.getDate(i) != null) results.add(rsMetaData.getColumnName(i) + ": " + rs.getDate(i));
							if(rs.getDate(i) != null) results.add(rs.getDate(i));
							break;
						default:
							//if(rs.getObject(i) != null) results.add(rsMetaData.getColumnName(i) + ": " + rs.getObject(i));
							if(rs.getObject(i) != null) results.add(rs.getObject(i));
					}
				}
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		finally {System.gc();}
		
		return results;
	}
	
	
	/**
	 * @param con
	 * @param pStmt
	 * @return Returns an ArrayList<Url> containing the result set from calling
	 * the given PreparedStatement using the specified Connection.
	 */
	protected static ArrayList<Url> executeUrlStoredProcedure(Connection con, PreparedStatement pStmt) {
		ArrayList<Url> results = new ArrayList<Url>();
		
		try(ResultSet rs = pStmt.executeQuery()) {
			while(rs.next()) {
				results.add(
						new Url(
								rs.getInt("urlId"),
								rs.getString("link"),
								rs.getString("urlDesc"),
								rs.getInt("hits"),
								rs.getBoolean("paid")
						)
				);
			}
		}
		catch(SQLException e) {e.printStackTrace();}
		finally {System.gc();}
		
		return results;
	}
}
