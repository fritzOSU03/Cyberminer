package com.cyberminer.kwic;

import java.util.Arrays;

/**
 * AlphaShift.java
 * 
 * @author James
 * Date: July 27rd, 2020
 */
public class AlphaShift {
	private IndexStorage indexes = null;
	private final String[] noiseWords = {"a", "an", "and", "at", "by", "for", "of", "or", "the", "to"};
	
	/**
	 * @param indexes		This is an IndexStorage object that is to be alphabetically sorted.
     * @return              Returns a boolean that is true on success, else false.
	 */
	public boolean alpha(IndexStorage indexes) {
		try {
			//Make a local copy of the IndexStorage object.
			this.indexes = indexes;
			
			//Sort the indexes.
			for(int j = 0; j < this.indexes.getSize(); j++)
				for(int i = 0; i < this.indexes.getSize(); i++)
					if( this.indexes.getKeyword(j).compareToIgnoreCase(this.indexes.getKeyword(i)) <= 0) {
						this.indexes.insert(i, this.indexes.get(j));
						this.indexes.delete(j + 1);
						break;
					}
			
			//Once sorted, add each index to the database.
			for(Index idx : this.indexes.indexes) {
				if(!Arrays.asList(noiseWords).contains(idx.getKeyword().toLowerCase()))
                    if(idx.addIndex() == 0) return false;
			}
			
			//Clear IndexStorage once the current URL indexes are added.
			this.indexes.reset();
			
			return true;
		}
		catch(Exception e) {e.printStackTrace();}
        return false;
	}
}
