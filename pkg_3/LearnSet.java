package pkg_3;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import plants.*;

public class LearnSet {

	public static void main(String[] args) {
		
		LearnSet ls = new LearnSet(); 
		ls.do1();

	}

	private void do1() {
		
		Set<Plant> set = new HashSet<>();
		//Set<Plant> set = new LinkedHashSet<>();
		
		set.add( new Cucumber() );
		set.add( new Cucumber() );
		set.add( new Hilo() );
		set.add( new Orange() );
		
		pSet(set);
		
	}
	
	private static void pSet( Set<?> set ) {
		for( Object obj : set ) {
			System.out.println(obj);
		}
	}
	
	
}
