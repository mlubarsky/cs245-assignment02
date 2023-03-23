public class LinkedList<T> implements List<T> {
	private int size;
	
    public static class LLN {
        int val;
        LLN next;

        LLN() {
        }

        LLN(int val) {
            this.val = val;
        }

        LLN(int val, LLN next) {
            this.val = val;
            this.next = next;
        }
    }
    public LLN head;

    public LinkedList() {
        this.head = new LLN();
    }
	
    //TODO: Implement
	public int size() {
		return 0;
	}
	//TODO: Implement
	public T get(int pos) {
		return null;
	}
	//TODO: Implement
	public boolean add(T item) {
		return false;
	}
	//TODO: Implement
	public void add(int pos, T item) {
		
	}
	//TODO: Implement
	public void remove(int pos) {
		
	}
}
