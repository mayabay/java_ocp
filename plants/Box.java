package plants;
//interface Box<T super Hilo > {
interface Box<T extends Plant > {
	boolean add(T t);
	boolean remove(T t);
	T removeAndGet(T t);
	T get( int index );
	int size();
}
