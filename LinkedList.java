public class LinkedList<T> implements List<T> {
	private int size;
	public Node<T> head;
	
	public Node<T> getHead(){  //returns the head of linked list
		return head;
	}
    
    public LinkedList() { //linked list constructor
        head = null;
        size = 0;
    }
	
	public int size() { //returns size of linked list
		return size;
	}
	
	public T get(int pos) { //returns element at specified index
		Node<T> curr = head;
		for (int i = 0; i < pos; i++)
			curr = curr.next;
		return curr.data;
	}
	
	public boolean add(T item) { //add new element to the front default
		if (head == null) { //check if no node
			head = new Node<T>(item);
			++size;
			return true;
		}
		Node<T> node = new Node<T>(item);
		node.next = head;
		head = node;
		++size;
		return true;
	}
	
	public void add(int pos, T item) { //add new element to a specified position
		if (pos == 0) { //adding to the first spot
			Node<T> node = new Node<T>(item);
			node.next = head;
			head = node;
			++size;
		} else { //any other spot
			Node<T> prev = head;
			for (int i = 0; i < pos - 1; i++)
				prev = prev.next;
			Node<T> node = new Node<T>(item);
			node.next = prev.next;
			prev.next = node;
			++size;
		}
	}
	
	public T remove(int pos) { //remove element from specified position
		if (pos == 0) { //front of list
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
}