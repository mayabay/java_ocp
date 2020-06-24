package hanu_key_to_gen;
import java.util.*;
class BigBox {
	public String toString(){ return "BigBox"; }
}
class BBoxT1 extends BigBox {
	double width;
	BBoxT1( double width ){ this.width = width; }
	public double getWidth(){ return width; }
	public void setWidth( double width ){ this.width = width; }
	public String toString(){ return ("BBoxT1, width = " + width);  }
}
class BBoxT2 extends BigBox {
	double width;
	BBoxT2( double width ){ this.width = width; }
	public double getWidth(){ return width; }
	public void setWidth( double width ){ this.width = width; }
	public String toString(){ return ("BBoxT1, width = " + width);  }
}
class SuperTypeSafe {

	public static void main(String[] args){
		List<? super BBoxT1> lowerList = new ArrayList<>();
		lowerList.add( new BBoxT1( 1.50 ) );
		//lowerList.add( (BigBox)new BBoxT1( 1.50 ) );	// DNC no suitable 
														// CAP#1 extends Object super: BBoxT1 from capture of ? super BBoxT1
		
		lowerList.add( new BBoxT1( 3.75 ) );
		//lowerList.add( new Object() );			// DNC 29: error: no suitable method found for add(Object)


		//BigBox bb = lowerList.get(0);	// 27: error: incompatible types: CAP#1 cannot be converted to BigBox
										// CAP#1 extends Object super: BBoxT1 from capture of ? super BBoxT1
		BigBox bb = (BigBox)lowerList.get(0);	
		Object bb2 = lowerList.get(0);	
		System.out.println( bb );

		// consume3(lowerList);		// DNC 36: error: method consume3 in class SuperTypeSafe cannot be applied to given types;
									// required: List<? extends BBoxT1>
									// found: List<CAP#1>
									// reason: argument mismatch; List<CAP#1> cannot be converted to List<? extends BBoxT1>
									// CAP#1 extends Object super: BBoxT1 from capture of ? super BBoxT1
		 
		// consume1(lowerList)
		consume2(lowerList);
		
	}


	static void consume1( List<?> list ){
		for( Object obj : list ){
			System.out.println( obj );
		}

		//list.add(new Object());		// DNC 55: error: no suitable method found for add(Object)

	}

	static void consume2( List<? super BBoxT1> list ){
		list.add( new BBoxT1(9.99) );
		for( Object obj : list ){
			//System.out.println( obj );
		}

		//for( BBoxT1 obj : list ){			// DNC CAP#1 extends Object super: BBoxT1 from capture of ? super BBoxT1
		//	System.out.println( obj );
		//}

	}

	static void consume3( List<? extends BBoxT1> list ){
		for( BBoxT1 obj : list ){
			System.out.println( obj );
			obj.setWidth( 1.00 );
		}
	}


}
