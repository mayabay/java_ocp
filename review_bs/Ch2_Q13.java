package review_bs;

import java.util.ArrayList;
import java.util.List;

public class Ch2_Q13 {
    public static void main(String[] args) {
        List<? super Integer> ints = new ArrayList<Number>();
        ints.add(42);
        ints.add(73);
        System.out.println(ints);
    }
}
