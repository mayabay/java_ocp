package pkg_1;
/*
    nested class is defined as a class inside another class (nc)
    
    a nested class that is not static is called an inner class (ic)
   
   there are several types of inner classes:
        a member inner class lives inside a class (mic)
        a local inner class lives inside a method (lic)
        a annonymous local inner class is a lic without a name (alic)

*/

public class Outer {
    
    private String greeting = "Hi";

    // ---

    protected class Inner {                         // any access modifier, can extend a class or impl int
                                                    // abstract or final ok
                                                    // no static members
        public int repeat = 3;
        public void go(){
            for(int i = 0; i < repeat; i++ )
                System.out.println( greeting  );    // mic  can access private member of outer class
        }
    }

    // --

    public void callInner(){
        Inner inner = new Inner();
        inner.go();
    }

    public static void main( String[] args ){
        Outer outer = new Outer();
        outer.callInner();
        
        //Outer.Inner inner = new Inner() // DNC 40: error: non-static variable this cannot be referenced from a static context

        // new Outer().Inner inner = new Inner(); // DNC 43: error: not a statement

        // !!! an instance of outer is required to create an innner class:

        //Inner inner = Outer.new Inner()i;	// DNC 47: error: cannot find symbol

		Inner inner = outer.new Inner();	// use new as if it where an instance method of the outer class
											// can be called only on an outer instance

    }



}

