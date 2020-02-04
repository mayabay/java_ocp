package pkg_1;
enum Planet {  MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE  }

enum Planet2 {  MERCURY, VENUS, EARTH, MARS, JUPITER, SATURN, URANUS, NEPTUNE;  }

public class LearnEnum {

	public static void main(String[] args){
		Season s = Season.SUMMER;
		Season s2 = Season.SUMMER;
		System.out.println( "== " + (s == s2)  );

		System.out.println( s  );
		System.out.println( s == Season.SUMMER  );

		for( Season season: Season.values()  ){
			System.out.println( "Season " + season + ", ordinal " + season.ordinal()   );
		}

		//Season fall = Season.valueOf("FALgL");	// RTE java.lang.IllegalArgumentException: No enum constant pkg_1.Season.FALgL

		//switch( SUMMER  ){	// DNC 15: error: cannot find symbol
		//switch(Season.SUMMER){
		switch(s){
			case WINTER:
				System.out.println( "Get out the sled!"  );
				break;
			case SUMMER:
				System.out.println( "Time for the pool!"  );
				System.out.println( "expectedVisiors: " ); 
				//s.printExpectedVisitors();
				Season.SUMMER.printExpectedVisitors();
				break;
			default:
				System.out.println( "Is it summer yet?"  );

		}

	}
}
