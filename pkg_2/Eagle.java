package pkg_2;

public class Eagle implements Fly {
	public int getWingSpan(){
		return 15;
	}
	
	public void land(){
		System.out.println( "EAgle is diving fast" );

	}

	
	public static void main(String[] args){
		Eagle e = new Eagle();
	}

}
