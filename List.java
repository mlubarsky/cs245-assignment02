public interface List<T>{
	public int size();
	public T get(int pos);
	public boolean add(T item);
	public void add(int pos, T item);
	public void remove(int pos);
}