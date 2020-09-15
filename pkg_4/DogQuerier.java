package pkg_4;

/**
 * OCP KB 8
 * 
 * */
@FunctionalInterface
public interface DogQuerier {

	/**
	 * Tests a dog
	 * 
	 * @param Dog dog to be tested
	 * @return boolean true, if test succeeds
	 * */
	boolean test(Dog dog);
	
}
