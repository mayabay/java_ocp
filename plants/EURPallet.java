package plants;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * EUR pallet (https://en.wikipedia.org/wiki/EUR-pallet)
 * */
public class EURPallet<T, S> implements Pallet<T,S>, Comparable<EURPallet<T,S>> {

	private S id;
	
	// items are stored in a set
	private Set<T> set;
	
	/**
	 * Creates a new EUR pallet
	 * 
	 * @param s - unique identifier for this pallet
	 * */
	public EURPallet(S s){
		this.set = new HashSet<T>();
		this.id = s;
	}
	
	/**
	 * @return
	 * */
	public S getId() {
		return this.id;
	}
	
	@Override
	public double widthInMM() {
		return 1200.00;
	}

	@Override
	public double depthInMM() {
		return 800.00;
	}

	@Override
	public double heightInMM() {
		return 144.00;
	}

	@Override
	public int items() {
		return set.size();
	}

	@Override
	public Iterator<T> iterator() {
		return this.set.iterator();
	}

	@Override
	public boolean loadItem(T t) {
		return this.set.add(t);
	}

	@Override
	public T unloadItem(T t) {
		if ( set.contains(t) ) {
			set.remove(t);
		}
		return t;
	}

	@Override
	public void clear() {
		set.clear();
	}

	@Override
	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		
		if ( ! set.isEmpty()  ) {
			Iterator<T> iter = set.iterator();
			T t = iter.next();
			buf.append( t.getClass().getCanonicalName() );
		}else {
			buf.append("-null-");
		}
		
		return "EURPallet [loaded with= "+ buf +"; id=" + id + "]";
	}

	@Override
	public int compareTo( EURPallet<T,S> other ) {
		Integer intObj = (Integer)other.getId();
		return ((Integer)this.id).intValue() - intObj.intValue();
	}
	
}
