package com.anagram;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"word","anagrams"})
public class SucessMessage {
	
	@JsonProperty("word")
	private String searchKey ;
	
	@JsonProperty("anagrams")
	private ArrayList<String> anangramWords = new ArrayList<String>();
	
	
	
	public SucessMessage(String searchKey, ArrayList<String> anangramWords) {
		super();
		this.searchKey = searchKey;
		this.anangramWords = anangramWords;
	}
	
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public ArrayList<String> getAnangramWords() {
		return anangramWords;
	}
	public void setAnangramWords(ArrayList<String> anangramWords) {
		this.anangramWords = anangramWords;
	}
	
	

}
