package hanu_key_to_gen;
import java.util.*;
class SmallBox {
	private String id;
	public String getId(){ return id; }
	public void setId(String id){ this.id = id; }
}
class SBoxT1 extends SmallBox {
	double width;
	SBoxT1( double width ){ this.width = width; }
	public double getWidth(){ return width; }
}
class SBoxT2 extends SmallBox {
	double width;
	SBoxT2( double width ){ this.width = width; }
	public double getWidth(){ return width; }
}
class UpperTypeSafe {

	public static void main(String[] args){

		List<? extends SmallBox> upperList = new ArrayList<>();
		//upperList.add( new SBoxT1 (3.14) );		// DNC 24: error: no suitable method found for add(SBoxT1)
												// where CAP#1 is a fresh type-variable:
												// CAP#1 extends SmallBox from capture of ? extends SmallBox



	}
	


}
