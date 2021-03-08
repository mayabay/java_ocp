package review_bs;

public class Ch2_Q18 {

    class A {}
    class B extends A {}
    class C extends B {}
    class D<C> {
        A a1 = new B();
        //C c1 = new B(); // DNS no relationship between C and B
        //C c2 = new Object();  // same
        C c3 = null;
    }

    // generic method
    // <?> not allowed
    // <? extends Number>
    // no bould with wildcard for return type
    public static <T extends Number> T go (T t){
        return t;
    }

    // DNC U cannot be resolved to a type 
    // public static U go (U u){
    //     return u;
    // }    

    // generic type U
    public static <U> U go (U u){
        return u;
    }   

    interface inter{
        <Ne>Ne allesKlar( Ne ne );
    }

    public static void main(String[] args) {
        Ch2_Q18 o = new Ch2_Q18();
        D<String> d1 = o.new D<String>();
        d1.c3 = "hello";

    }
}
