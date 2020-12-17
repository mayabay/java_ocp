/**
 * 
 */
package pkg_3;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 *
 */
public class BoundsTest1 {

	interface I {}
	
	class A implements I {}	
	
	class B extends A{}
	
	class C extends B {}
	
	class T extends B {}
	
	class Kasten<U>{
		U u;
		Kasten( U u ){
			this.u = u;
		}
		public String toString() {
			return "[Kasten, u = "+ u +"]";
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 
		List<? extends A> list1 = new ArrayList<T>();
		List<? extends I> list11 = new ArrayList<A>();
		
		
		// ArrayList<T> is invariant to List<A>
		//List<A> list2 = new ArrayList<T>();
		BoundsTest1 bt1 = new BoundsTest1();
		Object listreturn = (Object)bt1.test1(list1);
		System.out.println( "listreturn = " +  listreturn);
		
		System.out.println( listreturn.getClass().getTypeName() );
		
		
		//List<?> listreturn2 = bt1.test1(list11);
		//System.out.println( "listreturn2 = " +  listreturn2.get(0));
		
		Kasten<String> kasten = bt1.new Kasten("hallo");
//		System.out.println( 
//				( (bt1.test3( kasten )).getClass().getTypeName()) 
//				); 
		Object o = bt1.test3(kasten);
		System.out.println( o.getClass().getTypeName() );
		System.out.println( ((Kasten)o) );
		
	}

	// no ? allowed in generic m() as return type
	// return type w/o wildcard <? super B>
	private <V extends Object> V test1( List<? super V> list ) {
		//return new T();	// Type mismatch: cannot convert from T to BoundsTest1.B
		// <T> B test1 ..  DNC T is hiding BoundsTest1.T
		//return new B();
		list.add( (V)new Object() );
		return (V)list.get(0);
		
		//return list.get(0);	// cannot read V from <? super V>
		//return null;
	}
	
	private B test2( List<T> list ) {
		return new T();
	}	
	
	private <X> X test3( Kasten< ? super X> kasten ) {
		Kasten<String> k2 = ( Kasten<String> )kasten;
		k2.u = "hä?";
		return (X)k2;
		
		//return (X)new Kasten( kasten.u );
	}
	
}
