package client;
import food.*;
import java.util.*;
public class Customer {

	public static void main(String[] args){
		shop();
	}

	private static void shop(){
		Fruit f1 = new Apple();
		Fruit f2 = new Banana();
		List<? super Apple> basket = new ArrayList<>();
		//fruits.add(f1);		// DNC 14: error: no suitable method found for add(Fruit)
					//CAP#1 extends Object super: Apple from capture of ? super Apple
		basket.add( new Apple() );
	}

}
