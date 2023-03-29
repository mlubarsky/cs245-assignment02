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
	public static ArrayList<Data> readFileArrayList(String file) {
		Path p2 = Path.of(file);
		ArrayList<String> line = new ArrayList<>();
		ArrayList<Data> businesses = new ArrayList<>();
		String[] tag = null;	
		
	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	    	String currentLine = null; //while there is content on the current line
	    	System.out.println("Reading the data file...");
	    	int i = 0;
	    	while ((currentLine = reader.readLine()) != null) {
	            line.add(currentLine.trim()); //adds line into a list
    			tag = line.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); //splits line into separate elements to get the tag
    			i++;
    			Data<String> entry = new Data<>(tag[16], tag[14], tag[3], tag[23]);
    			businesses.add(entry);
	        }
	        reader.close();
	    } catch (IOException ex) { //handle an exception here
	        ex.printStackTrace(); 
	    }
	    businesses.remove(0); //remove header line
	    return businesses;
	}
	
	/**
     * Read and print the contents of CSV file
     * @param file - Pass in the CSV file
     * @return - List that contains each line of the file read 
     */  
	public static LinkedList<Data> readFileLinkedList(String file) {
		Path p2 = Path.of(file);
		String line = null;
		LinkedList<Data> businesses = new LinkedList<>();
		String[] tag = null;	
		
	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	    	String currentLine = null; //while there is content on the current line
	    	System.out.println("Reading the data file...");
	    	int i = 0;
	    	currentLine = reader.readLine();
	    	while ((currentLine = reader.readLine()) != null) {
	            line = currentLine.trim(); //adds line into a list
				tag = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); //splits line into separate elements to get the tag
				i++;
				Data<String> entry = new Data<>(tag[16], tag[14], tag[3], tag[23]);
				businesses.add(entry);
	        }
	        reader.close();
	    } catch (IOException ex) { //handle an exception here
	        ex.printStackTrace(); 
	    }
	    return businesses;
	}
	
	//TODO: implement
	public static String respondUserArrayList(String command, ArrayList<Data> dataArray) {
		String[] commandSplit = command.split(" ");
		int naicsCount = 0;
		int zipCount = 0;
		ArrayList<String> matchingZips = new ArrayList<>();
		
		if (command.contains("Zip")) {
			for (int i = 0; i < dataArray.size(); i++) {
				if(dataArray.get(i).getZipCode().equals(commandSplit[1]))
					zipCount++;
			}
			System.out.println(zipCount);
		} else if(command.contains("NAICS")) {
			for (int i = 0; i < dataArray.size(); i++) {
				if(dataArray.get(i).naicsCodeInRange(commandSplit[1]))
					naicsCount++;
				boolean unique = true;
				for (int j = 0; j < matchingZips.size; j++) {
					if (matchingZips.get(j) == dataArray.get(j).getZipCode()) {
						unique = false;
					}
				}
				if (unique)
					matchingZips.add(dataArray.get(i).getZipCode());
			}
			System.out.println("Total Businesses: " + naicsCount);
			System.out.println("Zip Codes: " + matchingZips.size);
		} else {
				
		}
		return null;
	}
	
	public static String respondUserLinkedList(String command, LinkedList<Data> dataArray) {
		String[] commandSplit = command.split(" ");
		int naicsCount = 0;
		int zipCount = 0;
		ArrayList<String> matchingZips = new ArrayList<>();
		
		if (command.contains("Zip")) {
			Node<Data> currentNode = dataArray.getHead();
			while (currentNode != null) {
				System.out.println(currentNode.data.getZipCode() + "," + commandSplit[1]);
				if(currentNode.data.getZipCode().equals(commandSplit[1]))
					zipCount++;
				currentNode = currentNode.next;
			}
			System.out.println("Total Businesses: " +  zipCount);
		} else if(command.contains("NAICS")) {
			Node<Data> currentNode = dataArray.getHead();
			while (currentNode != null) {
				if(currentNode.data.naicsCodeInRange(commandSplit[1])) {
					naicsCount++;
					boolean unique = true;
					for (int i = 0; i < matchingZips.size; i++) {
						if (matchingZips.get(i) == currentNode.data.getZipCode()) {
							unique = false;
						}
					}
					if (unique)
						matchingZips.add(currentNode.data.getZipCode());
				}
				currentNode = currentNode.next;
			}
			System.out.println("Total Businesses: " + naicsCount);
			System.out.println("Zip Codes: " + matchingZips.size);
		} else {
				
		}
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
		
		ArrayList<Data> AL = readFileArrayList(file);
		//LinkedList<Data> LL = readFileLinkedList(file);
		System.out.println("Command: ");
		String command = scnr.nextLine();
		
		respondUserArrayList(command, AL);
		
	}	
}