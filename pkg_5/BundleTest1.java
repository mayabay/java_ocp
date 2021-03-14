/**
 * 
 */
package pkg_5;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 *
 */
public class BundleTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			System.out.println("add package content to class path ..");
			LearnResourceBundle.addPath("./src/pkg_5");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("..");
		
		System.out.println( "def. locale = " + Locale.getDefault() );
		
		System.out.println("..");
		
		BundleTest1 bt1 = new BundleTest1();
		bt1.test1();
		System.out.println("..");
		bt1.test2();
		System.out.println("..");
		bt1.test3();
		System.out.println("..");
		bt1.test4();
	}

	private void test1() {
		
		ResourceBundle rb = ResourceBundle.getBundle("pkg_5.lang");
		
		System.out.println( "lang (locale: "+ rb.getLocale() +") lang : " + rb.getObject("car") );
	}
	
	private void test2() {
		
		ResourceBundle rb = ResourceBundle.getBundle("pkg_5.lang", new Locale("de"));
		
		System.out.println( "lang (locale: "+ rb.getLocale() +") lang : " + rb.getObject("car") );
	}	
	
	private void test3() {
		
		ResourceBundle rb = ResourceBundle.getBundle("pkg_5.lang", new Locale("fr"));
		
		System.out.println( "lang (locale: "+ rb.getLocale() +") lang : " + rb.getObject("car") );
		
		System.out.println( "lang (locale: "+ rb.getLocale() +") lang : " + rb.getObject("sun") );
	}		
	
	private void test4() {
		
		ResourceBundle rb = ResourceBundle.getBundle("pkg_5.lang", new Locale("fr","CN"));
		
		System.out.println( "lang (locale: "+ rb.getLocale() +") lang : " + rb.getObject("car") );
		
		System.out.println( "lang (locale: "+ rb.getLocale() +") lang : " + rb.getObject("sun") );
	}		
	
}
