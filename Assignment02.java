/**
 * Assigment02 - Business Analyzer
 *
 * @author Maxwell Lubarsky 3/31/23
 */
import java.io.FileReader;
import java.io.IOException;
//import com.opencsv.CSVReader;
//import com.opencsv.exceptions.CsvValidationException;

public class Assignment02 {
	
	/**
     * Read and print the contents of CSV file
     * @param file -> Pass in the CSV file
     * @return -> List that contains each line of the file read 
     */ 
	public static void readFile(String file) {
        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                String[] values = new String[line.length];
                for (int i = 0; i < line.length; i++) {
                    values[i] = line[i];
                }
                for (String value : values) {
                    System.out.print(value + " | ");
                }
                System.out.println();
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		String file = "C:/Users/mluba/Downloads/Registered_Business_Locations_-_San_Francisco.csv";
//		readFile(file);
		
		ArrayList<Assignment02> naics = new ArrayList<>();
		naics.add("hello");
		System.out.println(naics.size());
	}	
}