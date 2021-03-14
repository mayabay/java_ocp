/**
 * Learn ResourceBundle
 */
package pkg_5;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class LearnResourceBundle {
	
	Path current = Paths.get("./src/pkg_5");
	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		addPath("D:\\projects\\eclipse-workspace\\java_ocp\\src\\pkg_5");
		LearnResourceBundle lrb = new LearnResourceBundle();
		lrb.test3();
		
	}

	private void test3() {
		System.out.println("Your default locale is : " + Locale.getDefault());
		System.out.println("---");	
		ResourceBundle rb = ResourceBundle.getBundle("pkg_5.rb");
		System.out.println("[en_US] rb.properties : " + rb.getObject("rb.properties"));
	}
	
	private void test2(){

		System.out.println("Your default locale is : " + Locale.getDefault());
		System.out.println("---");
		
		Locale.setDefault(Locale.GERMANY);
		System.out.println("Your default locale is : " + Locale.getDefault());
		System.out.println("---");
		
		Locale locale_fr_CAN = new Locale( "fr","CAN" );
		
		ResourceBundle rb = ResourceBundle.getBundle("pkg_5.labels", locale_fr_CAN);
		System.out.println("[fr_CAN] hello : " + rb.getObject("hello"));
		System.out.println("[fr_CAN] go : " + rb.getObject("go"));
		System.out.println("[fr_CAN] jo : " + rb.getObject("jo"));
		
		System.out.println("[fr_CAN] unique_key : " + rb.getObject("unique_key"));	
		// if a bundle for java class is found, the key search will only look
		// for java class files and throw a MissingResourceException even if the 
		// key is in the bundle properties file
		// https://coderanch.com/t/672492/certification/Errata-OCP-Java-SE-Study
	}
	
	private void test1() {
		
		System.out.println("Your default locale is : " + Locale.getDefault());
		System.out.println("---");
		
		Locale locale_en = new Locale("en");
		Locale locale_en_US = new Locale("en_US");
		Locale locale_fr = new Locale("fr");
		
		ResourceBundle rb_en = ResourceBundle.getBundle("labels", locale_en);
		ResourceBundle rb_en_US = ResourceBundle.getBundle("labels", locale_en_US);
		ResourceBundle rb_fr = ResourceBundle.getBundle("labels", locale_fr);
	
		// finding the bundle for a requested key
		// look for 	en_US
		// 				en
		// 				default Locale
		// 				default Bundle
		// 				cry
		
		
		System.out.println("[en] hello in en : " + rb_en.getString("hello"));
		System.out.println("[fr] hello in fr : " + rb_fr.getString("hello"));
		
		
		// System.out.println("[fr] i am in an  : " + rb_fr.getString("elevator")); // RTE java.util.MissingResourceException
		//System.out.println("[en] i am in an  : " + rb_en.getString("elevator")); // // RTE java.util.MissingResourceException
		
		System.out.println("[en_US] i am in an  : " + rb_en_US.getString("elevator") + ", and looking at a " + rb_en_US.getString("tree") );		
		
	}
	
	/**
	 * https://stackoverflow.com/questions/7884393/can-a-directory-be-added-to-the-class-path-at-runtime
	 * */
	public static void addPath(String s) throws Exception {
	    File f = new File(s);
	    URL u = f.toURL();
	    
	    URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
	    
	    Class urlClass = URLClassLoader.class;
	    
	    Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
	    
	    method.setAccessible(true);
	    method.invoke(urlClassLoader, new Object[]{u});
	}	
	
}
