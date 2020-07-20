package cando;
public interface Speaker{
	
	int volume = 42; 
	
	String says();
	
	default String hello(){
		return "hi";	
	}

	static String bye(){
		return "bye";
	}

}
