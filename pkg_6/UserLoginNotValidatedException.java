/**
 * BS 6.5 Learn Custom Exceptions
 * */
package pkg_6;

/**
 * Thrown when LoginUser credentials could not be validated during login attempt 
 * 
 * */
public class UserLoginNotValidatedException extends RuntimeException {

	public UserLoginNotValidatedException() {
		super();
	}
	
	public UserLoginNotValidatedException( Exception cause ) {
		super(cause);
	}
	
	public UserLoginNotValidatedException( String message ) {
		super (message);
	}
}
