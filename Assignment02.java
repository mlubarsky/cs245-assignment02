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
import java.util.Scanner;

public class Assignment02 {
	
	/**
     * Read and print the contents of CSV file
     * @param file - Pass in the CSV file
     * @return - List that contains each line of the file read 
     */  
	public static ArrayList<String> readFileArrayList(String file) {
		Path p2 = Path.of(file);
		ArrayList<String> line = new ArrayList<>();
		ArrayList<String> tags = new ArrayList<String>();
		String[] tag = null;	
		
	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	    	String currentLine = null; //while there is content on the current line
	    	System.out.println("Reading the data file...");
	    	int i = 0;
	    	Data<String> naicsCode = new Data<>();
	    	Data<String> zipCode = new Data<>();
	    	Data<String> businessName = new Data<>();
	    	Data<String> neighborhood = new Data<>();
	    	while ((currentLine = reader.readLine()) != null) {
	            line.add(currentLine.trim()); //adds line into a list
    			tag = line.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); //splits line into separate elements to get the tag
    			i++;
    			naicsCode.setNaicsCode(tag[16]);
    			zipCode.setZipCode(tag[14]);
    			businessName.setBusinessName(tag[3]);
    			neighborhood.setNeighborhood(tag[23]);
    			String data = naicsCode.getNaicsCode() + "," + zipCode.getZipCode() + "," +
    			businessName.getBusinessName() + "," + neighborhood.getNeighborhood();
    			tags.add(data);
	        }
	        reader.close();
	    } catch (IOException ex) { //handle an exception here
	        ex.printStackTrace(); 
	    }
	    tags.remove(0); //remove header line
	    return tags;
	}
	
	
	/**
     * Read and print the contents of CSV file
     * @param file - Pass in the CSV file
     * @return - List that contains each line of the file read 
     */  
	public static LinkedList<String> readFileLinkedList(String file) {
		Path p2 = Path.of(file);
		LinkedList<String> line = new LinkedList<String>();
		LinkedList<String> tags = new LinkedList<String>();
		String[] tag = null;
		
	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	    	String currentLine = null; //while there is content on the current line
	    	System.out.println("Reading the data file...");
	    	int i = 0;
	    	Data<String> naicsCode = new Data<>();
	    	Data<String> zipCode = new Data<>();
	    	Data<String> businessName = new Data<>();
	    	Data<String> neighborhood = new Data<>();
	    	while ((currentLine = reader.readLine()) != null) {
	    		line.add(currentLine.trim()); //adds line into a list
    			tag = line.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); //splits line into separate elements to get the tag
    			i++;
    			naicsCode.setNaicsCode(tag[16]);
    			zipCode.setZipCode(tag[14]);
    			businessName.setBusinessName(tag[3]);
    			neighborhood.setNeighborhood(tag[23]);
    			String data = naicsCode.getNaicsCode() + "," + zipCode.getZipCode() + "," +
    			businessName.getBusinessName() + "," + neighborhood.getNeighborhood();
    			tags.add(data);
	        }
	        reader.close();
	    } catch (IOException ex) { //handle an exception here
	        ex.printStackTrace(); 
	    }
	    tags.remove(0); //remove header line
	    return tags;
	}
	
	//TODO: implement
	public static String respondUser(String command, ArrayList<String> dataArray) {
		String[] data = null;
		String[] naicsCode = null;
		String naicsCodeLowerBound = null;
		String naicsCodeUpperBound = null;
		String zipCode = null;
		String[] commandSplit = command.split(" ");
		int naicsCount = 0;
		int zipCount = 0;
		
		for(int i = 0; i < dataArray.size(); i++) { 
			data = dataArray.get(i).split(",");
			if (data[0] != "") {
				naicsCode = data[0].split("-");
				naicsCodeLowerBound = naicsCode[0];
				naicsCodeUpperBound = naicsCode[1];
			}
			if (data[1] != "") {
				zipCode = data[1];
			}
			
			if(command.contains("NAICS")) {
				if(naicsCodeLowerBound.compareTo(commandSplit[1]) <= 0 && naicsCodeUpperBound.compareTo(commandSplit[1]) >= 0)
					naicsCount++;
			} else if (command.contains("Zip")) {
				if(zipCode.compareTo(commandSplit[1]) <= 0 && zipCode.compareTo(commandSplit[1]) >= 0)
					zipCount++;
			} else {
				
			}
		}
		System.out.println(zipCount);
		return null;
	}
	
	public static void main(String[] args) {
		String file = "C:/Users/mluba/Downloads/Registered_Business_Locations_-_San_Francisco.csv";
		Scanner scnr = new Scanner(System.in);
		
//		if (args[1] == "AL") {
//			ArrayList<String> AL = readFileArrayList(file);
//		} else if (args[1] == "LL") {
//			LinkedList<String> LL = readFileLinkedList(file);
//		}
		
		ArrayList<String> AL = readFileArrayList(file);
		System.out.println("Command: ");
		String command = scnr.nextLine();
		
		respondUser(command, AL);
		
	}	
}