public class LinkedList<T> implements List<T> {
	private int size;
	public Node<T> head;
	
    public class Node<T> {
        T data;
        Node<T> next;

        public Node(T value) {
        	data = value;
        	next = null;
        }
    }
    
    public LinkedList() {
        head = null;
        size = 0;
    }
	
	public int size() {
		return size;
	}
	
	public T get(int pos) {
		Node<T> curr = head;
		for (int i = 0; i < pos; i++)
			curr = curr.next;
		return curr.data;
	}
	
	public boolean add(T item) {
		if (head == null) {
			head = new Node<T>(item);
			++size;
			return true;
		}
		Node<T> prev = head;
		for (int i = 0; i < size; i++)
			prev = prev.next;
		Node<T> node = new Node<T>(item);
		prev.next = node;
		++size;
		return true;
	}
	
	public void add(int pos, T item) {
		if (pos == 0) {
			Node<T> node = new Node<T>(item);
			node.next = head;
			head = node;
			++size;
		} else {
			Node<T> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.next;
			Node<T> node = new Node<T>(item);
			node.next = prev.next;
			prev.next = node;
			++size;
		}
	}
	
	public T remove(int pos) {
		if (pos == 0) {
			Node<T> node = head;
			head = head.next;
			--size;
			return (T) node.data;
		} else {
			Node<T> prev = head;
			for (int i = 0; i< pos - 1; i++)
				prev = prev.next;
			Node<T> node = prev.next;
			prev.next = node.next;
			--size;
			return node.data;
		}
	}
	
	private class linkedListIterator<T> implements listIterator<T>{
		Node<T> node = (Node<T>) head;
		private int nextIndex;

        public linkedListIterator() {
            nextIndex = 0;
        }
		
		public boolean hasNext () {
			return node.next != null;
		}
		
		public T next () { // Return data and advance
			Node<T> prev = node;
			node = node.next;
			return prev.data;
		}
		
		public void reset() {
            nextIndex = 0;
        }
	}
}