package com.cyberminer.kwic;

import java.util.ArrayList;

import com.cyberminer.url.Url;

/**
 * CircularShift.java
 * 
 * @author James
 * Date: July 27th, 2020
 */
public class CircularShift {
	private IndexStorage indexes;
	
	/**
	 * @param url		This is a Url object containing the original url, description, hits, and paid.
	 * @param indexes	This is an IndexStorage object containing the indexes of the circularly shifted lines.
     * @return          Returns a boolean that is true on success, else false.
	 */
	public boolean shift(Url url, IndexStorage indexes) {
		this.indexes = indexes;
		ArrayList<Integer> wordIdx = new ArrayList<Integer>();
		
		wordIdx.add(0);
		for(int pos = 0; pos < url.getDesc().length(); pos++) {
			if(url.getDesc().charAt(pos) == ' ') {
				wordIdx.add(pos + 1);
				pos++;
			}
		}
		wordIdx.add(url.getDesc().length());
		
		for(int i = 0; i < wordIdx.size() - 1; i++) {
			String word = url.getDesc().substring(wordIdx.get(i), wordIdx.get(i + 1) - (i == wordIdx.size() - 2 ? 0 : 1));
			indexes.add(url.getId(), wordIdx.get(i) + 1, word);
		}
		
		return Kwic.alphaShift.alpha(this.indexes);
	}
}
