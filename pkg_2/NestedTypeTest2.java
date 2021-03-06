/**
 * call ctor of inner static class in another package
 */
package pkg_2;

/**
 * NestedTypeTest2 will call ctor of inner static class in pkg_1
 *
 */
public class NestedTypeTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		pkg_1.NestedTypesTest1.B b1 = new pkg_1.NestedTypesTest1.B(23);

	}

}
