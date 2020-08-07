package plants;
import java.util.*;
public class StrongCardboardBox<T extends Plant, S, U> extends CardboardBox<T,S> {

	// u is a reference to the manufacturer
	private U manuRef;

	public U getManufacturerReference(){
		return this.manuRef;
	}

	public StrongCardboardBox(){}

	// <S> declared here is another S as in loc 3
	public StrongCardboardBox( S id ){
		this.id = id; 
	}

	public StrongCardboardBox( S id, U manufacturerReference ){
		this.id = id; 
		this.manuRef = manufacturerReference;
	}

	// what is S? It must be declared in class scope so in line 3
	private S id;

	private ArrayList<T> list = new ArrayList<>();

	// <S> declared here is another S as in loc 3 
	public S getId(){
		return this.id;
	}

	public boolean add(T t){
		return list.add( t );
	}
	
	public boolean remove(T t){
		return list.remove(t);
	}

	public T removeAndGet(T t){
		return list.remove( list.indexOf(t) ); 
	}

	public T get( int index ){
		return list.get(index);
	}

	public int size(){
		return list.size();
	}
}
