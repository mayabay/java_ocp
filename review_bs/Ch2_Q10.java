package review_bs;

interface inter1 {
	default void do1 () {}
}

interface inter2 {
	default void do1 () {}
}

// Duplicate default methods named do1 with the parameters () and () are inherited from the types inter2 and inter1
// interface child extends inter1, inter2 {}

public class Ch2_Q10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
