/**
 * BS 9.2.3
 */
package pkg_8;

import java.io.IOException;

import  pkg_9.FilesClassTest1;

/**
 * test protected access
 *
 */
public class FilesClassTest1Child extends FilesClassTest1 {

	protected FilesClassTest1Child() throws IOException {
		super();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FilesClassTest1Child fct1c = new FilesClassTest1Child();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
