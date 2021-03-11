package util;

public class PlayerTest {

	class Blob {
		String id = "1";
		public String toString() {
			return "[blob, id = " + id + " ]";
		}
	}
	
	Player p1;
	Player p2;
	
	public static void main(String[] args) {
		
		
		String name = "Hans";
		String item = "knive";
		Integer i = 7777;
		StringBuilder sb = new StringBuilder("bla");
		
		PlayerTest playerTest = new PlayerTest();
		
		Blob blob = playerTest.new Blob();
		
		playerTest.p1 = new Player(  );
		playerTest.p1.name = name;
		playerTest.p1.item = item;
		playerTest.p1.i = i;
		playerTest.p1.sb = sb;
		playerTest.p1.blob = blob;
		
		playerTest.p2 = new Player( playerTest.p1 );

		System.out.println( playerTest.p1 );
		System.out.println( playerTest.p2 );
		
		//playerTest.p1.name = "Fritz";
		//playerTest.p1.i = 8888;
		//sb = new StringBuilder("blub");
		
		sb.append("blaaaaa");
		
		blob.id = "2";

		System.out.println( playerTest.p1 );
		System.out.println( playerTest.p2 );		
		
	}

}
