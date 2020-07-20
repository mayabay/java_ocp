package pkg_1;
import cando.Speaker;
class ModifierTest1 implements Speaker {

	public String says() {
		return "bla";
	}

	private static ModifierTest1 instance;

	//private static final volatile int n; // DNC field cannot be constant and volatile
	private static volatile int n; 

	public static void main(){
		instance = new ModifierTest1();

	}

	private void do1(){
	
	}


}
