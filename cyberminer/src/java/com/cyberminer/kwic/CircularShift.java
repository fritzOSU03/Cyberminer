package com.cyberminer.kwic;

import java.util.ArrayList;

import com.cyberminer.url.Url;

/**
 * CircularShift.java
 * 
 * @author James
 * Date: July 23rd, 2020
 */
public class CircularShift {
	private IndexStorage indexes;
	
	/**
	 * @param url		This is a Url object containing the original url, description, hits, and paid.
	 * @param indexes	This is an IndexStorage object containing the indexes of the circularly shifted lines.
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
			addNewKeyword(url.getId(), wordIdx.get(i) + 1, word);
		}
		
		return Kwic.alphaShift.alpha(this.indexes);
	}
	
	/**
	 * @param urlId	This is an int representing the position of the line in LineStorage.
	 * @param wordPos	This is an int representing the position of the word in the line in LineStorage.
	 * @param word		This is a String containing the word to be stored in the IndexStorage record.
	 */
	private void addNewKeyword(int urlId, int wordPos, String word) {
		indexes.add(urlId, wordPos, word);
	}
}
