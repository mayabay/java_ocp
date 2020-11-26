/**
 * 
 */
package pkg_8;

import java.io.File;

/**
 * 
 *
 */
public class ConfigObj {

	private static final String WIN_USER_HOME = "USERPROFILE";
	private static final String UNIX_USER_HOME = "HOME";
	private static final String IMMO_DATA_DIR_NAME = "immodata";
	/** absolute path to immodata directory */
	public static final String IMMO_DATA_PATH;
	
	static {
		IMMO_DATA_PATH = File.separatorChar == '/' ? 
				(System.getenv(UNIX_USER_HOME) + File.separatorChar + IMMO_DATA_DIR_NAME) 
				:
				(System.getenv(WIN_USER_HOME) + File.separatorChar + IMMO_DATA_DIR_NAME);
	}	
	
}
