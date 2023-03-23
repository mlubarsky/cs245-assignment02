/**
 * Assigment02 - Business Analyzer
 *
 * @author Maxwell Lubarsky 3/31/23
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Assignment02 {
	static int count = 0;
	
	/**
     * Read and print the contents of CSV file
     * @param file -> Pass in the CSV file
     * @return -> List that contains each line of the file read 
     */  
	public static void readFile(String file) {
        try (CSVParser parser = new CSVParser(new FileReader(file), CSVFormat.DEFAULT)) {
            List<CSVRecord> records = parser.getRecords();
            for (CSVRecord record : records) {
                    String code = record.get(16);
                    if(code != null) {
                        System.out.printf("%s\n", code);
                        count++;
                    }
            }
            System.out.printf("%d", count);
        } catch (IOException ex) { //handle an exception here
            ex.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		String file = "C:/Users/mluba/Downloads/Registered_Business_Locations_-_San_Francisco.csv";
		readFile(file);
		
		ArrayList<Assignment02> arr = new ArrayList<>();
		
	}	
}