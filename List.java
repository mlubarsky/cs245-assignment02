public interface List<T>{
	public int size();
	public T get(int pos);
	public boolean add(T item);
	public void add(int pos, T item);
	public T remove(int pos);
	public Iterator<T> Iterator();
}