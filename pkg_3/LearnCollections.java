package pkg_3;
import java.util.*;
import plants.*;
public class LearnCollections {

	static List<String> birds = new ArrayList<>();

	public static void main(String[] args){
		do2();
	}

	static void do2(){
		Collection<Ananas> coll = new ArrayList<>();

	}

	static void do1(){
	
		System.out.println(  birds.add( "hawk" ));
		birds.add("hawk");
		birds.add("pigeon");
		birds.add("pinguin");
		birds.add("owl");

		System.out.println( birds );

		birds.remove( "hawk" );



		System.out.println( birds );

		System.out.println( birds.isEmpty() );
		System.out.println( birds.size() );

		System.out.println( birds.contains("owl") );


	}


}
