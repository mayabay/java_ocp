/**
 * 
 */
package util;

import java.util.Enumeration;
import java.util.Properties;

/**
 *
 */
public class SystemProperties {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SystemProperties sp = new SystemProperties();
		sp.printSystemProperties();

	}

	private void printSystemProperties() {
		Properties props = System.getProperties();
		Enumeration<?> keys = props.propertyNames();
		while( keys.hasMoreElements() ) {
			Object keyObj = keys.nextElement();
			if ( keyObj instanceof String ) {
				String key = (String) keyObj;
				String value = (String)props.get(key);
				System.out.println( key + " : " + value );
				
			}
			
		}
		
	}
	
}
