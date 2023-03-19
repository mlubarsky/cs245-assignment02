/**
 * Assigment01 - Movie Tags
 *
 * @author Maxwell Lubarsky 2/22/23
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Assignment01 {
	
	/**
     * Read and print the contents of the tags.csv file
     * @param file -> Pass in the tags.csv file
     * @return -> List that contains each line of the file read
     */ 
	public static List<String> readFile(String file) {
		Path p2 = Path.of(file);
		List<String> line = new ArrayList<String>();
		List<String> tags = new ArrayList<String>();
		String[] tag = null;
		
	    try (BufferedReader reader = Files.newBufferedReader(p2, StandardCharsets.UTF_8)) {
	    	String currentLine = null; //while there is content on the current line
	    	System.out.println("Reading the data file...");
	    	int i = 0;
	    	while ((currentLine = reader.readLine()) != null) {
	            line.add(currentLine.trim()); //adds line into a list
    			tag = line.get(i).split(","); //splits line into separate elements to get the tag
    			i++;
    			tags.add(tag[2]);
	        }
	        reader.close();
	    } catch (IOException ex) { //handle an exception here
	        ex.printStackTrace(); 
	    }
	    tags.remove(0); //remove header line
	    return tags;
	}
	
	/**
     * Display the frequency of the top 3 highest and lowest tags
     * @param tags -> Pass in the read contents of the tags.csv file
     * @return -> Print count of top 3 highest and lowest tags
     */ 
	public static void frequencyTags(List<String> tags) {
		List<String> uniqueList = new ArrayList<>();
		List<Integer> frequency = new ArrayList<>();
		int count = 1;
		int i = 0;
		quickSort(tags, 0, tags.size() - 1);
		uniqueList.add(tags.get(0));
		frequency.add(count);
		for (int j = 0; j < tags.size() - 1; j++) {
			if(tags.get(j).compareTo(tags.get(j + 1)) == 0) { //increment the frequency for that tag
				count++;
				frequency.set(i, count);
			} else { 
				uniqueList.add(tags.get(j + 1)); //add the new tag to the list, set its frequency to 1
				count = 1;
				frequency.add(count);
				i++;
			}
		}
		int[] highestFrequencies = {0, 0, 0};
		String[] mostFrequentTags = {null, null, null};
		int[] lowestFrequencies = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
		String[] leastFrequentTags = {null, null, null};
		for (int j = 0; j < uniqueList.size(); j++) {
		    int freq = frequency.get(j);
		    if (freq > highestFrequencies[0]) {
		        highestFrequencies[2] = highestFrequencies[1];
		        mostFrequentTags[2] = mostFrequentTags[1];
		        highestFrequencies[1] = highestFrequencies[0];
		        mostFrequentTags[1] = mostFrequentTags[0];
		        highestFrequencies[0] = freq;
		        mostFrequentTags[0] = uniqueList.get(j);
		    } else if (freq > highestFrequencies[1]) {
		        highestFrequencies[2] = highestFrequencies[1];
		        mostFrequentTags[2] = mostFrequentTags[1];
		        highestFrequencies[1] = freq;
		        mostFrequentTags[1] = uniqueList.get(j);
		    } else if (freq > highestFrequencies[2]) {
		        highestFrequencies[2] = freq;
		        mostFrequentTags[2] = uniqueList.get(j);
		    }
		}
		for (int j = 0; j < uniqueList.size(); j++) {
		    int freq = frequency.get(j);
		    if (freq < lowestFrequencies[0]) {
		        lowestFrequencies[2] = lowestFrequencies[1];
		        leastFrequentTags[2] = leastFrequentTags[1];
		        lowestFrequencies[1] = lowestFrequencies[0];
		        leastFrequentTags[1] = leastFrequentTags[0];
		        lowestFrequencies[0] = freq;
		        leastFrequentTags[0] = uniqueList.get(j);
		    } else if (freq < lowestFrequencies[1]) {
		        lowestFrequencies[2] = lowestFrequencies[1];
		        leastFrequentTags[2] = leastFrequentTags[1];
		        lowestFrequencies[1] = freq;
		        leastFrequentTags[1] = uniqueList.get(j);
		    } else if (freq < lowestFrequencies[2]) {
		        lowestFrequencies[2] = freq;
		        leastFrequentTags[2] = uniqueList.get(j);
		    }
		}
		if (lowestFrequencies[2] == lowestFrequencies[1]) {
		    if (leastFrequentTags[2].compareTo(leastFrequentTags[1]) < 0) {
		        String tempTag = leastFrequentTags[2];
		        leastFrequentTags[2] = leastFrequentTags[1];
		        leastFrequentTags[1] = tempTag;
		    }
		}
		if (lowestFrequencies[1] == lowestFrequencies[0]) {
		    if (leastFrequentTags[1].compareTo(leastFrequentTags[0]) < 0) {
		        String tempTag = leastFrequentTags[1];
		        leastFrequentTags[1] = leastFrequentTags[0];
		        leastFrequentTags[0] = tempTag;
		    }
		}
		System.out.println("*** Highest 3 movies by count ***");
        System.out.println(highestFrequencies[0] + ": " + mostFrequentTags[0]);
        System.out.println(highestFrequencies[1] + ": " + mostFrequentTags[1]);
        System.out.println(highestFrequencies[2] + ": " + mostFrequentTags[2]);
        System.out.println("*** Lowest 3 movies by count ***");
        System.out.println(lowestFrequencies[0] + ": " + leastFrequentTags[0]);
        System.out.println(lowestFrequencies[1] + ": " + leastFrequentTags[1]);
        System.out.println(lowestFrequencies[2] + ": " + leastFrequentTags[2]);
		searchTags(uniqueList, frequency);
	}
	
	/**
	 * Search for a specific tag and display it's count or tags with a specific count
	 * @param uniqueList -> List of tags
	 * @param frequency -> Frequency of each tag from uniqueList
	 * @return -> Tag with count or tags of a certain count
	 */
	public static void searchTags(List<String> uniqueList, List<Integer> frequency) {
		Scanner scnr = new Scanner(System.in); 
		System.out.println("Search by Tag or Tag Count? (Enter T or C... or EXIT to exit):");
		String input = scnr.nextLine();
       	if (!(input.equals("T") || input.equals("C") || //input validation
       		input.equals("t") || input.equals("c")|| 
       		input.equals("EXIT") || input.equals("exit"))) { 
       		while (!(input.equals("T") || input.equals("C") ||
       	       		input.equals("t") || input.equals("c")|| 
       	       		input.equals("EXIT") || input.equals("exit"))) {
       			System.out.println("Invalid Input. Enter T or C or EXIT");
       			input = scnr.nextLine();
       		}
        } 
       	if (input.equals("T") || input.equals("t")) { //search for tags
       		System.out.println("Tag to search for: ");
       		boolean tagFlag = false;
       		String tagToPrint = null;
       		int countToPrint = 0;
       		String tagToSearch = scnr.nextLine();
       		for (int i = 0; i < uniqueList.size() - 1; i++) {
       			if (tagToSearch.compareTo(uniqueList.get(i)) == 0) {
       				tagToPrint = uniqueList.get(i);
       				countToPrint = frequency.get(i);
       				tagFlag = true;
       			} 
       		}
       		if (tagFlag == true) { //print tags
	       		System.out.println("Tag \"" + tagToPrint + "\" occured " + countToPrint + " times.");
	       		searchTags(uniqueList, frequency);
	       	} else if (tagFlag == false){
	       		System.out.println("Tag not found.");
	       		searchTags(uniqueList, frequency);
	       	}
       	} else if (input.equals("C") || input.equals("c")) { //search for count
       		System.out.println("Count to search for: ");
       		try {
       			int countToSearch = scnr.nextInt();
	       		boolean countFlag = false;
	       		if (countToSearch > 1000000000 || countToSearch < 0) { //input validation
	       			while (countToSearch > 1000000000 || countToSearch < 0) {
	       				System.out.println("Invalid input. Enter a number: ");
		       			countToSearch = scnr.nextInt();
	       			}
	       		}
	       		System.out.println("Tags with " + countToSearch + " occurences:");
	       		for (int i = 0; i < uniqueList.size() - 1; i++) {
	       			if(countToSearch == frequency.get(i)) {
	       				countFlag = true;
	       				System.out.println("* " + uniqueList.get(i));
	       			} 
	       		}
	       		if (countFlag == true) {
	       			searchTags(uniqueList, frequency);
	       		}
	       		else if (countFlag == false) {
	       			System.out.println("None found with that count.");
		       		searchTags(uniqueList, frequency);
	       		} 
       		} catch (InputMismatchException ex) {
       			System.out.println("Invalid input.");
       			searchTags(uniqueList, frequency);
       		}
       	} else if (input.equals("EXIT") || input.equals("exit")) { //exit the program
       		System.out.println("Exited successfully.");
       	}
		scnr.close();
	}

	/**
     * Sort the list of tags using the quick sort method
     * @param arr -> List of tags
     * @param bot -> First index of the list
     * @param top -> Last index of the list
     * @return -> Sorted list 
     */ 
	static void quickSort(List<String> arr, int bot, int top) {
		if (bot < top) {
			int p = partition(arr, bot, top);
			quickSort(arr, bot, p - 1);
			quickSort(arr, p + 1, top);
		}
	}
	
	/**
	 * Create the partition for quick sort
	 * @param arr -> List of tags
	 * @param bot -> Last index of the list
	 * @param top -> First index of the list
	 * @return -> Partition index
	 */
	static int partition(List<String> arr, int bot, int top) {
		String pivot = arr.get(top);
        int i = (bot - 1);
        for (int j = bot; j <= top - 1; j++) {
            if (arr.get(j).compareTo(pivot) < 0) {
                i++;
                swap(arr, i, j);  
            }
        }
        swap(arr, i + 1, top);
        return (i + 1);
	} 
	
	/**
	 * Swap two variables around
	 * @param arr -> List of values to swap
	 * @param j -> Temporary variable for swapping
	 * @param i -> Temporary variable for swapping
	 * @return -> Swapped indexes
	 */
	static void swap(List<String> arr, int j, int i) {
		String temp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, temp);
	}
	
	public static void main(String[] args) {
		String file = args[0];
		List<String> tags = readFile(file);
		System.out.println(" ==========================================");
		frequencyTags(tags);
		System.out.println(" ==========================================");
	}	
}