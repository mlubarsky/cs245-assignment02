public class ArrayList<T> implements List<T> {
		private int size;
		private T[] arr;
		
		@SuppressWarnings("unchecked")
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
			@SuppressWarnings("unchecked")
			T[] new_arr = (T[]) new Object [arr.length * 2];
			for (int i = 0; i < arr.length; i++)
				new_arr[i] = arr[i];
			arr = new_arr;
		}
		
	}