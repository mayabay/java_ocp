package review_bs;

import java.util.TreeSet;

public class Ch2_Q8 {
    public static void main(String[] args) {
        Ch2_Q8 o = new Ch2_Q8();
        o.test1();
        // o.test2();


    }

    private void test1(){
        TreeSet<String> t = new TreeSet<>();
        t.add("!");
        t.add("one");
        t.add("One");
        t.add("42");
        t.add("ONE");
        System.out.println(t);
        System.out.println(t.floor("41"));
    }

    private void test2(){
        TreeSet<Integer> t = new TreeSet<>();
        t.add(3);
        t.add(5);
        t.add(1);
        t.add(12);
        t.add(7);
        System.out.println(t);
        System.out.println(t.floor(4));
    }

}
