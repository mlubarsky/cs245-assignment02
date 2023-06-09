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
import java.util.ArrayDeque;
import java.util.Queue;

public class Assignment02 {
	
	/**
     * Read and store the contents of CSV file in an Array List
     * @param file - Pass in the CSV file
     * @return - Array List that contains each line of the file read 
     */
	public static List<Data> readFileArrayList(String file) {
	    Path p2 = Path.of(file);
	    String line = null;
	    List<Data> businesses = new ArrayList<>(); //use List interface
	    String[] tag = null;

	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	        String currentLine = null;
	        System.out.println("Reading the data file into an Array List...");
	        int i = 0;
	        currentLine = reader.readLine();
	        while ((currentLine = reader.readLine()) != null) {
	            line = currentLine.trim();
	            tag = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	            i++;            
	            Data<String> entry = new Data<>(tag[16], tag[14], tag[3], tag[23], tag[9], tag[8], tag[17]);
	            businesses.add(entry);
	        }
	        reader.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    businesses.remove(0); //remove header
	    return businesses;
	}
	
	/**
     * Read and store the contents of CSV file in an Linked List
     * @param file - Pass in the CSV file
     * @return - Linked List that contains each line of the file read 
     */
	public static List<Data> readFileLinkedList(String file) {
	    Path p2 = Path.of(file);
	    String line = null;
	    List<Data> businesses = new LinkedList<>(); //use List interface
	    String[] tag = null;

	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	        String currentLine = null;
	        System.out.println("Reading the data file into a Linked List...");
	        int i = 0;
	        currentLine = reader.readLine();
	        while ((currentLine = reader.readLine()) != null) {
	            line = currentLine.trim();
	            tag = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
	            i++;            
	            Data<String> entry = new Data<>(tag[16], tag[14], tag[3], tag[23], tag[9], tag[8], tag[17]);
	            businesses.add(entry);
	        }
	        reader.close();
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	    businesses.remove(0); //remove header
	    return businesses;
	}
	
	/**
	 * Output the results for user inputed command
	 * @param command - the user's command
	 * @param dataList - list of the important pieces of data from the CSV file
	 */
	public static void respondUserList(String command, List<Data> dataList) {
	    String[] commandSplit = command.split(" ");
	    int naicsCount = 0;
	    int zipCount = 0;
	    int closedCount = 0;
	    int lastYearCount = 0;
	    List<Data> matchingZips = new ArrayList<>();
	    List<Data> matchingNeighborhoods = new ArrayList<>();
	    List<Data> matchingTypes = new ArrayList<>();
	    List<Data> endDates = new ArrayList<>();

	    if (command.contains("Zip") || command.contains("zip")) {
	    	for (Iterator<Data> iter = dataList.Iterator(); iter.hasNext();) {
	            Data data = iter.next();
	            if (data.getZipCode().equals(commandSplit[1])) //compare zip in command to zip in file
	                zipCount++;

	            boolean uniqueHoods = true;
	            for (Iterator<Data> it = matchingNeighborhoods.Iterator(); it.hasNext();) { //check unique neighborhoods
	                if (it.next().getNeighborhood().equals(data.getNeighborhood())) {
	                    uniqueHoods = false;
	                }
	            }
	            if (uniqueHoods)
	                matchingNeighborhoods.add(data);

	            boolean uniqueTypes = true;
	            for (Iterator<Data> it = matchingTypes.Iterator(); it.hasNext();) { //check unique business types
	                if (it.next().getBusinessType().equals(data.getBusinessType())) {
	                    uniqueTypes = false;
	                }
	            }
	            if (uniqueTypes)
	                matchingTypes.add(data);
	        }
	    	
	    	//output
	        System.out.println("Total Businesses: " + zipCount);
	        System.out.println("Business Types: " + matchingTypes.size());
	        System.out.println("Neighborhoods: " + matchingNeighborhoods.size());

	    } else if (command.contains("NAICS") || command.contains("naics") || command.contains("Naics")) {
	    	for (Iterator<Data> iter = dataList.Iterator(); iter.hasNext();) {
	            Data data = iter.next();
	            if (data.naicsCodeInRange(commandSplit[1])) { //check for NAICS in command to be in the range from the NAICS in file
	                naicsCount++;

	                boolean uniqueZips = true;
	                for (Iterator<Data> it = matchingZips.Iterator(); it.hasNext();) { //check unique zips
	                    if (it.next().getZipCode().equals(data.getZipCode())) {
	                        uniqueZips = false;
	                    }
	                }
	                if (uniqueZips)
	                    matchingZips.add(data);

	                boolean uniqueHoods = true;
	                for (Iterator<Data> it = matchingNeighborhoods.Iterator(); it.hasNext();) { //check unique neighborhoods
	                    if (it.next().getNeighborhood().equals(data.getNeighborhood()))
	                        uniqueHoods = false;
	                }
	                if (uniqueHoods)
	                    matchingNeighborhoods.add(data);
	            }
	        }

	    	//output
	        System.out.println("Total Businesses: " + naicsCount);
	        System.out.println("Zip Codes: " + matchingZips.size());
	        System.out.println("Neighborhoods: " + matchingNeighborhoods.size());

	    } else if (command.equals("Summary") || command.equals("summary")){
	    	 for (Iterator<Data> iter = dataList.Iterator(); iter.hasNext();) {
	            Data data = iter.next();
	            if (!data.getEndDate().equals("")) //check if business end date is empty
	                closedCount++;
	            String[] startDateSplit = ((String) data.getStartDate()).split("/");
	            if (startDateSplit[2].equals("2022") || startDateSplit[2].equals("2023")) //check if business start date was in 2022 or 2023
	                lastYearCount++;
	        }

	    	 //output
	        System.out.println("Total Businesses: " + dataList.size());
	        System.out.println("Closed Businesses: " + closedCount);
	        System.out.println("New Businesses in last year: " + lastYearCount);
	    }
	}

	public static void main(String[] args) {
		String file = args[0];
		Scanner scnr = new Scanner(System.in);
		Queue<String> myQueue = new ArrayDeque<>();
		List<Data> dataList;

		//Read into appropriate data structure based on args[1] tag
		if (args[1].equals("AL")) {
		    dataList = new ArrayList<>();
		    dataList = readFileArrayList(file);
		} else if (args[1].equals("LL")) {
		    dataList = new LinkedList<>();
		    dataList = readFileLinkedList(file);
		} else {
		    System.out.println("Invalid argument: " + args[1]);
		    return;
		}
		
		String command = "";
		while (true) { //Keep asking for input until quit or exit is entered
		    System.out.print("\nCommand: (Quit or Exit to exit) ");
		    command = scnr.nextLine();	
		    if (args[1].equals("AL")) {
		    	respondUserList(command, dataList);
		    }
		    if (args[1].equals("LL")) {
		    	respondUserList(command, dataList);
		    }
		    if (command.equals("History")) { 
		        System.out.println(myQueue);
		    } else if (command.equals("Quit") || command.equals("quit") || command.equals("Exit") || command.equals("exit")){
		        break;
		    } else {
		        myQueue.add(command);
		    }
		}	
	}	
}