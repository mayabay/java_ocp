package carr_c2;
public interface Bag<T> {
	
	int getCurrentSize();
	boolean isEmpty();
	int getFrequencyOf( T t );
	boolean contains(T t);
	T[] toArray();

	boolean add( T t );
	boolean remove(T t);
	boolean remove();
	void clear();

}
