/**
 * Assigment02 - Business Analyzer
 *
 * @author Maxwell Lubarsky 3/31/23
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment02 {
	
	/**
     * Read and print the contents of CSV file
     * @param file -> Pass in the CSV file
     * @return -> List that contains each line of the file read
     */ 
	public static void readFile(String file) {
		Path p2 = Path.of(file);
		List<String> line = new ArrayList<String>();
		
	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	    	String currentLine = null; //while there is content on the current line
	    	System.out.println("Reading the data file...");
	    	while ((currentLine = reader.readLine()) != null) {
	            line.add(currentLine.trim()); //adds line into a list
	            System.out.println(currentLine);
	        }
	        reader.close();
	    } catch (IOException ex) { //handle an exception here
	        ex.printStackTrace(); 
	    }
	}

	
	public static void main(String[] args) {
		String file = "C:/Users/mluba/Downloads/Registered_Business_Locations_-_San_Francisco.csv";
		readFile(file);
	}	
}