package com.hemebiotech.analytics;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple brute force implementation
 *
 */
public class ReadSymptomDataFromFile implements ISymptomReader {

	private String filename;
	
	/**
	 * 
	 * @param filename a partial path to file with symptom strings in it, one per line
	 */
	public ReadSymptomDataFromFile (String filename) {
		this.filename = filename;
	}
	
	@Override
	public List<String> GetSymptoms() {
		ArrayList<String> result = new ArrayList<>();
		
		if (filename != null) {
			try {
				InputStream inputStream = getFileFromResourcesAsStream(filename);
				BufferedReader reader = new BufferedReader (new InputStreamReader(inputStream));
				String line = reader.readLine();
				
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	/**
	 * @param filename a string that represents a file in the resources folder
	 * @return a stream retrieved from the classLoader or throw an exception if the file doesn't exist
	 * @throws FileNotFoundException
	 */
	private InputStream getFileFromResourcesAsStream(String filename) throws FileNotFoundException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classloader.getResourceAsStream(filename);
		if(inputStream == null){
			throw new FileNotFoundException("File not found "+filename);
		}
		return inputStream;
	}
}
