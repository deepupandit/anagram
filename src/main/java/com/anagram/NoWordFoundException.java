package com.anagram;

public class NoWordFoundException extends Exception {	/**
	 * 
	 */
	private static final long serialVersionUID = -6960741551234679248L;
	
	private String word;
	
	

	public String getWord() {
		return word;
	}



	public void setWord(String word) {
		this.word = word;
	}



	public NoWordFoundException(String word){
		super();
		this.word = word;
	}

}
