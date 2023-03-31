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
import java.util.PriorityQueue;
import java.util.Queue;

public class Assignment02 {
	
	/**
     * Read and store the contents of CSV file in an Array List
     * @param file - Pass in the CSV file
     * @return - Array List that contains each line of the file read 
     */  
	public static ArrayList<Data> readFileArrayList(String file) {
		Path p2 = Path.of(file);
		ArrayList<String> line = new ArrayList<>();
		ArrayList<Data> businesses = new ArrayList<>();
		String[] tag = null;	
		
	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	    	String currentLine = null; //while there is content on the current line
	    	System.out.println("Reading the data file into an Array List...\n");
	    	int i = 0;
	    	while ((currentLine = reader.readLine()) != null) {
	            line.add(currentLine.trim()); //adds line into a list
    			tag = line.get(i).split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); //splits line into separate elements to get the tag
    			i++;
    			Data<String> entry = new Data<>(tag[16], tag[14], tag[3], tag[23], tag[9], tag[8], tag[17]);
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
     * Read and store the contents of CSV file in a Linked List
     * @param file - Pass in the CSV file
     * @return - Linked List that contains each line of the file read 
     */  
	public static LinkedList<Data> readFileLinkedList(String file) {
		Path p2 = Path.of(file);
		String line = null;
		LinkedList<Data> businesses = new LinkedList<>();
		String[] tag = null;	
		
	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	    	String currentLine = null; //while there is content on the current line
	    	System.out.println("Reading the data file into a Linked List...");
	    	int i = 0;
	    	currentLine = reader.readLine();
	    	while ((currentLine = reader.readLine()) != null) {
	            line = currentLine.trim(); //adds line into a list
				tag = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); //splits line into separate elements to get the tag
				i++;
				Data<String> entry = new Data<>(tag[16], tag[14], tag[3], tag[23], tag[9], tag[8], tag[17]);
				businesses.add(entry);
	        }
	        reader.close();
	    } catch (IOException ex) { //handle an exception here
	        ex.printStackTrace(); 
	    }
	    businesses.remove(0); //remove head line
	    return businesses;
	}
	
	/**
	 * 
	 * @param command
	 * @param dataArray
	 */
	public static void respondUserArrayList(String command, ArrayList<Data> dataArray) {
		String[] commandSplit = command.split(" ");
		int naicsCount = 0;
		int zipCount = 0;
		int closedCount = 0;
		int lastYearCount = 0;
		ArrayList<Data> matchingZips = new ArrayList<>();
		ArrayList<Data> matchingNeighborhoods = new ArrayList<>();
		ArrayList<Data> matchingTypes = new ArrayList<>();
		ArrayList<Data> endDates = new ArrayList<>();
		
		if (command.contains("Zip")) {
			for (int i = 0; i < dataArray.size(); i++) {
				if (dataArray.get(i).getZipCode().equals(commandSplit[1]))
					zipCount++;
				
				boolean uniqueHoods = true;
				boolean uniqueTypes = true;
				for (int j = 0; j < matchingNeighborhoods.size(); j++) {
					if (matchingNeighborhoods.get(j).getNeighborhood().equals(dataArray.get(i).getNeighborhood())) {
						uniqueHoods = false;
					}
				} if (uniqueHoods)
					matchingNeighborhoods.add(dataArray.get(i));
				
				for (int j = 0; j < matchingTypes.size(); j++) {
					if (matchingTypes.get(j).getBusinessType().equals(dataArray.get(i).getBusinessType())) {
						uniqueTypes = false;
					}
				} if (uniqueTypes)
					matchingTypes.add(dataArray.get(i));
			}
			
			System.out.println("Total Businesses: " + zipCount);
			System.out.println("Business Types: " + matchingTypes.size());
			System.out.println("Neighborhoods: " + matchingNeighborhoods.size());
			
		} else if (command.contains("NAICS")) {
			for (int i = 0; i < dataArray.size(); i++) {
				if (dataArray.get(i).naicsCodeInRange(commandSplit[1])) {
						naicsCount++;
						
					boolean uniqueHoods = true;
					boolean uniqueZips = true;
					
					for (int j = 0; j < matchingZips.size(); j++) {
						if (matchingZips.get(j).getZipCode().equals(dataArray.get(i).getZipCode())) {
							uniqueZips = false;
						}
					} if (uniqueZips)
						matchingZips.add(dataArray.get(i));
					
					for (int j = 0; j < matchingNeighborhoods.size(); j++) {
						if (matchingNeighborhoods.get(j).getNeighborhood().equals(dataArray.get(i).getNeighborhood()))
							uniqueHoods = false;
					} if (uniqueHoods)
						matchingNeighborhoods.add(dataArray.get(i));
				}
			}
			
			System.out.println("Total Businesses: " + naicsCount);
			System.out.println("Zip Codes: " + matchingZips.size());
			System.out.println("Neighborhoods: " + matchingNeighborhoods.size());
			
		} else {
			for (int i = 0; i < dataArray.size(); i++) {
				if (!dataArray.get(i).getEndDate().equals(""))
					closedCount++;
				String [] startDateSplit = ((String) dataArray.get(i).getStartDate()).split("/");
				if(startDateSplit[2].equals("2022") || startDateSplit[2].equals("2023"))
					lastYearCount++;
			}
			
			System.out.println("Total Businesses: " + dataArray.size());	
			System.out.println("Closed Businesses: " + closedCount);
			System.out.println("New Businesses in last year: " + lastYearCount);
		}
	}
	
	/**
	 * 
	 * @param command
	 * @param dataArray
	 */
	public static void respondUserLinkedList(String command, LinkedList<Data> dataArray) {
		String[] commandSplit = command.split(" ");
		int naicsCount = 0;
		int zipCount = 0;
		int closedCount = 0;
		int lastYearCount = 0;
		ArrayList<Data> matchingZips = new ArrayList<>();
		ArrayList<Data> matchingNeighborhoods = new ArrayList<>();
		ArrayList<Data> matchingTypes = new ArrayList<>();
		ArrayList<Data> endDates = new ArrayList<>();
		
		if (command.contains("Zip")) {
			Node<Data> currentNode = dataArray.getHead();
			while (currentNode != null) {
				if(currentNode.data.getZipCode().equals(commandSplit[1])) {
					zipCount++;
						
					boolean uniqueHoods = true;
					boolean uniqueTypes = true;
					for (int i = 0; i < matchingNeighborhoods.size(); i++) {
						if (matchingNeighborhoods.get(i).getNeighborhood().equals(currentNode.data.getNeighborhood()))
							uniqueHoods = false;
					} if (uniqueHoods)
						matchingNeighborhoods.add(currentNode.data);
					
					for (int i = 0; i < matchingTypes.size(); i++) {
						if (matchingTypes.get(i).getBusinessType().equals(currentNode.data.getBusinessType()))
							uniqueTypes = false;
					} if (uniqueTypes)
						matchingTypes.add(currentNode.data);
				}
				currentNode = currentNode.next;
			}
			
			System.out.println("Total Businesses: " + zipCount);
			System.out.println("Business Types: " + matchingTypes.size());
			System.out.println("Neighborhoods: " + matchingNeighborhoods.size());
			
		} else if(command.contains("NAICS")) {
			Node<Data> currentNode = dataArray.getHead();
			
			while (currentNode != null) {
				if (currentNode.data.naicsCodeInRange(commandSplit[1])) {
					naicsCount++;

					boolean uniqueZips = true;
					boolean uniqueHoods = true;
					for (int i = 0; i < matchingZips.size(); i++) {
						if (matchingZips.get(i).getZipCode().equals(currentNode.data.getZipCode()))
							uniqueZips = false;
					} if (uniqueZips)
						matchingZips.add(currentNode.data);
					
					for (int i = 0; i < matchingNeighborhoods.size(); i++) {
						if (matchingNeighborhoods.get(i).getNeighborhood().equals(currentNode.data.getNeighborhood()))
							uniqueHoods = false;
					} if (uniqueHoods)
						matchingNeighborhoods.add(currentNode.data);
				}
				currentNode = currentNode.next;
			}
			System.out.println("Total Businesses: " + naicsCount);
			System.out.println("Zip Codes: " + matchingZips.size());
			System.out.println("Neighborhoods: " + matchingNeighborhoods.size());
		} else {
			Node<Data> currentNode = dataArray.getHead();
			System.out.println("got here 1");
			while (currentNode != null) {
				System.out.println("got here 2");
				if (!currentNode.data.getEndDate().equals(""))
					closedCount++;
				System.out.println("got here 3");
				String [] startDateSplit = ((String) currentNode.data.getStartDate()).split("/");
				if(startDateSplit[2].equals("2022") || startDateSplit[2].equals("2023"))
					lastYearCount++;
				System.out.println("got here 4");
			}	
			System.out.println("Total Businesses: " + dataArray.size());	
			System.out.println("Closed Businesses: " + closedCount);
			System.out.println("New Businesses in last year: " + lastYearCount);
		}
	}
	
	
	public static void main(String[] args) {
		String file = "C:/Users/mluba/Downloads/Registered_Business_Locations_-_San_Francisco.csv";
		Scanner scnr = new Scanner(System.in);
		
//		if (args[1] == "AL") {
//			ArrayList<String> AL = readFileArrayList(file);
//		} else if (args[1] == "LL") {
//			LinkedList<String> LL = readFileLinkedList(file);
//		}
		
		//ArrayList<Data> AL = readFileArrayList(file);
		LinkedList<Data> LL = readFileLinkedList(file);
		Queue<String> myQueue = new PriorityQueue<>();
		
		String command = "";
		
		while (!command.equals("Quit")) {
			System.out.print("\nCommand: (Type Quit to exit) ");
			command = scnr.nextLine();	
			if (command.equals("History")) {
				System.out.println(myQueue);
			} else if (command.equals("Quit")){
				break;
			} else {
				//respondUserArrayList(command, AL);
				respondUserLinkedList(command, LL);
				myQueue.add(command);
			}
		}
	}	
}