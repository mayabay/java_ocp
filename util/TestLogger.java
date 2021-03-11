/**
 * 
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * 
 *
 */
public class TestLogger {

	 private static Logger LOGGER = null;
	
	  static {
	      InputStream stream = TestLogger.class.getClassLoader().
	              getResourceAsStream("logging.properties");
	      try {
	          LogManager.getLogManager().readConfiguration(stream);
	          LOGGER= Logger.getLogger(TestLogger.class.getName());

	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	      System.out.println("-- main method starts --");
	      LOGGER.info("in TestLogger");
	      LOGGER.warning("a test warning");

	}

}
