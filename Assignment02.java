/**
 * Assigment02 - Business Analyzer
 *
 * @author Maxwell Lubarsky 3/31/23
 */
import java.io.FileReader;
import java.io.IOException;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

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
	
	public interface List<T>{
		public int size();
		public T get(int pos) throws Exception;
		public boolean add(T item);
		public void add(int pos, T item);
		public void remove(int pos) throws Exception;
	}
	
	public class ArrayList<T> implements List<T> {
		private int size;
		private T[] arr;
		
		public ArrayList() {
			arr = (T[]) new Object[10];
			size = 0;
		}
		
		public int size () {
			return size;
		}
		
		public T get(int pos) throws Exception {
			if (pos < 0 || pos >= size)
				throw new Exception("Invalid position");
			return arr[pos];
		}
		
		public boolean add(T item) {
			if (size == arr.length)
				grow_array();
			arr[size++] = item;
			return true;
		}
		
		public void add(int pos, T item) {
			for (int i = size; i > pos; i--)
				arr[i] = arr[i - 1];
			arr[pos] = item;
			++size;
		}
		
		public void remove(int pos) throws Exception {
			if (pos < 0 || pos >= size)
				throw new Exception("Invalid position");
			System.arraycopy(arr, pos + 1, arr, pos, size - pos - 1);
	        arr[--size] = null;
		}
		
		private void grow_array() {
			T[] new_arr = (T[]) new Object [arr.length * 2];
			for (int i = 0; i < arr.length; i++)
				new_arr[i] = arr[i];
			arr = new_arr;
		}
		
	}

	
	public static void main(String[] args) {
		String file = "C:/Users/mluba/Downloads/Registered_Business_Locations_-_San_Francisco.csv";
		readFile(file);
		
//		ArrayList<Assignment02> naics = new ArrayList<>();
//		naics.add("hello");
//		System.out.println(naics.size());
		
		//TODO: Questions to ask
		//1) Does the list interface have all the functions or the ArrayList class, same with LL
		//2) Purpose of list iterator?
		//3) CSVreader not working
	}	
}