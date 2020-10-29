/**
 * 
 */
package pkg_7;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Andreas Mann (lokal)
 *
 */
public class ZooManager {

	private Map<String,Object> foodData = new HashMap<>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ZooManager manager = new ZooManager();
		manager.test1();
		
	}

	private void test1 () {
		foodData.put("penguin",1);
		foodData.put("flamingo",2);

		Set<String> keys =  foodData.keySet();
		
		for( String s : keys ) {
			
		}		
	}
	
}
