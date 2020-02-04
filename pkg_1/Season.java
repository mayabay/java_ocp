package pkg_1;
public enum Season {

	SPRING("Medium"){
		
		public void printHours(){
			System.out.println( "9am - 5 pm " );
		}	
	}, SUMMER("High"){
	
		public void printHours(){
			System.out.println( "9am - 5 pm " );
		}	
	}, FALL("Medium"){
	
		public void printHours(){
			System.out.println( "9am - 5 pm " );
		}	
	}, WINTER("Low"){
	
		public void printHours(){
			System.out.println( "9am - 5 pm " );
		}	
	};
	
	private String expectedVisitors;


	private Season(String expectedVisitors){
	//public Season(String expectedVisitors){		// 10: error: modifier public not allowed here
		// 8.9.2 It is a compile-time error if a constructor declaration in an enum declaration is public or protected

		this.expectedVisitors = expectedVisitors;
	}

	public void printExpectedVisitors(){
		System.out.println( expectedVisitors  );

	}

	public abstract void printHours();
	

}
 
