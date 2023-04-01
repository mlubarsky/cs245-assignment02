public class ArrayList<T> implements List<T> {
    T[] arr;
    int size;

    public ArrayList() {
        arr = (T[]) new Object[10]; //Initial Array Declared with size 10
        size = 0;
    }
    
    public T get(int pos) {
        return (T) arr[pos];
    }
    
    public boolean add(T item) {
        if (size == arr.length) { //If we have an array overflow
            grow_array();
        }
        arr[size++] = item; //assign item and increment size
        return false;
    }

    public void add(int pos, T item) {
        for(int i = size; i > pos; i--) {
            arr[i] = arr[i-1]; //Shifting elements by one
        }
        arr[pos] = item;
        size++;
    }

    public T remove(int pos) {

        T[] copy = (T[]) new Object[size]; //temporary array to be copied

        int j = 0; //Initializing copy-iterator index

        for (int i = 0; i < size; i++){ 
            if (i != pos) { //ignore the element at pos
                copy[j++] = arr[i];
            }
        }
        T return_obj = (T) arr[pos]; //Object to be returned
        arr = copy;
        --size;
        return return_obj;
    }

    public int size() {
        return size;
    }

    private void grow_array() {
        int newSize = size * 2; //Doubling array

        T[] copy = (T[]) new Object[newSize]; //temporary array meant for copying

        for (int i = 0; i < size; i++) {
            copy[i] = arr[i];
        }
        arr = copy; //Update arr
    }
    
    public Iterator<T> Iterator() {
        return new ListIterator<>();
    }
    
    public class ListIterator<T> implements Iterator<T> {
        private int nextIndex;

        public ListIterator() {
            nextIndex = 0;
        }

        public boolean hasNext() { 
            return nextIndex >= 0 && nextIndex < size;
        }
        
        public T next() {
            return (T) arr[nextIndex++];
        }
    }
}