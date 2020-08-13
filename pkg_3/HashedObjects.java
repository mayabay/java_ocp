package pkg_3;
import java.util.*;
class HashedObjects {

	public static void main(String[] args){
	
		Tree t1 = new Tree( "A", 34.23D );
		Tree t2 = new Tree( "B", 14.49D );
		Tree t3 = t1;
		Tree t4 = new Tree( "B", 14.49D );
		
		Set<Tree> trees = new HashSet<>();
		
		add( trees, t1 );
		add( trees, t2 );
		add( trees, t3 );	// will not be added, hc returns same value
		add( trees, t4 );	// will be added, hc returns diff value, althoug same tree
		
		System.out.println( trees );


	}

	private static <T> void add( Set<T> set, T t ){
		set.add(t);
	}


} 
