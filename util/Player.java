package util;


public class Player {

	StringBuilder sb; // --> 
	String name;
	String item;
	Integer i;
	PlayerTest.Blob blob;
	
	public Player() {
		
	}
	
	public Player( Player player ) {
		name = player.name;
		item = player.item;
		i = player.i;
		sb = player.sb;
		blob = player.blob;
	}

	@Override
	public String toString() {
		return "Player [name=" + name + ", item=" + item + ", i= "+ i +", sb = "+ sb +", blob = "+blob+"   ]";
	}
	
	
}
