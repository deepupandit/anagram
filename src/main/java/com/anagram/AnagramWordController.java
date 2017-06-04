package com.anagram;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnagramWordController {
	private static final String FILENAME = "c://logs//linuxword.txt";
	
	@GetMapping("/word/{word}")
	public ResponseEntity<SucessMessage> findAnagramWord(@PathVariable ("word") String inputString) throws Exception {
		Map<String, ArrayList<String>> dictionary = new LinkedHashMap<String, ArrayList<String>>();
		
		loadData(dictionary);
		
		char[] sortedWord = inputString.toLowerCase().toCharArray();
		Arrays.sort(sortedWord);
		
		String sortedString = new String(sortedWord);
				
		if(dictionary.containsKey(sortedString)){
			
			ArrayList<String> list  = dictionary.get(sortedString);
			
			//The list of words in anagrams must not contain the requested word itself.
			list.remove(inputString.toLowerCase());
			
			//The list of words in anagrams must be sorted alphabetically.		
			Collections.sort(list);
			
			SucessMessage response = new SucessMessage(inputString,list);
						
			return new ResponseEntity<SucessMessage>(response,HttpStatus.OK);
			
		} else {
						
			NoWordFoundException exception = new NoWordFoundException(inputString);
			throw exception ;
			
		}
		
				
	}

	
	/**
	 * 
	 * @param dictionary
	 */
	
	private void loadData(Map<String, ArrayList<String>> dictionary) {
		
		BufferedReader br = null;
		FileReader fr = null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILENAME));

			while ((sCurrentLine = br.readLine()) != null) {								
				String wordfromFile = sCurrentLine.toLowerCase();
				
				char[] sortedWord = wordfromFile.toCharArray();
				Arrays.sort(sortedWord);
				
				String sortedString = new String(sortedWord);			
				
				ArrayList<String> list = new ArrayList<String>();				
				if (dictionary.containsKey(sortedString)) {					
					list = dictionary.get(sortedString);
					
				}
				list.add(wordfromFile);
				dictionary.put(sortedString, list);			
				
			}

		} catch (IOException e) {
			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
	
	/**
	 * 
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(NoWordFoundException.class)
	public ResponseEntity<Map<String,String>> wordNotFoundExceptionHandler(NoWordFoundException exception) {
		
		Map<String,String> responseMessage = new HashMap<String,String>();
		responseMessage.put("message", "Couldn't find word " + exception.getWord());
		
		return new ResponseEntity<Map<String,String>>(responseMessage,HttpStatus.NOT_FOUND);
		
	}
	
}
