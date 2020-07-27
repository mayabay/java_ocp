package pkg_3;
import java.util.*;
class WhyGenerics {
	private Number[] n1 = { 1,2,3,4 };
	private List n2 = new ArrayList();	
	private List<Number> n3 = new ArrayList<>();

	{
		n2.add(1);
		n2.add(2);
		n2.add("ups");

		n3.add(1);
		n3.add(2);
		//n3.add("ups"); // DNC no method applicable
		

	}

	public static void main(String[] args){
		WhyGenerics wg = new WhyGenerics();

	}


	public void produceNumbers(  ){
	}

}
